package com.yeminnaing.pagingwithpagingmanager.presentationLayer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeminnaing.pagingwithpagingmanager.dataLayer.pagingManager.PagingManager
import com.yeminnaing.pagingwithpagingmanager.dataLayer.repositories.GetMovieRepoKtor
import com.yeminnaing.pagingwithpagingmanager.domainLayer.Resources
import com.yeminnaing.pagingwithpagingmanager.domainLayer.models.ResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListScreenVm @Inject constructor(
    private val getMovieRepo:GetMovieRepoKtor
) : ViewModel() {

    var state by mutableStateOf(ScreenState())

    private val paginator = PagingManager(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            return@PagingManager when (val response = getMovieRepo.getMovie(nextPage)) {
                is Resources.Error -> {
                    Resources.Error(error= response.error)
                }

                is Resources.Success -> {
                   Resources.Success(data = response.data.resultModels)
                }
            }
        },
        getNextKey = {
            state.page + 1
        },
        onError = { error ->
            state = state.copy(error = error)
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                items = state.items + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    init {
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

}

data class ScreenState(
    val isLoading: Boolean = false,
    val items: List<ResultModel> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1
)