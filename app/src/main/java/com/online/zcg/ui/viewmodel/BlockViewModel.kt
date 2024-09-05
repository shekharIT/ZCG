package com.online.zcg.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.online.zcg.respository.BlockRepository
import com.online.zcg.ui.UIState
import com.online.zcg.utils.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlockViewModel @Inject constructor(
    private val repository: BlockRepository,
    private val networkUtils: NetworkUtils
) : ViewModel() {

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> = _uiState

    fun fetchBlocks() {
        _uiState.postValue(UIState.Loading)

        // Check if the network is available
        if (!networkUtils.isNetworkAvailable()) {
            _uiState.postValue(UIState.Error("No internet connection"))
            return
        }

        viewModelScope.launch {
            repository.getBlocks().catch { e ->
                Log.e("BlockViewModel_ERROR", "getBlocks: ${e.message}")
                _uiState.value = UIState.Error("getBlocks: ${e.message}")
            }.collect { response ->
                _uiState.value = UIState.Success(response)
            }
        }
    }
}
