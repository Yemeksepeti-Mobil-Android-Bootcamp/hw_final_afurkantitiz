package com.afurkantitiz.foodapp.ui.profile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afurkantitiz.foodapp.data.entity.order.Order
import com.afurkantitiz.foodapp.databinding.ItemOrderCardBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    private lateinit var orderList: List<Order>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding =
            ItemOrderCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order: Order = orderList[position]

        holder.binding.apply {
            orderRestaurantName.text = order.restaurant.name
            orderQuantityCount.text = order.meals.size.toString()
            orderDate.text = SimpleDateFormat("dd/MM/yyyy").format(order.createdDate).toString()

            Glide
                .with(holder.binding.orderRestaurantImage.context)
                .load(order.restaurant.image)
                .into(holder.binding.orderRestaurantImage)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(orderList: List<Order>?) {
        orderList?.let {
            this.orderList = orderList
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = orderList.size

    inner class OrderViewHolder(val binding: ItemOrderCardBinding) :
        RecyclerView.ViewHolder(binding.root)
}