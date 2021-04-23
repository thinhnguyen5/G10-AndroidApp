package fi.oamk.androidapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class MyCartRecyclerViewAdapter(
        private val values: ArrayList<Cart>)
    : RecyclerView.Adapter<MyCartRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cart = values[position]
        holder.nameView.text = cart.name
        Picasso.get().load(cart.image).into(holder.imageView)
        holder.priceView.text = cart.price
        holder.quantityView.text = cart.quantity.toString()
        holder.btn_Delete.tag = cart.key

//        holder.btn_Minus.setOnClickListener{minusCartItem(holder,item)}
    }

//    private fun minusCartItem(holder: ViewHolder, cartItem: CartItem) {
//        if (cartItem.quantity > 1) {
//            cartItem.quantity - 1
//            cartItem.totalPrice = (cartItem.quantity * cartItem.price!!.toFloat()).toInt()
//            holder.quantityView.text = item.quantity.toString()
//        }
//    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.name)
        val imageView: ImageView = view.findViewById(R.id.image)
        val priceView: TextView = view.findViewById(R.id.price)
        val quantityView: TextView = view.findViewById(R.id.quantity)
        val btn_Minus: ImageView = view.findViewById(R.id.btnMinus)
        val btn_Plus: ImageView = view.findViewById(R.id.btnPlus)
        val btn_Delete: Button = view.findViewById(R.id.btnDelete)
    }
}
