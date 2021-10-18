package com.example.comicapp.screen.content.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.comicapp.R
import kotlinx.android.synthetic.main.item_content.view.*

class ContentAdapter(private val context: Context) :
    RecyclerView.Adapter<ContentAdapter.ViewHolder>() {
    private val listImage: MutableList<String> = mutableListOf()

    fun setData(listImage: MutableList<String>?) {
        listImage?.let {
            this.listImage.clear()
            this.listImage.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listImage[position].let {
            Glide.with(context).load(it).into(holder.imgContent)
        }
    }

    override fun getItemCount() = listImage.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgContent: ImageView = itemView.imgContent
    }

}
