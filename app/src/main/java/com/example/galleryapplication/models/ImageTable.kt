package com.example.galleryapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageTable(
    val url: String,
    @PrimaryKey()
    val id: Int
)
