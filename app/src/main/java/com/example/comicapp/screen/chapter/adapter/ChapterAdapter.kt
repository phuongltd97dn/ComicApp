package com.example.comicapp.screen.chapter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.comicapp.R
import com.example.comicapp.data.model.Chapter
import com.example.comicapp.utils.OnItemChapterClickListener
import kotlinx.android.synthetic.main.item_chapter.view.*

class ChapterAdapter(private val context: Context, private val listener: OnItemChapterClickListener) :
    RecyclerView.Adapter<ChapterAdapter.ViewHolder>() {
    private val listChapter: MutableList<Chapter> = mutableListOf()

    fun setData(listChapter: MutableList<Chapter>?) {
        listChapter?.let {
            this.listChapter.clear()
            this.listChapter.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listChapter[position].let {
            holder.tvChapterDetail.text = it.name
        }

        holder.itemView.setOnClickListener {
            listener.onItemChapterClick(listChapter[position])
        }
    }

    override fun getItemCount() = listChapter.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvChapterDetail: TextView = itemView.tvChapterDetail
    }

}
