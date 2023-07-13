package com.example.memesapi

import com.google.gson.annotations.SerializedName

data class Meme(
    @SerializedName("text") var text: ArrayList<String>?=null,
    @SerializedName("url") var url :String ?= null
)
