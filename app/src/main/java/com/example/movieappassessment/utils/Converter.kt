package com.example.movieappassessment.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converter(
    private val jsonParser: Gson
) {
    @TypeConverter
    fun fromString(value: String): List<Int> {
        return jsonParser.fromJson(value, object : TypeToken<List<Int>>() {}.type) ?: emptyList()
    }

    @TypeConverter
    fun toList(list: List<Int>): String? {
        return jsonParser.toJson(list, object : TypeToken<List<Int>>() {}.type)
    }
}