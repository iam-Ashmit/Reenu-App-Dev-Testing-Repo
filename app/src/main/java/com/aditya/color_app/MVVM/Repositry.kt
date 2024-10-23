package com.aditya.color_app.MVVM

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import com.aditya.color_app.RoomSetup.MyRoomDatabase
import com.aditya.color_app.RoomSetup.myEntity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


class Repositry @Inject constructor(private val db: MyRoomDatabase)  {

private val _getColors=MutableStateFlow<List<myEntity>>(emptyList())
val getColors:StateFlow<List<myEntity>> =_getColors.asStateFlow()

private val _getCloudData=MutableStateFlow<List<myEntity>>(emptyList())
val getCloudData:StateFlow<List<myEntity>> =_getCloudData.asStateFlow()






 suspend fun getData(){

 _getColors.value =   db.myDao.getData()

 }

 suspend fun insertData(data:myEntity){
     db.myDao.insertData(data)
 }

 fun deleteData(data: myEntity){

  db.myDao.deleteData(data)

 }

 suspend fun uploadToCloud(){
  val mdb=Firebase.firestore
  getData()
  val data=getColors.value

  for (i in data){

   mdb.collection("Colors").document().set(i).addOnCompleteListener {
    deleteData(i)
   }.addOnFailureListener {
    Log.i("KELa",it.message.toString())
   }

  }

  db.clearAllTables()

 }




 fun getCloudData(){

  val db=Firebase.firestore
  db.collection("Colors").addSnapshotListener { value, error ->

   val data=value?.toObjects(myEntity::class.java)
   _getCloudData.value=data!!

  }

 }



}