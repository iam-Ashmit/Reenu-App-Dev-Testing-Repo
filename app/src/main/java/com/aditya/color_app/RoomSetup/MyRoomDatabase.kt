package com.aditya.color_app.RoomSetup

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [myEntity::class], version = 1)
abstract class MyRoomDatabase:RoomDatabase() {

    abstract val myDao:RoomDao




}