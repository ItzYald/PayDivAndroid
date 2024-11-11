package com.example.doljnikprod

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


fun addCheck(
    thisDebtStr: String,
    debtorsNames: MutableList<String>,
    whoDebts: MutableList<Boolean>,
    navController: NavHostController, thisRoom: Room, user: Person
) {
    val thisdebt = thisDebtStr.toIntOrNull()
    if (thisdebt != null) {
        var quantity = 0
        for (w in whoDebts) {
            if (w) quantity += 1;
        }

        for (d in user.debtors) {
            for (thisPerson in thisRoom.users) {
                if (debtorsNames.indexOf(d.key) != -1) {
                    if (thisPerson.name == d.key && whoDebts[debtorsNames.indexOf(
                            d.key
                        )]
                    ) {
                        thisRoom.setDebt(
                            user,
                            thisPerson,
                            thisdebt / quantity
                        )
                        break
                    }
                }
            }
        }
        navController.popBackStack()
        navController.navigate(Routes.Room.route)
    }
}

@Composable
fun AddCheckWindow(navController: NavHostController, thisRoom: Room, user: Person) {
    var debtors = remember { mutableMapOf<String, Int>() }


    val debtorsNames = remember { mutableStateListOf<String>() }
    debtorsNames.clear()
    val whoDebts = remember { mutableStateListOf<Boolean>() }
    whoDebts.clear()

    for (i in thisRoom.users) {
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

    var thisDebtStr by rememberSaveable { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.SpaceAround) {
        BackButton({
            navController.popBackStack()
            navController.navigate(Routes.Room.route)
        })
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(120.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            TextField(
                thisDebtStr, modifier = Modifier
                    .fillMaxWidth()
                    .size(70.dp),
                textStyle = TextStyle(fontSize = 25.sp),
                onValueChange = { thisDebtStr = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
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
                .size(350.dp, 80.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = {
                    addCheck(thisDebtStr, debtorsNames, whoDebts, navController, thisRoom, user)
                }) {
                Text("Добавить", fontSize = 35.sp)
            }
        }

    }
}
