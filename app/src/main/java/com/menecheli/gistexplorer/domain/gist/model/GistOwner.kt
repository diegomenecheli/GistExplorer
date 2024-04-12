package com.menecheli.gistexplorer.domain.gist.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GistOwner (
    @ColumnInfo(name = "login")
    val login: String,
    @SerializedName("avatar_url")
    @ColumnInfo(name = "avatar")
    val avatar: String
) : Serializable
