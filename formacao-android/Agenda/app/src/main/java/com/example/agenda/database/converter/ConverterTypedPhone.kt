package com.example.agenda.database.converter

import androidx.room.TypeConverter
import com.example.agenda.model.TypePhone

class ConverterTypedPhone {

    @TypeConverter
    fun toString(typePhone: TypePhone?): String? {
        return typePhone?.let { typePhone ->
            return@let typePhone.name
        }
    }

    @TypeConverter
    fun toTypePhone(value: String?): TypePhone? {
        return value?.let { value ->
            return@let TypePhone.valueOf(value.uppercase())
        }
    }
}