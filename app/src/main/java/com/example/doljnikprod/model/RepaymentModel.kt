package com.example.doljnikprod.model

import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.example.doljnikprod.Person
import com.example.doljnikprod.Routes
import com.example.doljnikprod.Room

class RepaymentModel (var room: Room, var userRepairer : Person, var userGivedDebt: MutableState<Person>) {

    fun repay(
        sumStr: String,
        navController: NavHostController,
        description: String
    ) {
        val sum = sumStr.toIntOrNull()
        if (sum != null) {
            navController.popBackStack()
            navController.navigate(Routes.Room.route)
            room.setDebt(userRepairer, userGivedDebt.value, sum, description)
        }
    }
}



