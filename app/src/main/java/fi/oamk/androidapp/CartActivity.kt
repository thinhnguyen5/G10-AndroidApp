package fi.oamk.androidapp

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CartActivity : AppCompatActivity() {

    private lateinit var btnAddCart: Button

    private lateinit var tvTest : EditText
    var recievedName: String = ""
    private lateinit var ArrayCart: ArrayList<CartItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recievedName = intent.getStringExtra("name").toString()
//        tvTest.text = recievedName
        Log.e("RECIEVED_NAME: ", "$recievedName")

//        tvTest.text = intent.getStringExtra("name")

        btnAddCart = findViewById(R.id.btn_checkout)
        btnAddCart.setOnClickListener{
            Toast.makeText(this, "Checkout", Toast.LENGTH_LONG).show()
        }
    }
}