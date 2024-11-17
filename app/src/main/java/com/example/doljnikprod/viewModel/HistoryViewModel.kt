package com.example.doljnikprod.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.doljnikprod.Routes
import com.example.doljnikprod.Transaction

class HistoryViewModel : ViewModel() {

    val transaction = mutableStateOf(Transaction("", 0, "", ""))

    fun goBackToRoom(navController: NavHostController) {
        navController.popBackStack()
        navController.navigate(Routes.Room.route)
    }

    fun goToFilters(navController: NavHostController) {
        navController.popBackStack()
        navController.navigate(Routes.HistoryFilters.route)
    }

    fun goToViewTransaction(navController: NavHostController, transaction : Transaction) {
        this.transaction.value = transaction
        navController.navigate(Routes.ViewingTransaction.route)

    }


}