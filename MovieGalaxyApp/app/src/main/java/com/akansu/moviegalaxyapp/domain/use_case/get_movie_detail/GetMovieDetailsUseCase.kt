package com.akansu.moviegalaxyapp.domain.use_case.get_movie_detail

import coil.network.HttpException
import com.akansu.moviegalaxyapp.data.remote.dto.toMovieDetail
import com.akansu.moviegalaxyapp.domain.model.MovieDetail
import com.akansu.moviegalaxyapp.domain.repository.MovieRepository
import com.akansu.moviegalaxyapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository : MovieRepository) {
    fun executeGetMovieDetails(imdbId: String) : Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetail(imdbId = imdbId).toMovieDetail()
            emit(Resource.Success(movieDetail))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Could not reach internet"))
        }
    }

}