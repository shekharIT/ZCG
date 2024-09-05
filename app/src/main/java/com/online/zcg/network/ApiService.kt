package com.online.zcg.network

import com.online.zcg.model.Block
import retrofit2.http.GET

//https://jsonkeeper.com/b/5BEJ
interface ApiService {
    @GET("5BEJ/")
    suspend fun getBlocks(): List<Block>
}