package fi.oamk.androidapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class OrderHistoryFragment : Fragment() {

    private lateinit var database: DatabaseReference

    private lateinit var history: ArrayList<OrderHistory>
    private lateinit var rcList: RecyclerView
    private  lateinit var adapter: MyOrderHistoryRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        database = Firebase.database.reference

        history = arrayListOf()

//        for (i in 0..10) {
//            items.add("Item $i")
//        }
        database.child("history").get().addOnSuccessListener {
            if (it.value != null) {
//                Log.e("day ne: ", "$it")
                val historyFromDB = it.value as HashMap<String, Any>
                history.clear()
                historyFromDB.map { (key, value) ->
                    val orderhistoryFromDb = value as HashMap<String, Any>
                    val name = orderhistoryFromDb.get("name").toString()
                    val date = orderhistoryFromDb.get("date").toString()
                    val user = orderhistoryFromDb.get("user").toString()

//                    val tam = price + 2
                    val orderhistory = OrderHistory(key, name, date, user)
//                    Log.e("item", "(($price)+2)")

                    history.add(orderhistory)
                }
                rcList.adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_orderhistory_list, container, false)

        rcList = view.findViewById(R.id.list1)
        rcList.layoutManager = LinearLayoutManager(context)
        //rcList.adapter = MyOrderHistoryRecyclerViewAdapter(history)
        adapter = MyOrderHistoryRecyclerViewAdapter(history)
        rcList.adapter = adapter
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.history_menu,menu)
        var searchItem = menu.findItem(R.id.search)
        var searchView = searchItem.actionView as SearchView

        searchView.queryHint="Search..."
        searchView.isIconifiedByDefault = false

        val magId: Int = resources.getIdentifier("android:id/search_mag_icon", null, null);
        val magImage: ImageView = searchView!!.findViewById(magId);
        val searchViewGroup: ViewGroup = magImage.getParent() as ViewGroup
        searchViewGroup.removeView(magImage)


        val queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter.filter(p0)
                return true
            }
            //
            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
            }
        }
        searchView.setOnQueryTextListener(queryTextListener)

        val actionExpandListener = object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                adapter.filter.filter("")
                return true
            }

            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }
        }

        searchItem.setOnActionExpandListener(actionExpandListener)

        super.onCreateOptionsMenu(menu, inflater)
    }


}