package com.akansu.moviegalaxyapp.presentation.movie_detail

import com.akansu.moviegalaxyapp.domain.model.MovieDetail


data class MovieDetailState(
    val isLoading : Boolean = false,
    val movie : MovieDetail? = null,
    val error : String = ""
)