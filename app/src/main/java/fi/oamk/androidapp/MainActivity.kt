package fi.oamk.androidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.firstFragment, R.id.secondFragment, R.id.thirdFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)


        bottomNavigationView.setupWithNavController(navController)
    }

    fun signup(view: View) {
        // add method
        val intent = Intent(this,RegisterActivity::class.java)
        startActivity(intent)
}

    fun signin(view: View) {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

    fun seeMore(view: View) {
//        Toast.makeText(this, "See More Button", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, DetailsActivity::class.java).apply {
            putExtra("key", view.tag.toString())
        })
    }
}
