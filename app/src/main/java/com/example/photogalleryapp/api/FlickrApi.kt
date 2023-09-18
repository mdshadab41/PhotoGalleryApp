package com.example.photogalleryapp.api

import retrofit2.http.GET

private const val API_KEY = "67bb68c7d9eb4109ed65df2e87137574"
interface FlickrApi {
    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
                "&api_key=$API_KEY" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_s"
    )
    suspend fun fetchPhotos(): String
}