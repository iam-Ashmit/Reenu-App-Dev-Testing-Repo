package com.aditya.color_app.Screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aditya.color_app.MVVM.ColorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun TopBar(viewModel: ColorViewModel) {



  TopAppBar(

      colors = TopAppBarDefaults.topAppBarColors(Color.Blue.copy(alpha = 0.3f)),
      title = {
     Row {
         Text(text = "Kela")
     }
      },

      actions = {
          Row{
              Text(text = "Color -App"
              , fontSize = 25.sp,
                  modifier = Modifier.padding(start = 20.dp))
          }
          TopBarLayout(viewModel)
      }

  )

}

@Composable
fun TopBarLayout(viewModel: ColorViewModel) {
LaunchedEffect(true) {
    viewModel.getData()
}

    val data=viewModel.getData.collectAsState()




    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {



        Box(
            modifier = Modifier
                .width(100.dp)
                .height(50.dp)

                .clip(RoundedCornerShape(60))
                .clickable { }
                .background(Color.Gray),
            contentAlignment = Alignment.Center
            ) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(text = data.value.size.toString(),
                   fontSize = 20.sp
                    )

                Spacer(modifier = Modifier.width(6.dp))

                Icon(imageVector = Icons.Filled.Refresh, contentDescription =null,
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .clickable {
                            viewModel.uploadToCloud()
                            viewModel.getData()
                        }
                        .animateContentSize()

                )

            }


        }


    }

}