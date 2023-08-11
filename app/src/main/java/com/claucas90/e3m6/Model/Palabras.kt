package com.claucas90.e3m6.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TABLE_PALABRAS")
data class Palabras (
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "palabra")
    val palabra: String,

    )