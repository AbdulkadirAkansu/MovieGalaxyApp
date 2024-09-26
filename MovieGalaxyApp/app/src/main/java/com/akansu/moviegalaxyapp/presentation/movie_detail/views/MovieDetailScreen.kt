package com.akansu.moviegalaxyapp.presentation.movie_detail.views
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.akansu.moviegalaxyapp.domain.model.MovieDetail
import com.akansu.moviegalaxyapp.presentation.movie_detail.MovieDetailState
import com.akansu.moviegalaxyapp.presentation.movie_detail.MovieDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(viewModel: MovieDetailViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    val backgroundColor by animateColorAsState(
        targetValue = if (state.movie != null) Color.Black else MaterialTheme.colorScheme.background
    )
    Scaffold(
        topBar = { MovieDetailTopBar(state.movie?.Title ?: "Movie Details") },
        containerColor = backgroundColor,
        content = { padding ->
            MovieDetailContent(state = state, modifier = Modifier.padding(padding))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailTopBar(title: String) {
    CenterAlignedTopAppBar(
        title = { Text(text = title, color = Color.White, fontWeight = FontWeight.Bold) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Transparent)
    )
}

@Composable
fun MovieDetailContent(state: MovieDetailState, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Column { // For background gradient
            LazyColumn( // Main content scrollable
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Black, Color.Transparent),
                            startY = 0f
                        )
                    ),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { MovieDetailPoster(movie = state.movie) }
                item { MovieDetailInfo(movie = state.movie) }
                item { MovieDetailCast(movie = state.movie) }
                // Add more sections if needed: Recommendations, Reviews etc.
            }
        }

        when {
            state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            state.error.isNotEmpty() -> Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun MovieDetailPoster(movie: MovieDetail?) {
    if (movie != null) {
        Image(
            painter = rememberAsyncImagePainter(movie.Poster),
            contentDescription = "Movie poster",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f) // Standard movie poster aspect ratio
                .clip(RoundedCornerShape(12.dp))
                .shadow(5.dp, RoundedCornerShape(12.dp))
        )
    }
}

@Composable
fun MovieDetailInfo(movie: MovieDetail?) {
    if (movie != null) {
        Column {
            Text(
                text = movie.Title,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Directed by: ${movie.Director}",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
            )
            Text(
                text = "Genre: ${movie.Genre}",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
            )
            Text(
                text = "Released: ${movie.Released}",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
            )
            Text(
                text = "Rating: ${movie.imdbRating}",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieDetailCast(movie: MovieDetail?) {
    if (movie != null) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Cast:",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
            )
            FlowRow(modifier = Modifier.weight(1f)) {
                movie.Actors.split(",").map { actor ->
                    Text(
                        text = actor.trim(),
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f)) // Subtle background
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieDetailScreen() {
    MovieDetailScreen()
}
