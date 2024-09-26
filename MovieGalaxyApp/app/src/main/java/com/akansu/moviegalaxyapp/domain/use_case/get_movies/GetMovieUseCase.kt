package com.akansu.moviegalaxyapp.domain.use_case.get_movies

import coil.network.HttpException
import com.akansu.moviegalaxyapp.data.remote.dto.toMovieList
import com.akansu.moviegalaxyapp.domain.model.Movie
import com.akansu.moviegalaxyapp.domain.repository.MovieRepository
import com.akansu.moviegalaxyapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject


class GetMoviesUseCase @Inject constructor(private val repository : MovieRepository) {
    fun executeGetMovies(search: String) : Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repository.getMovies(search)
            if(movieList.Response.equals("True")) {
                emit(Resource.Success(movieList.toMovieList()))
            } else {
                emit(Resource.Error(message = "No movie found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Could not reach internet"))
        }
    }

}