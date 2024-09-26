package com.akansu.moviegalaxyapp.presentation.movies

import com.akansu.moviegalaxyapp.domain.model.Movie


data class MoviesState(
    val isLoading : Boolean = false,
    val movies : List<Movie> = emptyList(),
    val error : String = "",
    val search : String = "batman"
)
