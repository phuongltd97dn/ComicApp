package com.example.comicapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Chapter(
    val id: Int,
    val name: String,
    val listImage: MutableList<String>
) : Parcelable {
    constructor() : this(0, "", mutableListOf())
}
