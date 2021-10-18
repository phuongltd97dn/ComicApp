package com.example.comicapp.screen.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.comicapp.data.model.Comic

class HomeViewModel : ViewModel() {

    var listComics: MutableLiveData<MutableList<Comic>> = MutableLiveData()

    fun setListComics(comics: MutableList<Comic>?) {
        this.listComics.value = comics
    }
}
