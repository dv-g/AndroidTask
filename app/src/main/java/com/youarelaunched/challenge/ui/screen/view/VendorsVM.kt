package com.youarelaunched.challenge.ui.screen.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class VendorsVM @Inject constructor(
    private val repository: VendorsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        VendorsScreenUiState(
            vendors = null
        )
    )
    val uiState = _uiState.asStateFlow()

    private val _searchFlow = MutableStateFlow("")
    private val searchFlow = _searchFlow.asStateFlow()

    fun updateSearch(name: String) {
        _searchFlow.value = name
    }

    init {
        getVendors("")

        viewModelScope.launch {
            val dropFirstTypedSymbols = 2
            val timeMilliSecondsDebouncing = 500L
            val mimSymbols = 3
            searchFlow
                .drop(dropFirstTypedSymbols)
                .debounce(timeMilliSecondsDebouncing)
                .collect { query ->
                    if (query.length >= mimSymbols) {
                        getVendors(query)
                    }
                }
        }
    }

    fun getVendors(companyName : String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    vendors = repository.getVendors(companyName)
                )
            }
        }
    }
}