package com.online.zcg.model

import com.google.gson.annotations.SerializedName

data class Block(
    @SerializedName("sectionType" )
    val sectionType: String,
    val items: List<Product> = arrayListOf()
)