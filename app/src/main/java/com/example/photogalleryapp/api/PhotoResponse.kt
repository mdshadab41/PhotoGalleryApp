package com.example.photogalleryapp.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PhotoResponse(
    @Json(name = "photo") val galleryItems: List<GalleryItem>
)