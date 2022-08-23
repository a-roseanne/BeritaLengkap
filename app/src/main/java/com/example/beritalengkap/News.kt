package com.example.beritalengkap

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News (
    var title: String? = null,
    var description: String? = null,
    var photo: String? = null
) : Parcelable