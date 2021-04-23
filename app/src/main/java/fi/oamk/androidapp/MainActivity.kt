package fi.oamk.androidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {

    private lateinit var  rcList :RecyclerView
    private  lateinit var  adapter: MyItemRecyclerViewAdapter


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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.app_menu,menu)

        var searchItem = menu!!.findItem(R.id.search)
        var searchView = searchItem.actionView as SearchView

        searchView.queryHint = "Search..."
        searchView.isIconifiedByDefault = false

        val magId: Int = resources.getIdentifier("android:id/search_mag_icon", null, null);
        val magImage: ImageView = searchView!!.findViewById(magId);
        val searchViewGroup: ViewGroup = magImage.getParent() as ViewGroup
        searchViewGroup.removeView(magImage)


        val queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter.filter(p0)
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
            }
        }
        searchView.setOnQueryTextListener(queryTextListener)

        val actionExpandListener = object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                adapter.filter.filter("")
                return true
            }

            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }
        }

        searchItem.setOnActionExpandListener(actionExpandListener)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.cart -> {
            this.showCart()
            true
        } else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun showCart() {
        startActivity(Intent(this, CartFragment::class.java).apply {
            putExtra("email", email)
        })
    }


//    fun signup(view: View) {
//        // add method
//        val intent = Intent(this, RegisterActivity::class.java)
//        startActivity(intent)
//    }
//
//    fun signin(view: View) {
//        val intent = Intent(this,LoginActivity::class.java)
//        startActivity(intent)
//    }
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
}
