package com.tarekrefaei.littlelemonstore.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tarekrefaei.littlelemonstore.data.MenuDataImpl
import com.tarekrefaei.littlelemonstore.utils.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val repo: MenuDataImpl
) : ViewModel() {

    var state by mutableStateOf(RestaurantState())
    private var filterJob: Job? = null

    init {
        getMenuItems()
    }

    fun onEvent(events: RestaurantEvents) {
        when (events) {
            is RestaurantEvents.OnFilter -> {
                state = state.copy(
                    searchQuery = events.query
                )
                filterJob?.cancel()
                filterJob = viewModelScope.launch {
                    delay(500)
                    getMenuItems()
                }
            }
        }
    }

    private fun getMenuItems(
        query:String = state.searchQuery.lowercase()
    ) {
        viewModelScope.launch {
            repo.getMenu(query = query).collect { result ->
                when (result) {
                    is Resources.Error -> {

                    }
                    is Resources.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                    is Resources.Success -> {
                        result.data?.let { menuItems ->
                            state = state.copy(
                                menuList = menuItems
                            )
                        }
                    }
                }
            }
        }
    }
}