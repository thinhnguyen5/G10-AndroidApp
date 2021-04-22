package fi.oamk.androidapp

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.SearchView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ItemFragment : Fragment() {

    private lateinit var database: DatabaseReference

    private lateinit var items: ArrayList<Item>
    private lateinit var rcList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Firebase.database.reference

        items = arrayListOf()

//        for (i in 0..10) {
//            items.add("Item $i")
//        }
        database.child("items").get().addOnSuccessListener {
            if (it.value != null) {
//                Log.e("day ne: ", "$it")
                val itemsFromDB = it.value as HashMap<String, Any>
                items.clear()
                itemsFromDB.map { (key, value) ->
                    val itemFromDb = value as HashMap<String, Any>
                    val name = itemFromDb.get("name").toString()
                    val image = itemFromDb.get("image").toString()
                    val pricenum = itemFromDb.get("price").toString()
                    val price = NumberFormatException(pricenum)
//                    val tam = price + 2
                    val item = Item(key, name, image, pricenum)
//                    Log.e("item", "(($price)+2)")
                    items.add(item)
                }
                rcList.adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        rcList = view.findViewById(R.id.list)
        rcList.layoutManager = LinearLayoutManager(context)
        rcList.adapter = MyItemRecyclerViewAdapter(items)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_menu,menu)
        var searchItem = menu.findItem(R.id.search)
        var searchView = searchItem.actionView as SearchView

        searchView.queryHint = "Search..."
        searchView.isIconifiedByDefault = false

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.cart -> {
            startActivity(Intent(activity, CartActivity::class.java))
            true
        } else -> {
            super.onOptionsItemSelected(item)
        }
    }


}