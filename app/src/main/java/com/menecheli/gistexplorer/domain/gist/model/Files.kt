package com.menecheli.gistexplorer.domain.gist.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Files (
    @SerializedName("filename")
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "type")
    val type: String,
    @SerializedName("raw_url")
    @ColumnInfo(name = "url")
    val url: String
)  : Serializable
