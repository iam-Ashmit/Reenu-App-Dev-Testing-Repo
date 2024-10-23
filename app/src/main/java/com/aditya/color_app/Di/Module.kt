package com.aditya.color_app.Di

import android.app.Application
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.aditya.color_app.RoomSetup.MyRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    @Singleton
    fun getDB(application: Application):MyRoomDatabase{
      return  Room.databaseBuilder(application,MyRoomDatabase::class.java,"myData").allowMainThreadQueries() .build()
    }





}