package fi.oamk.androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    private lateinit var ivImage: ImageView
    private lateinit var tvKey: TextView
    private lateinit var tvName: TextView
    private lateinit var tvprice: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ivImage = findViewById(R.id.image)
        tvKey = findViewById(R.id.key)
        tvName = findViewById(R.id.name)
        tvprice = findViewById(R.id.price)

        val key = intent.getStringExtra("key").toString()
        tvKey.text = key

        database = Firebase.database.reference

        database.child("items").child(key).get().addOnSuccessListener {
            if (it.value != null) {
                val item = it.value as HashMap<String, Any>
                val image = item.get("image")
                val name = item.get("name")
                val price = item.get("price")

                Picasso.get().load(image.toString()).into(ivImage)
                tvName.text = name.toString()
                tvprice.text = price.toString()
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