package com.example.doljnikprod

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.doljnikprod.viewModel.RepaymentViewModel


@SuppressLint("UnrememberedMutableState")
@Composable
fun RepaymentWindow(
    navController: NavHostController,
    repaymentViewModel: RepaymentViewModel
) {
    val summStr = mutableStateOf("")
    val description = mutableStateOf("")

    Column {
        BackButton() {
            repaymentViewModel.backToRoom(navController)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(40.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                "Сумма",
                fontSize = 40.sp,
                style = TextStyle(textIndent = TextIndent(20.sp, 20.sp))
            )
        }
        MyTextField(summStr, KeyboardType.Phone)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(40.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                "Описание",
                fontSize = 40.sp,
                style = TextStyle(textIndent = TextIndent(20.sp, 20.sp))
            )
        }
        MyTextField(description)


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(120.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(modifier = Modifier
                .size(350.dp, 80.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = {
                    repaymentViewModel.repay(summStr.value,
                        navController,
                        description.value)

                }) {
                Text("Добавить", fontSize = 35.sp)
            }
        }
    }


}
