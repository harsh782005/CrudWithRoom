package com.example.crudwithroom.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id :Int =0,
    val tittle:String,
    val content :String
)