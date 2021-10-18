package com.example.comicapp.screen.detail_info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.comicapp.data.model.Comic

class DetailInfoViewModel : ViewModel() {
    var comic: MutableLiveData<Comic> = MutableLiveData()

    fun setComic(comic: Comic?) {
        this.comic.value = comic
    }
}
