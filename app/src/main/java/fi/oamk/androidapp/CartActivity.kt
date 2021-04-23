package fi.oamk.androidapp

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_details.*

class CartActivity : Fragment() {

    private lateinit var database: DatabaseReference

    private lateinit var cart: ArrayList<Cart>
    private lateinit var rcList: RecyclerView

    private lateinit var btnAddCart: Button

    private lateinit var tvTest : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Firebase.database.reference
        cart = arrayListOf()

        database.child("cart").get().addOnSuccessListener {
            if (it.value != null) {
                val cartFromDB = it.value as HashMap<String, Any>
                cart.clear()
                cartFromDB.map { (key, value) ->
                    val cartlistFromDB = value as HashMap<String, Any>
                    val name = cartlistFromDB.get("name").toString()
                    val image = cartlistFromDB.get("image").toString()
                    val price = cartlistFromDB.get("price")
                    val quantity = cartlistFromDB.get("quantity")

                    val cartlist = Cart(key, name, image, price as String, quantity as String)
                    cart.add(cartlist)
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
        rcList.adapter = MyCartAdapter(cart)

        return view
    }

}