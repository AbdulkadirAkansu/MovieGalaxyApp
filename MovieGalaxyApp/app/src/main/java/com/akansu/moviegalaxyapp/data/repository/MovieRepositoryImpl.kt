package com.akansu.moviegalaxyapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.akansu.moviegalaxyapp.data.remote.MovieAPI
import com.akansu.moviegalaxyapp.data.remote.dto.MovieDetailDto
import com.akansu.moviegalaxyapp.data.remote.dto.MoviesDto
import com.akansu.moviegalaxyapp.domain.model.Movie
import com.akansu.moviegalaxyapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api : MovieAPI) : MovieRepository {
    override suspend fun getMovies(search: String): MoviesDto {
        return api.getMovies(searchString = search)
    }
    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return api.getMovieDetail(imdbId = imdbId)
    }

}