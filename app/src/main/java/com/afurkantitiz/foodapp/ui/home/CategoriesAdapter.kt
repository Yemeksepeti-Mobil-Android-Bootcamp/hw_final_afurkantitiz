package com.afurkantitiz.foodapp.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.data.entity.Categories
import com.afurkantitiz.foodapp.databinding.ItemCategoriesCardBinding
import com.bumptech.glide.Glide

class CategoriesAdapter(
    private val categoriesList: ArrayList<Categories>,
    private val mContext: Context
) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {
    private var listener: ICategoriesOnClick? = null
    private var selectedItem = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding =
            ItemCategoriesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val categories: Categories = categoriesList[position]

        holder.binding.itemCategoriesName.text = categories.categoryName

        Glide
            .with(holder.binding.itemCategoriesImageView.context)
            .load(categories.categoryIcon)
            .into(holder.binding.itemCategoriesImageView)

        if (position == selectedItem) {
            holder.binding.itemCategoriesCardView.animate().scaleX(1.1f)
            holder.binding.itemCategoriesCardView.animate().scaleY(1.1f)
            holder.binding.itemCategoriesCardView.outlineSpotShadowColor =
                mContext.getColor(R.color.dark_purple)
            holder.binding.itemCategoriesCardView.outlineAmbientShadowColor =
                mContext.getColor(R.color.dark_purple)
            holder.binding.itemCategoriesImageView.setColorFilter(mContext.getColor(R.color.dark_purple))
            holder.binding.itemCategoriesName.setTextColor(mContext.getColor(R.color.dark_purple))
        } else {
            holder.binding.itemCategoriesCardView.animate().scaleX(1f)
            holder.binding.itemCategoriesCardView.animate().scaleY(1f)
            holder.binding.itemCategoriesCardView.outlineSpotShadowColor =
                mContext.getColor(R.color.dark_gray)
            holder.binding.itemCategoriesCardView.outlineAmbientShadowColor =
                mContext.getColor(R.color.dark_gray)
            holder.binding.itemCategoriesName.setTextColor(mContext.getColor(R.color.black))
            holder.binding.itemCategoriesImageView.setColorFilter(mContext.getColor(R.color.dark_gray))
        }

        holder.binding.itemCategoriesCardView.setOnClickListener {
            selectedItem = holder.adapterPosition
            listener?.let {
                listener?.onClick(position)
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = categoriesList.size

    fun addListener(listener: ICategoriesOnClick) {
        this.listener = listener
    }

    fun removeListeners() {
        this.listener = null
    }

    inner class CategoriesViewHolder(val binding: ItemCategoriesCardBinding) :
        RecyclerView.ViewHolder(binding.root)
}