package fi.oamk.androidapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class OrderHistoryFragment : Fragment() {

    private lateinit var database: DatabaseReference

    private lateinit var history: ArrayList<OrderHistory>
    private lateinit var rcList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        rcList.adapter = MyOrderHistoryRecyclerViewAdapter(history)

        return view
    }
}