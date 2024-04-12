package com.menecheli.gistexplorer.utils

import androidx.room.TypeConverter
import com.menecheli.gistexplorer.domain.gist.model.Files
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class FilesConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromFilesMap(filesMap: Map<String, Files>): String {
        return gson.toJson(filesMap)
    }

    @TypeConverter
    fun toFilesMap(filesString: String): Map<String, Files> {
        val type: Type = object : TypeToken<Map<String, Files>>() {}.type
        return gson.fromJson(filesString, type)
    }
}
