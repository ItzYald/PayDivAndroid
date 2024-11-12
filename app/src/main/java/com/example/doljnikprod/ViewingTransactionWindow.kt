package com.example.doljnikprod

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
fun ViewingTransactionWindow(navController: NavHostController, thisTransaction: MutableState<Transaction>){

    Column () {
        BackButton {
            navController.popBackStack()
            navController.navigate(Routes.History.route)
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
        ){
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    thisTransaction.value.name,
                    fontSize = 30.sp,
                    style = TextStyle(textIndent = TextIndent(5.sp, 5.sp))
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    thisTransaction.value.money.toString(),
                    fontSize = 30.sp,
                    style = TextStyle(textIndent = TextIndent(5.sp, 5.sp))
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                thisTransaction.value.description,
                fontSize = 30.sp,
                style = TextStyle(textIndent = TextIndent(5.sp, 5.sp))
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                thisTransaction.value.date,
                fontSize = 30.sp,
                style = TextStyle(textIndent = TextIndent(5.sp, 5.sp))
            )
        }

    }


}

