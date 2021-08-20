package com.afurkantitiz.foodapp.ui.profile.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afurkantitiz.foodapp.data.entity.order.Order
import com.afurkantitiz.foodapp.databinding.ItemOrderCardBinding
import com.bumptech.glide.Glide

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    private lateinit var orderList: List<Order>

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): OrderAdapter.OrderViewHolder {
        val binding = ItemOrderCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderAdapter.OrderViewHolder, position: Int) {
        val order: Order = orderList[position]

        holder.binding.itemMealTitle.text = order.meal.name
        holder.binding.itemMealSubTitle.text = order.restaurant.name

        Glide
            .with(holder.binding.itemMealImageView.context)
            .load(order.meal.image)
            .into(holder.binding.itemMealImageView)
    }

    fun setData(orderList: List<Order>?) {
        orderList?.let {
            this.orderList = orderList
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = orderList.size

    inner class OrderViewHolder(val binding: ItemOrderCardBinding): RecyclerView.ViewHolder(binding.root)
}