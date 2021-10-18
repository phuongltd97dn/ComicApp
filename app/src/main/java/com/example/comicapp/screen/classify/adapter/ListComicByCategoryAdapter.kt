package com.example.comicapp.screen.classify.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.comicapp.R
import com.example.comicapp.data.model.Comic
import com.example.comicapp.utils.OnItemComicClickListener
import kotlinx.android.synthetic.main.item_comic_by_category.view.*

class ListComicByCategoryAdapter(
    private val context: Context,
    private val listener: OnItemComicClickListener
) : RecyclerView.Adapter<ListComicByCategoryAdapter.ViewHolder>() {

    private val listComicByCategory: MutableList<Comic> = mutableListOf()

    fun setData(listComic: MutableList<Comic>?) {
        listComic?.let {
            this.listComicByCategory.clear()
            this.listComicByCategory.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comic_by_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listComicByCategory[position].apply {
            holder.tvComicByCategory.text = name
            Glide.with(context).load(image).into(holder.imgComicByCategoryImage)
            holder.tvComicByCategoryStatus.text = status
            holder.tvComicByCategoryNewest.text =
                context.getString(R.string.text_view_comic_by_category_newest_text, newestChapter)

            holder.itemView.setOnClickListener {
                listener.onItemComicClick(this)
            }
        }
    }

    override fun getItemCount() = listComicByCategory.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgComicByCategoryImage: ImageView = itemView.imgComicByCategoryImage
        val tvComicByCategory: TextView = itemView.tvComicByCategoryName
        val tvComicByCategoryStatus: TextView = itemView.tvComicByCategoryStatus
        val tvComicByCategoryNewest: TextView = itemView.tvComicByCategoryNewest
    }

}
