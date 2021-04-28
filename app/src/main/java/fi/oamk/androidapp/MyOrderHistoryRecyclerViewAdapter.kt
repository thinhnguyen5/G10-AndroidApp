package fi.oamk.androidapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList


class MyOrderHistoryRecyclerViewAdapter(
        private val fullList: ArrayList<OrderHistory>)
    : RecyclerView.Adapter<MyOrderHistoryRecyclerViewAdapter.ViewHolder>(),Filterable {

    var filteredList = ArrayList<OrderHistory>()

    init {
        filteredList = fullList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_orderhistory, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderhistory = filteredList[position]
        holder.nameView.text = orderhistory.name
        Picasso.get().load(orderhistory.image).into(holder.imageView)
        holder.quantityView.text = orderhistory.quantity
        holder.emailView.text = orderhistory.email
        holder.priceView.text = orderhistory.price
    }

    override fun getItemCount(): Int = filteredList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.name)
        val imageView: ImageView = view.findViewById(R.id.image)
        val quantityView: TextView = view.findViewById(R.id.quantity)
        val emailView: TextView = view.findViewById(R.id.email)
        val priceView: TextView = view.findViewById(R.id.price)
    }

    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var searchString = p0.toString()

                if (searchString.isEmpty()) {
                    filteredList = fullList
                } else {
                    val results = ArrayList<OrderHistory>()

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
                filteredList = p1?.values as ArrayList<OrderHistory>
                notifyDataSetChanged()
            }
        }
    }
}
