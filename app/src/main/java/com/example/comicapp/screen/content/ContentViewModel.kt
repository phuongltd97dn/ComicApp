package com.example.comicapp.screen.content

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.comicapp.data.model.Chapter

class ContentViewModel : ViewModel() {
    var chapter: MutableLiveData<Chapter> = MutableLiveData()

    fun setChapter(chapter: Chapter?) {
        this.chapter.value = chapter
    }
}