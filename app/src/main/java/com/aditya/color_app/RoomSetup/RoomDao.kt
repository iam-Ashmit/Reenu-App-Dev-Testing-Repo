package com.aditya.color_app.RoomSetup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.MutableStateFlow

@Dao
interface RoomDao {

    @Query("select * from myEntity")
   suspend fun getData(): List<myEntity>


    @Insert
    suspend  fun insertData(data: myEntity)

    @Delete
    fun deleteData(data: myEntity)



}