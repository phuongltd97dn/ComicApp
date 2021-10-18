package com.example.comicapp.screen.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.comicapp.data.model.Comic

class DetailViewModel : ViewModel() {
    var comic: MutableLiveData<Comic> = MutableLiveData()

    fun setComic(comic: Comic?) {
        this.comic.value = comic
    }
}
