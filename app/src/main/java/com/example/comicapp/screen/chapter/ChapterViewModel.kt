package com.example.comicapp.screen.chapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.comicapp.data.model.Comic

class ChapterViewModel : ViewModel() {
    var comic: MutableLiveData<Comic> = MutableLiveData()

    fun setComic(comic: Comic?) {
        this.comic.value = comic
    }
}