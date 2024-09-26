package com.akansu.moviegalaxyapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Search(
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
)