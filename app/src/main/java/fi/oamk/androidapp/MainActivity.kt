package fi.oamk.androidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {

    private lateinit var  rcList :RecyclerView
    private  lateinit var  adapter: MyItemRecyclerViewAdapter
    private lateinit var database: DatabaseReference

    private lateinit var items: ArrayList<Item>


    var email: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Firebase.database.reference

        items = arrayListOf()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.firstFragment, R.id.secondFragment, R.id.profileFragment))

        setupActionBarWithNavController(navController, appBarConfiguration)


        bottomNavigationView.setupWithNavController(navController)

        email = intent.getStringExtra("email").toString()
    }
    fun signup(view: View) {
        // add method
        val intent = Intent(this,RegisterActivity::class.java)
        startActivity(intent)
}

    fun seeMore(view: View) {
//        Toast.makeText(this, "See More Button", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, DetailsActivity::class.java).apply {
            putExtra("key", view.tag.toString())
            putExtra("email", email)
        })
    }

    fun setting(item: MenuItem) {
        startActivity(Intent(this, ChangepasswordActivity::class.java).apply {
        })
    }

    fun cart(item: MenuItem) {
        startActivity(Intent(this, CartActivity::class.java).apply {
        })
    }

}


