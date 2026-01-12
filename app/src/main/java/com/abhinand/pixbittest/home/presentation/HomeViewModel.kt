package com.abhinand.pixbittest.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhinand.pixbittest.core.network.NetworkResource
import com.abhinand.pixbittest.home.domain.usecase.GetEmployeeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getEmployeeListUseCase: GetEmployeeListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    init {
        fetchFirstPage()
    }

    fun fetchFirstPage() {
        resetPagination()
        fetchNextPage()
    }

    fun fetchNextPage() {
        if (_uiState.value.isLoading || _uiState.value.endReached) return

        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            when (val result = getEmployeeListUseCase(_uiState.value.page)) {
                is NetworkResource.Success -> {
                    val response = result.data

                    if (response == null || response.employees.isEmpty()) {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                endReached = true
                            )
                        }
                        return@launch
                    }

                    _uiState.update {
                        it.copy(
                            employees = it.employees + response.employees,
                            page = it.page + 1,
                            endReached = it.page + 1 > response.meta.total,
                            isLoading = false
                        )
                    }
                }

                is NetworkResource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }

    private fun resetPagination() {
        fetchJob?.cancel()
        _uiState.value = HomeUiState()
    }
}