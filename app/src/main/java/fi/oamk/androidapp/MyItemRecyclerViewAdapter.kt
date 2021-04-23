package fi.oamk.androidapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList


class MyItemRecyclerViewAdapter(
        private val fullList: ArrayList<Item>)
    : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>(),Filterable {

    var filteredList = ArrayList<Item>()

    init {
        filteredList = fullList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = filteredList[position]
        holder.nameView.text = item.name
        Picasso.get().load(item.image).into(holder.imageView)
        holder.priceView.text = item.price
        holder.btn_SeeMore.tag = item.key
    }

    override fun getItemCount(): Int = filteredList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.name)
        val imageView: ImageView = view.findViewById(R.id.image)
        val priceView: TextView = view.findViewById(R.id.price)
        val btn_SeeMore: Button = view.findViewById(R.id.btn_seemore)
    }

    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var searchString = p0.toString()

                if (searchString.isEmpty()) {
                    filteredList = fullList
                } else {
                    val results = ArrayList<Item>()

                    for (row in fullList) {
                        if (row.name.toLowerCase(Locale.ROOT).contains(searchString)) {
                            results.add(row)
                        }
                    }
                    filteredList = results
                }
                var filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                filteredList = p1?.values as ArrayList<Item>
                notifyDataSetChanged()
            }
        }
    }
}
