package com.example.comicapp.screen.home.adapter

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
import kotlinx.android.synthetic.main.item_comic.view.*
import java.util.*

class ComicAdapter(private val context: Context, private val listener: OnItemComicClickListener) :
    RecyclerView.Adapter<ComicAdapter.ViewHolder>() {
    private val listComic: MutableList<Comic> = mutableListOf()

    fun setData(listComic: MutableList<Comic>?) {
        listComic?.let {
            this.listComic.clear()
            this.listComic.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun sortData(string: String) {
        var k = 0
        for (index in listComic.indices) {
            if (listComic[index].name.toUpperCase(Locale.ROOT)
                    .indexOf(string.toUpperCase(Locale.ROOT)) >= 0
            ) {
                listComic[index] = listComic[k].also { listComic[k] = listComic[index] }
                k++
            }
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comic, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listComic[position].let {
            Glide.with(context).load(it.image).into(holder.imgImage)
            holder.tvChapter.text = it.newestChapter
            holder.tvName.text = it.name
        }

        holder.itemView.setOnClickListener {
            listener.onItemComicClick(listComic[position])
        }
    }

    override fun getItemCount() = listComic.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgImage: ImageView = itemView.imgImage
        val tvChapter: TextView = itemView.tvChapter
        val tvName: TextView = itemView.tvName
    }

}
