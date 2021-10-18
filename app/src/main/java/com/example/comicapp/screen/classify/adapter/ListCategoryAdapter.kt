package com.example.comicapp.screen.classify.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.comicapp.R
import com.example.comicapp.data.model.Category
import com.example.comicapp.utils.OnItemCategoryClickListener
import kotlinx.android.synthetic.main.item_category.view.*

class ListCategoryAdapter(
    private val context: Context,
    private val listener: OnItemCategoryClickListener
) :
    RecyclerView.Adapter<ListCategoryAdapter.ViewHolder>() {
    private val listCategories: MutableList<Category> = mutableListOf()
    private var selectedPosition: Int = 0

    fun setData(listCategories: MutableList<Category>?) {
        listCategories?.let {
            this.listCategories.clear()
            this.listCategories.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listCategories[position].let {
            holder.tvCategory.text = it.name
        }

        if (selectedPosition == position) {
            holder.itemView.background = context.getDrawable(R.drawable.bg_edit_text)
        } else {
            holder.itemView.background = null
        }

        holder.itemView.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
            listener.onItemCategoryClick(listCategories[position])
        }
    }

    override fun getItemCount() = listCategories.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCategory: TextView = itemView.tvCategory
    }

}
