package com.aditya.color_app.Screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aditya.color_app.MVVM.ColorViewModel
import com.aditya.color_app.RoomSetup.myEntity
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

@Composable
fun AddColorScreen(viewModel: ColorViewModel, navController: NavHostController) {

val controller= rememberColorPickerController()
    var colorCode by remember {
        mutableStateOf("")
    }

 Column(modifier = Modifier.fillMaxSize()) {

     HsvColorPicker(
         modifier = Modifier
             .fillMaxWidth()
             .height(450.dp)
             .padding(10.dp),
         controller = controller,
         onColorChanged = {
             colorCode=it.hexCode.toString()

             Log.i("color",it.hexCode.toString())


         }
     )

     Box(modifier = Modifier
         .padding(14.dp)
         .fillMaxWidth()
         .height(70.dp)
         .clip(RoundedCornerShape(20))
         .background(
             color = controller.selectedColor.value
         ),
         contentAlignment = Alignment.Center
     ){
         Text(text = colorCode,
             modifier = Modifier.padding(4.dp)
             )
     }


     Spacer(modifier = Modifier.height(20.dp))

     Button(modifier = Modifier
         .padding(16.dp)
         .fillMaxWidth()
         .height(60.dp),
         colors = ButtonDefaults.buttonColors(controller.selectedColor.value),
         onClick = {

             val data=myEntity(colorCode,System.currentTimeMillis())
             viewModel.insertData(data)
             navController.popBackStack()

         }) {
         Text(text = "Save",
             fontWeight = FontWeight.Bold,
             fontSize = 24.sp)
     }

 }

}