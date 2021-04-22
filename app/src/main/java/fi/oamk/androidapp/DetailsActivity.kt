package fi.oamk.androidapp

import android.icu.util.TimeZone.getDefault
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.*
import com.google.common.eventbus.EventBus
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.fragment_profile.*
import java.util.Locale.getDefault

class DetailsActivity : AppCompatActivity() {
//class DetailsActivity : Fragment() {

    private lateinit var database: DatabaseReference

    private lateinit var ivImage: ImageView
    private lateinit var tvKey: TextView
    private lateinit var tvName: TextView
    private lateinit var tvprice: TextView
    private lateinit var etQuantity: EditText
    private lateinit var tvSumPrice: TextView

    private lateinit var btnAddCart: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ivImage = findViewById(R.id.image)
        tvKey = findViewById(R.id.key)
        tvName = findViewById(R.id.name)
        tvprice = findViewById(R.id.price)
        etQuantity = findViewById(R.id.quantity)
        tvSumPrice = findViewById(R.id.sumPrice)

        val key = intent.getStringExtra("key").toString()
        tvKey.text = key

        val email = intent.getStringExtra("email").toString()
        Log.e("this is email: ", email)

        database = Firebase.database.reference

        //button Add To Cart
        btnAddCart = findViewById(R.id.btn_addCart)

        //set status for item
        var tam: String = ""

        //sum price when choose quantity
        var tam1: Number = 0
//        Log.e("typeof tam1", "${typeOf<>(tam1)}")
//        typeOf<>()


        database.child("items").child(key).get().addOnSuccessListener {
            if (it.value != null) {
                val item = it.value as HashMap<String, Any>
                val image = item.get("image")
                val name = item.get("name")
                val price = item.get("price")
                val status = item.get("status")

                Picasso.get().load(image.toString()).into(ivImage)
                tvName.text = name.toString()
                tvprice.text = price.toString()
                tam = status.toString()
//                Log.e("tam", "$tam")
                if (tam == "Out of Stock") {
                    btnAddCart.isEnabled = false
                    Toast.makeText(this, "DISABLE", Toast.LENGTH_LONG).show()
//                    Log.e("button", "disable")
                }
                else {
                    btnAddCart.setOnClickListener{
                        val quantity = etQuantity.text.toString().trim()
                        if (quantity.isEmpty()) {
                            etQuantity.setError("Enter the quantity");
                            etQuantity.requestFocus();
                            return@setOnClickListener
                        }
                        val ref = FirebaseDatabase.getInstance().getReference("cart")
//                        val cartItem = CartItem(key, email, name as String,
//                            image as String, price as String, quantity as Int, price as Int)
                        val cartItem = CartItem()
                        cartItem.key = key
                        cartItem.name = name.toString()
                        cartItem.email = email
                        cartItem.image = image.toString()
                        cartItem.price = price.toString()
                        cartItem.quantity = quantity.toInt()
                        cartItem.totalPrice = price.toString().toInt()

                        ref.child(key).setValue(cartItem).addOnSuccessListener {
                            Toast.makeText(this,"Success add to Cart", Toast.LENGTH_LONG).show()
                        }
                            .addOnFailureListener {
                                Toast.makeText(this, "Fail", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
        }.addOnFailureListener{
            //
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}