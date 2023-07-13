package com.example.memesapi

import com.google.gson.annotations.SerializedName

data class memesList(
    @SerializedName("id"       ) var id       : String?           = null,
    @SerializedName("name"     ) var name     : String?           = null,
    @SerializedName("lines"    ) var lines    : Int?              = null,
    @SerializedName("overlays" ) var overlays : Int?              = null,
    @SerializedName("styles"   ) var styles   : ArrayList<String> = arrayListOf(),
    @SerializedName("blank"    ) var blank    : String?           = null,
    @SerializedName("meme"  ) var meme  : Meme?          = Meme(),
    @SerializedName("source"   ) var source   : String?           = null,
    @SerializedName("_self"    ) var Self     : String?           = null,
)
