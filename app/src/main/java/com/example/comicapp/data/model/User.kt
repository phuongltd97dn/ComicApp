package com.example.comicapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(
    var id: Int,
    var cover: String,
    var avatar: String,
    var username: String,
    var password: String,
    var fullName: String,
    var email: String,
    var phone: String
) : Parcelable {
    constructor() : this(-1, "", "", "", "", "", "", "")
}
