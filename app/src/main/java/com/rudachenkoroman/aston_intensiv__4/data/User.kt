package com.rudachenkoroman.aston_intensiv__4.data

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    var imageUri: Uri? = null,
) : Parcelable