package com.abhinand.pixbittest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhinand.pixbittest.login.domain.usecase.IsLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(isLoggedInUseCase: IsLoggedInUseCase) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    init {
        isLoggedInUseCase().onEach {
            _isLoggedIn.value = it
        }.launchIn(viewModelScope)
    }

}