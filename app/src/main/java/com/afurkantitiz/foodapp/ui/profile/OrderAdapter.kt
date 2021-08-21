package com.afurkantitiz.foodapp.ui.profile

import android.R
import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afurkantitiz.foodapp.data.entity.order.Order
import com.afurkantitiz.foodapp.databinding.ItemOrderCardBinding
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat


class OrderAdapter : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    private lateinit var orderList: List<Order>

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): OrderViewHolder {
        val binding = ItemOrderCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order: Order = orderList[position]

        setItem(order, holder)
    }

    @SuppressLint("SimpleDateFormat")
    private fun setItem(order: Order, holder: OrderAdapter.OrderViewHolder) {
        holder.binding.apply {
            orderRestaurantName.text = order.restaurant.name
            orderQuantityCount.text = order.meals.size.toString()
            orderDate.text = SimpleDateFormat("dd/MM/yyyy").format(order.createdDate).toString()
//            orderDate.text = order.createdDate

            Glide
               .with(holder.binding.orderRestaurantImage.context)
               .load(order.restaurant.image)
               .into(holder.binding.orderRestaurantImage)
        }
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