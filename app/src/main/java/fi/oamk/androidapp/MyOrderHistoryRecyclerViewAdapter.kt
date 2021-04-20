package fi.oamk.androidapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class MyOrderHistoryRecyclerViewAdapter(
        private val values: ArrayList<OrderHistory>)
    : RecyclerView.Adapter<MyOrderHistoryRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_orderhistory, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderhistory = values[position]
        holder.nameView.text = orderhistory.name
        holder.dateView.text = orderhistory.date
        holder.userView.text = orderhistory.user
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.name)
        val dateView: TextView = view.findViewById(R.id.date)
        val userView: TextView = view.findViewById(R.id.user)
    }
}
