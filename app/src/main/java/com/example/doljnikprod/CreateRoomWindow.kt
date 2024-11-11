package com.example.doljnikprod

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


fun createRoom(
    index: Int,
    name: MutableState<String>,
    password: MutableState<String>,
    rooms: MutableList<Room>,
    userName: String
) {
    if (name.value != "") {
        rooms.add(Room(index, name.value, password.value, userName))
    }
}

@Composable
fun CreateRoomWindow(navController: NavHostController, rooms: MutableList<Room>, userName: String) {

    val name = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.SpaceAround) {
        BackButton({
            navController.popBackStack()
            navController.navigate(Routes.ListRooms.route)
        })
        // Название
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(80.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                "Название",
                fontSize = 40.sp,
                style = TextStyle(textIndent = TextIndent(20.sp, 20.sp))
            )
        }
        MyTextField(name)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(80.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                "Пароль",
                fontSize = 40.sp,
                style = TextStyle(textIndent = TextIndent(20.sp, 20.sp))
            )
        }
        MyTextField(password)

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .size(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(80.dp)
        ) {
            Button(modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(20.dp),
                onClick = {
                    createRoom(rooms.size - 1, name, password, rooms, userName)
                    navController.popBackStack()
                    navController.navigate(Routes.ListRooms.route)
                }) {
                Text("Создать", fontSize = 35.sp)
            }
        }

    }


}


