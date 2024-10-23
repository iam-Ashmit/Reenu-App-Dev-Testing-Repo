package com.aditya.color_app.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aditya.color_app.MVVM.ColorViewModel
import com.aditya.color_app.RoomSetup.myEntity
import java.sql.Date
import java.sql.Timestamp

@Composable
fun Home(viewModel: ColorViewModel, navController:NavHostController){


    Scaffold(
        topBar = {
            TopBar(viewModel)
        },
        floatingActionButton = @androidx.compose.runtime.Composable {


            ExtendedFloatingActionButton(onClick = {
                navController.navigate("addColors") },
                shape = RoundedCornerShape(50),
                containerColor = Color.Blue.copy(alpha = 0.6f)


            ) {
                Text(text = "Add Color")
                Spacer(modifier = Modifier.width(6.dp))
                Icon(imageVector = Icons.Default.Add, contentDescription = null)

            }

        }


    ) {innerPadding->

        Column(modifier= Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Color.White)) {

            LaunchedEffect(true) {
                viewModel.getCloudData()

            }

            val data=viewModel.getCloudData .collectAsState()


            LazyVerticalGrid(GridCells.Fixed(2) ) {
                items(data.value){
                    showData(it)
                }
            }





        }
    }


}
@Composable
fun showData(data: myEntity) {

    Box (modifier = Modifier
        .width(150.dp)
        .height(150.dp)
        .padding(6.dp)
        .clip(RoundedCornerShape(10))
        .background(
            color = Color((android.graphics.Color.parseColor("#${data.colorName}")))
        ),

    ){

        val timestamp = Timestamp(data.time)
        val date = Date(timestamp.time)
        
        
        

        Column(modifier = Modifier.padding(12.dp)) {

            Text(text = "#${data.colorName}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White)
            Divider(modifier = Modifier.width(100.dp),
                color = Color.White)
        }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End) {

            Text(text = "Create at",
                fontSize = 20.sp)
            Text(text = date.toString(),
                fontSize = 24.sp)

        }
 
        


    }


}