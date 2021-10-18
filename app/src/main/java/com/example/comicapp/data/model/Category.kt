package com.example.comicapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Category(
    val id: Int,
    val name: String
) : Parcelable {
    constructor() : this(-1, "")
}