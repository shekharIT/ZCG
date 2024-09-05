package com.online.zcg.ui

import com.online.zcg.model.Block

sealed class UIState {
    object Loading : UIState()
    data class Success(val blocks: List<Block>) : UIState()
    data class Error(val message: String) : UIState()
}
