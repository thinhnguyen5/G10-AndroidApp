package fi.oamk.androidapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

/**
 * A fragment representing a list of Items.
 */
class ItemFragment : Fragment() {

    private lateinit var database: DatabaseReference

    private lateinit var items: ArrayList<String>
    private lateinit var rcList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Firebase.database.reference

        items = arrayListOf()

//        for (i in 0..10) {
//            items.add("Item $i")
//        }
        database.child("items").get().addOnSuccessListener {
            if (it.value != null) {
//                val itemsFromDB = it as HashMap<String, Any>
                Log.e("day ne: ", "$it")
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        rcList = view.findViewById(R.id.list)
        rcList.layoutManager = LinearLayoutManager(context)
        rcList.adapter = MyItemRecyclerViewAdapter(items)

        return view
    }
}