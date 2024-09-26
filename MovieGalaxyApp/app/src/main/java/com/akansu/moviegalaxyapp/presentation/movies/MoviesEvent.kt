package com.akansu.moviegalaxyapp.presentation.movies

sealed class MoviesEvent {
    data class Search(val searchString :String) : MoviesEvent()
}