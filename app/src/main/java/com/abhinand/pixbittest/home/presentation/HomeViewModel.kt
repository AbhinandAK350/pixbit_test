package com.abhinand.pixbittest.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhinand.pixbittest.core.network.NetworkResource
import com.abhinand.pixbittest.home.domain.usecase.GetEmployeeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getEmployeeListUseCase: GetEmployeeListUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun fetchEmployeeList() {
        viewModelScope.launch {
            if (_uiState.value.endReached || _uiState.value.isLoading) return@launch

            _uiState.update { it.copy(isLoading = true) }

            when (val result = getEmployeeListUseCase(_uiState.value.page)) {
                is NetworkResource.Success -> {
                    _uiState.update {
                        it.copy(
                            employees = result.data!!,
                            page = it.page,
                            endReached = result.data.isEmpty(),
                            isLoading = false
                        )
                    }
                }

                is NetworkResource.Error -> {
                    _uiState.update {
                        it.copy(
                            error = result.message,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}