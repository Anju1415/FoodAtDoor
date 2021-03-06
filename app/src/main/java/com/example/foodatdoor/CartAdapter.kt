package com.example.foodatdoor

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class CartAdapter(val context: Context, private val cartItems:ArrayList<CartItems>):
    RecyclerView.Adapter<CartAdapter.ViewHolderCart>() {


    class ViewHolderCart(view: View): RecyclerView.ViewHolder(view){
        val textViewOrderItem: TextView =view.findViewById(R.id.textViewOrderItem)
        val textViewOrderItemPrice: TextView =view.findViewById(R.id.textViewOrderItemPrice)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCart {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_recycler_single_row,parent,false)

        return ViewHolderCart(view)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolderCart, position: Int) {
        val cartItemObject=cartItems[position]
        holder.textViewOrderItem.text=cartItemObject.itemName
        holder.textViewOrderItemPrice.text="Rs. "+cartItemObject.itemPrice
    }


}