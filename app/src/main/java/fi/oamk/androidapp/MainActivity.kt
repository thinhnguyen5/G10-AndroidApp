package fi.oamk.androidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    var email: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    fun signin() {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

    fun seeMore(view: View) {
//        Toast.makeText(this, "See More Button", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, DetailsActivity::class.java).apply {
            putExtra("key", view.tag.toString())
        })
    }

    fun signout(view: View) {
        // add method
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
}
