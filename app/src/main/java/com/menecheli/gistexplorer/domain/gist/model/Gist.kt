package com.menecheli.gistexplorer.domain.gist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "gist")
data class Gist(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @SerializedName("html_url")
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "owner")
    val owner: GistOwner,
    @ColumnInfo(name = "files")
    val files: Map<String, Files>,
    @ColumnInfo(name = "updated_at")
    val updated_at: String,
    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false,
) : Serializable