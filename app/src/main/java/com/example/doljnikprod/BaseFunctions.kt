package com.example.doljnikprod

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doljnikprod.ui.theme.myBlack

@Composable
fun BackButton(onClick: () -> Unit) {
    Spacer(Modifier.fillMaxWidth().size(5.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(80.dp)
    ) {
        IconButton(modifier = Modifier.size(80.dp),

            onClick = { onClick() }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "", modifier = Modifier.size(35.dp))
        }
    }
}

//@Composable
//fun BackButton(onClick: () -> Unit) {
//    Spacer(Modifier.fillMaxWidth().size(5.dp))
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .size(80.dp)
//    ) {
//        FloatingActionButton(modifier = Modifier.size(80.dp),
//            containerColor = myBlack,
//            onClick = { onClick() }) {
//            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "", modifier = Modifier.size(35.dp))
//        }
//    }
//}

@Composable
fun MyTextField(state: MutableState<String>, keyboardType: KeyboardType=KeyboardType.Text){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(120.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        TextField(
            state.value, modifier = Modifier
                .fillMaxWidth()
                .size(70.dp),
            textStyle = TextStyle(fontSize = 25.sp),
            onValueChange = { state.value = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
        )
    }
}

