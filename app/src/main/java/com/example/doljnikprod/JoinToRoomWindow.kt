package com.example.doljnikprod

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun JoinToRoomWindow(navController: NavHostController, rooms : MutableList<Room>) {

    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Column {
        BackButton {
            navController.popBackStack()
            navController.navigate(Routes.ListRooms.route)
        }
        // Название
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(40.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                "Название",
                fontSize = 40.sp,
                style = TextStyle(textIndent = TextIndent(20.sp, 20.sp))
            )
        }
        MyTextField(login)
        // Пароль
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(40.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                stringResource(R.string.password),
                fontSize = 40.sp,
                style = TextStyle(textIndent = TextIndent(20.sp, 20.sp))
            )
        }
        MyTextField(password, keyboardType = KeyboardType.Password)

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .size(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(80.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(modifier = Modifier.size(350.dp, 80.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = {

                }) {
                Text(stringResource(R.string.add), fontSize = 35.sp)
            }
        }
    }
}