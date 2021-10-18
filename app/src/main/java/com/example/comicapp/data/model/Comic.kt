package com.example.comicapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Comic(
    var id: Int,
    var name: String,
    var image: String,
    var author: String,
    var status: String,
    var description: String,
    var listChapters: MutableList<Chapter>,
    var newestChapter: String = listChapters[listChapters.size - 1].name,
    var listCategories: MutableList<Category>
) : Parcelable {
    constructor() : this(-1, "", "", "", "", "", mutableListOf(), "", mutableListOf())
}
