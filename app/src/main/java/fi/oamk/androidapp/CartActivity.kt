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
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {
//class CartActivity : Fragment() {

    private lateinit var cartitems: ArrayList<CartItem>
    private lateinit var rcItems: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager

    private lateinit var btnAddCart: Button

    private lateinit var tvTest : EditText
//    private lateinit var ArrayCart: ArrayList<CartItem>

//    override fun onCreateView(
//            inflater: LayoutInflater, container: ViewGroup?,
//            savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.activity_cart, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        view.findViewById<Button>(R.id.btn_checkout).setOnClickListener {
////            Toast.makeText(this, "Checkout Button", Toast.LENGTH_LONG).show()
//            Log.e("ajshdgbjhasgdasgjdas", "this is function Checkout")
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        //////////////////////////cai moi///////////////////////////

        rcItems = findViewById(R.id.listItems)
//        rcItems.setHasFixedSize(true)
//        layoutManager

        cartitems = arrayListOf()

        val name = intent.getStringExtra("name").toString()
        val price = intent.getStringExtra("price").toString()
//        tvTest.text = recievedName
        Log.e("RECIEVED_NAME: ", "$name")
        Log.e("RECIEVED_PRICE: ", "$price")

//        val additems = CartItem(name, price)
//        cartitems.add(additems)
//        Log.e("NAME: ", "${cartitems.size}")

//        tvTest.text = intent.getStringExtra("name")

//        if (recievedName != null) {
//            val name = recievedName
////            cartitems.add(name)
//        }

        btnAddCart = findViewById(R.id.btn_checkout)
        btnAddCart.setOnClickListener{
            Toast.makeText(this, "Checkout", Toast.LENGTH_LONG).show()
//            Toast.makeText(this, "${cartitems.size}", Toast.LENGTH_LONG).show()
//            Log.e("CartItems: ", "$cartitems")
        }
    }


}