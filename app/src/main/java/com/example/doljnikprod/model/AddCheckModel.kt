package com.example.doljnikprod.model

import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.example.doljnikprod.Person
import com.example.doljnikprod.Room
import com.example.doljnikprod.Routes

data class AddCheckData(
    val debtStr: String,
    val debtorsNames: MutableList<String>,
    val whoDebts: MutableList<Boolean>,
    val navController: NavHostController,
    val user: Person,
    val description: String
)

fun addCheck(data: AddCheckData, room: Room) {
    val thisDebt = data.debtStr.toIntOrNull()
    if (thisDebt != null) {
        var quantity = 0
        for (w in data.whoDebts) {
            if (w) quantity += 1;
        }

        for (d in data.user.debtors) {
            for (thisPerson in room.users) {
                if (data.debtorsNames.indexOf(d.key) != -1) {
                    if (thisPerson.name == d.key && data.whoDebts[data.debtorsNames.indexOf(
                            d.key
                        )]
                    ) {
                        room.setDebt(
                            data.user,
                            thisPerson,
                            thisDebt / quantity,
                            data.description
                        )
                        break
                    }
                }
            }
        }
        data.navController.popBackStack()
        data.navController.navigate(Routes.Room.route)
    }
}



