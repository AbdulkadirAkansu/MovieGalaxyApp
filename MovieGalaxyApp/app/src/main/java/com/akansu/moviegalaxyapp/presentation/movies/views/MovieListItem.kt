package com.akansu.moviegalaxyapp.presentation.movies.views


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.akansu.moviegalaxyapp.domain.model.Movie
import com.akansu.moviegalaxyapp.presentation.ui.theme.popins

@Composable
fun MovieListItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    Column(
        modifier = modifier
            .height(250.dp)
            .clickable { onItemClick(movie) },
    ) {
        Box(modifier = Modifier.clip(RoundedCornerShape(16.dp))) {
            AsyncImage(
                model = movie.Poster,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(bottomStart = 2.dp, bottomEnd = 2.dp))
            )
        }
    }
    Spacer(modifier = Modifier.padding(3.dp))
    Text(
        text = movie.Title,
        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
        fontFamily = popins,
        textAlign = TextAlign.Start,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
 }

@Composable
@Preview
fun MovieListPrevies(){
    MovieListItem(movie = Movie("","","",""), onItemClick = {} )
}


