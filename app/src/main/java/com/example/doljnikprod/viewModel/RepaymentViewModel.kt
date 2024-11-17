package com.example.doljnikprod.viewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.doljnikprod.model.RepaymentModel
import com.example.doljnikprod.Routes

class RepaymentViewModel (private val repaymentModel: RepaymentModel) : ViewModel() {

    fun backToRoom(navController: NavHostController){
        navController.popBackStack()
        navController.navigate(Routes.Room.route)
    }

    fun repay(
        sumStr: String,
        navController: NavHostController,
        description: String
    ) {
        repaymentModel.repay(sumStr, navController,  description)
    }

}


