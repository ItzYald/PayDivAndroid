package com.example.doljnikprod

import com.example.doljnikprod.viewModel.AddCheckViewModel
import com.example.doljnikprod.model.AddCheckData

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@SuppressLint("UnrememberedMutableState")
@Composable
fun AddCheckWindow(
    navController: NavHostController,
    room: Room,
    user: Person,
    addCheckViewModel: AddCheckViewModel
) {
    var debtors = remember { mutableMapOf<String, Int>() }


    val debtorsNames = remember { mutableStateListOf<String>() }
    val whoDebts = remember { mutableStateListOf<Boolean>() }

    for (i in room.users) {
        if (user.name == i.name) {
            debtors = i.debtors
        }
    }

    for (i in debtors) {
        if (user.name != i.key) {
            debtorsNames.add(i.key)
            whoDebts.add(false)
        }
    }

    val thisDebtStr = mutableStateOf("")
    val description = mutableStateOf("")


    Column(verticalArrangement = Arrangement.SpaceAround) {

        BackButton {
            addCheckViewModel.goBackToRoom(navController)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(40.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                stringResource(R.string.sum),
                fontSize = 40.sp,
                style = TextStyle(textIndent = TextIndent(20.sp, 20.sp))
            )
        }
        MyTextField(thisDebtStr, KeyboardType.Phone)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(40.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                stringResource(R.string.description),
                fontSize = 40.sp,
                style = TextStyle(textIndent = TextIndent(20.sp, 20.sp))
            )
        }
        MyTextField(description)

        Row {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .size(70.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(modifier = Modifier
                    .size(dimensionResource(R.dimen.standart_button_weight), 60.dp),
                    shape = RoundedCornerShape(20.dp),
                    onClick = {
                        whoDebts.forEachIndexed { i, _ ->
                            whoDebts[i] = true
                        }
                    }) {
                    Text(stringResource(R.string.select_all), fontSize = 20.sp)
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .size(70.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(modifier = Modifier
                    .size(dimensionResource(R.dimen.standart_button_weight), 60.dp),
                    shape = RoundedCornerShape(20.dp),
                    onClick = {
                        whoDebts.forEachIndexed { i, _ ->
                            whoDebts[i] = false
                        }
                    }) {
                    Text(stringResource(R.string.deselect), fontSize = 20.sp)
                }
            }
        }



        LazyColumn(
            modifier = Modifier
                .weight(4f)
                .fillMaxSize()
        ) {
            itemsIndexed(debtorsNames) { i, it ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
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
                        Spacer(modifier = Modifier.size(10.dp))

                        Text(
                            it,
                            fontSize = 30.sp,
                            style = TextStyle(textIndent = TextIndent(5.sp, 5.sp))
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1.5f)
                            .fillMaxSize(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Checkbox(
                            checked = whoDebts[i],
                            onCheckedChange = { whoDebts[i] = it }
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(120.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(modifier = Modifier
                .size(dimensionResource(R.dimen.standart_button_weight), 80.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = {

                    val data = AddCheckData(
                        thisDebtStr.value,
                        debtorsNames,
                        whoDebts,
                        navController,
                        user,
                        description.value
                    )

                    addCheckViewModel.addCheckViewModel(data, room)
                }) {
                Text(stringResource(R.string.add), fontSize = 35.sp)
            }
        }

    }
}
