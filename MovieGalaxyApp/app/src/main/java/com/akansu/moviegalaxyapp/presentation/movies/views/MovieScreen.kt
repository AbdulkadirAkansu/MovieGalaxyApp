package com.akansu.moviegalaxyapp.presentation.movies.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.akansu.moviegalaxyapp.presentation.Screen
import com.akansu.moviegalaxyapp.presentation.movies.MoviesEvent
import com.akansu.moviegalaxyapp.presentation.movies.MoviesViewModel


@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel(),
    mainViewModel: MainViewModel
) {
    val state = viewModel.state.value
    val searchWidgetState by mainViewModel.searchWidgetState
    val searchTextState by mainViewModel.searchTextState

    Column() {
        Scaffold(
            modifier = Modifier.background(MaterialTheme.colorScheme.primary),
            topBar = {
                MainAppBar(
                    searchWidgetState = searchWidgetState,
                    searchTextState = searchTextState,
                    onTextChange = {
                        mainViewModel.updateSearchTextState(newValue = it)
                    },
                    onCloseClicked = {
                        mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                    },
                    onSearchClicked = {
                        viewModel.onEvent(MoviesEvent.Search(it))
                    },
                    onSearchTriggered = {
                        mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                    }
                )
            },
            content = {
                Column(Modifier.padding(it)) {
                LazyVerticalGrid(
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                        .padding(15.dp),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(state.movies) { movie ->
                        Column(
                            modifier = Modifier
                                .padding(5.dp)
                        ) {
                            MovieListItem(movie = movie, onItemClick = {
                                navController.navigate(Screen.MovieDetailScreen.route + "/${movie.imdbID}")
                            })
                        }
                    }
                }

                if (state.error.isNotBlank()) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    )
                }

                if (state.isLoading) {
                    Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                   }
                }
            }
        )
    }
}