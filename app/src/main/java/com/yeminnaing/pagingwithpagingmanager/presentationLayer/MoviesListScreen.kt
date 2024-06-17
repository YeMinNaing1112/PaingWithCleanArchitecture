package com.yeminnaing.pagingwithpagingmanager.presentationLayer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yeminnaing.pagingwithpagingmanager.domainLayer.models.ResultModel


@Composable
fun MovieListScreen() {
    val viewModel: MovieListScreenVm = hiltViewModel()
    val state = viewModel.state
    MovieListScreenDesign(modifier = Modifier, state){
        viewModel.loadNextItems()
    }
}

@Composable
fun MovieListScreenDesign(modifier: Modifier, state: ScreenState,  loadNextItems: () -> Unit) {
    LazyColumn(modifier= modifier.fillMaxSize()){
        items(state.items.size){
            val item = state.items[it]
            if (it >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                loadNextItems()
            }
            MovieListCard(resultModel = item)
        }
        item {
            if (state.isLoading) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }


}

@Composable
fun MovieListCard(resultModel: ResultModel) {
    Card(Modifier.padding(top = 16.dp)) {
        Column(Modifier.padding(10.dp)) {
            Text(text = resultModel.original_title)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = resultModel.overview, maxLines = 4)
        }

    }
}

@Preview
@Composable
fun MovieListCardPrev() {
    MovieListCard(
        resultModel = ResultModel(
            adult = false,
            backdrop_path = "/stKGOm8UyhuLPR9sZLjs5AkmncA.jpg",
            genre_ids = listOf(
                16,
                10751,
                18,
                12,
                35
            ),
            id = 1022789,
            original_language = "en",
            original_title = "Inside Out 2 ",
            overview = "Teenager Riley's mind headquarters is undergoing a sudden demolition to make room for something entirely unexpected: new Emotions! Joy, Sadness, Anger, Fear and Disgust, who’ve long been running a successful operation by all accounts, aren’t sure how to feel when Anxiety shows up. And it looks like she’s not alone.",
            popularity = 4974.9,
            poster_path = "/vpnVM9B6NMmQpWeZvzLvDESb2QY.jpg ",
            release_date = "2024-06-11",
            title = " Inside Out 2",
            video = false,
            vote_average = 7.636,
            vote_count = 143
        )
    )

}
