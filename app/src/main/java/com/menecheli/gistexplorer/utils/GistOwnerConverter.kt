package com.menecheli.gistexplorer.utils

import androidx.room.TypeConverter
import com.menecheli.gistexplorer.domain.gist.model.GistOwner
import org.json.JSONObject

class GistOwnerConverter {
    @TypeConverter
    fun fromGistOwner(gistOwner: GistOwner): String {
        return JSONObject().apply {
            put("avatar", gistOwner.avatar)
            put("login", gistOwner.login)
        }.toString()
    }

    @TypeConverter
    fun toGistOwner(gistOwner: String): GistOwner {
        val json = JSONObject(gistOwner)
        return GistOwner(json.getString("login"), json.getString("avatar"))
    }
}