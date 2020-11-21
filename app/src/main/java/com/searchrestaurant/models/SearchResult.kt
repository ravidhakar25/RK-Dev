package com.searchrestaurant.models

data class SearchResult(val results: ArrayList<Restaurant>)
data class Restaurant(
    val vicinity: String,
    val icon: String,
    val id: String,
    val name: String,
    val opening_hours: OpeningHours,
    val place_id: String,
    val plus_code: PlusCode,
    val price_level: String,
    val rating: String,
    val reference: String,
    val types: ArrayList<String>,
    val user_ratings_total: String,
)

data class OpeningHours(val open_now: Boolean=false)
data class PlusCode(val compound_code: String, val global_code: String)