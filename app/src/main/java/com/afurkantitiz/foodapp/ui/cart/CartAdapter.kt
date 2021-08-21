package com.afurkantitiz.foodapp.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afurkantitiz.foodapp.data.entity.Cart
import com.afurkantitiz.foodapp.databinding.ItemCartCardBinding

class CartAdapter: RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private lateinit var carts: List<Cart>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartViewHolder {
        val binding = ItemCartCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cart = carts[position]

        holder.binding.apply {
            itemCartRestaurantName.text = cart.restaurantName
            itemCardFoodName.text = cart.mealName
        }

       /* Glide
            .with(holder.binding.itemCartFoodImageView.context)
            .load(cart.)
            .into(holder.binding.itemMealsImageView)*/
    }

    fun setCarts(carts: List<Cart>){
        this.carts = carts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = carts.size

    inner class CartViewHolder(val binding: ItemCartCardBinding): RecyclerView.ViewHolder(binding.root)
}