package com.aditya.color_app.MVVM

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.viewModelScope
import com.aditya.color_app.RoomSetup.myEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorViewModel @Inject constructor(private val repo: Repositry):ViewModel() {

    val getData:StateFlow<List<myEntity>> =repo.getColors
    val getCloudData:StateFlow<List<myEntity>> =repo.getCloudData






    fun getData(){
        viewModelScope.launch {
            repo.getData()
        }

    }


  fun insertData(data :myEntity){
     viewModelScope.launch {
         repo.insertData(data)
     }
  }


 fun uploadToCloud(){
viewModelScope.launch {
    repo.uploadToCloud()
}
 }


    fun getCloudData(){
        viewModelScope.launch {
            repo.getCloudData()
        }
    }


}