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


class CartFragment : Fragment() {

    private lateinit var database: DatabaseReference

    private lateinit var cartitems: ArrayList<Cart>
    private lateinit var rcList: RecyclerView
    private  lateinit var adapter: MyCartRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Firebase.database.reference

        cartitems = arrayListOf()

//        for (i in 0..10) {
//            items.add("Item $i")
//        }
        database.child("cartitems").get().addOnSuccessListener {
            if (it.value != null) {
//                Log.e("day ne: ", "$it")
                val cartitemsFromDB = it.value as HashMap<String, Any>
                cartitems.clear()
                cartitemsFromDB.map { (key, value) ->
                    val cartFromDb = value as HashMap<String, Any>
                    val name = cartFromDb.get("name").toString()
                    val image = cartFromDb.get("image").toString()
                    val price = cartFromDb.get("price").toString()
                    val quantity = cartFromDb.get("quantity").toString()

                    val cart = Cart(key, name, image, price, quantity)
//                    Log.e("item", "(($price)+2)")
                    cartitems.add(cart)
                }
                rcList.adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_cart, container, false)

        rcList = view.findViewById(R.id.listItems)
        rcList.layoutManager = LinearLayoutManager(context)
//        rcList.adapter = MyItemRecyclerViewAdapter(items)
        adapter = MyCartRecyclerViewAdapter(cartitems)
        rcList.adapter = adapter

        return view
    }


}