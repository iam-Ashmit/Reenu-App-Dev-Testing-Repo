package com.aditya.color_app.RoomSetup

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class myEntity(

    val colorName:String="",
    val time:Long=0L,
    @PrimaryKey(autoGenerate = true)
    val id:Int=0
)
