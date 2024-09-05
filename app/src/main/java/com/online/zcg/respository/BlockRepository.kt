package com.online.zcg.respository

import com.online.zcg.model.Block
import com.online.zcg.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/*class BlockRepository  @Inject constructor(private val apiService: ApiService) {
    suspend fun getBlocks() = apiService.getBlocks()
}*/

class BlockRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getBlocks(): Flow<List<Block>> = flow {
        val response = apiService.getBlocks()
        emit(response)
    }.flowOn(Dispatchers.IO)
}