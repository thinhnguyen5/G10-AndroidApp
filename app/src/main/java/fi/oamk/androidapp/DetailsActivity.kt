package fi.oamk.androidapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlin.reflect.typeOf

class DetailsActivity : AppCompatActivity() {

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

        database = Firebase.database.reference

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
            }
        }.addOnFailureListener{
            //
        }

//        tam1 = etQuantity * price

        btnAddCart = findViewById(R.id.btn_addCart)
        btnAddCart.setOnClickListener() {
//            Log.d("kakak","$tam")
            if (tam == "Out of Stock") {
                AlertDialog.Builder(this)
                        .setMessage("$tam")
                        .setPositiveButton("OK", object : DialogInterface.OnClickListener {
                            override fun onClick(p0: DialogInterface?, p1: Int) {
//                                val intent = Intent(this, MainActivity::class.java)
//                                startActivity(intent)
//                                val intent = Intent(this,RegisterActivity::class.java)
//                                startActivity(intent)
//                                startActivity(Intent(this, MainActivity::class.java))
                            }
                        })
                        .create()
                        .show()
            }
            else {
//                AlertDialog.Builder(this)
//                        .setMessage("Added Successfully!")
//                        .setPositiveButton("OK", object : DialogInterface.OnClickListener {
//                            override fun onClick(p0: DialogInterface?, p1: Int) {
////                                TODO("Not yet implemented")
//                            }
//                        })
//                        .create()
//                        .show()
            }
//            Toast.makeText(this, "$tam", Toast.LENGTH_LONG).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}