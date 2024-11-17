package com.example.doljnikprod

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.doljnikprod.viewModel.HistoryViewModel

@Composable
fun HistoryWindow(navController: NavHostController, history: MutableList<Transaction>, historyViewModel: HistoryViewModel) {
    Column(verticalArrangement = Arrangement.SpaceAround) {
        BackButton {
            historyViewModel.goBackToRoom(navController)
        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            items(history) { it ->
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(70.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1.5f)
                            .fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            it.name,
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
                        Text(it.money.toString(), fontSize = 30.sp)
                    }
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxSize(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        IconButton(modifier = Modifier.size(80.dp),
                            onClick = {
                                historyViewModel.goToViewTransaction(navController, it)
                            }) {
                            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "", modifier = Modifier.size(40.dp))
                        }
                    }
                }
            }
        }

    }


}