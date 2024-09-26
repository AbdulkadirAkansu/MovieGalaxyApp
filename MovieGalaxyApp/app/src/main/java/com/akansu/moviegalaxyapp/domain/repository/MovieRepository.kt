package com.akansu.moviegalaxyapp.domain.repository

import android.adservices.adid.AdId
import com.akansu.moviegalaxyapp.data.remote.dto.MovieDetailDto
import com.akansu.moviegalaxyapp.data.remote.dto.MoviesDto

interface MovieRepository {

    suspend fun getMovies(search : String) : MoviesDto

    suspend fun getMovieDetail(imdbId : String) : MovieDetailDto


}