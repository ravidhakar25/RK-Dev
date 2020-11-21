package com.searchrestaurant.repository

import com.searchrestaurant.models.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("/maps/api/place/nearbysearch/json?location=47.6204,-122.3491&radius=2500&type=restaurant&key=AIzaSyDhHzf6y1brSirE2hPeMjTqSBYE73pzukM")
    suspend fun nearBySearch(): SearchResult

    @GET("/maps/api/place/nearbysearch/json?location=47.6204,-122.3491&radius=2500&type=restaurant&key=AIzaSyDhHzf6y1brSirE2hPeMjTqSBYE73pzukM")
    suspend fun nearBySearch(@Query("keyword") keyword: String): SearchResult

}