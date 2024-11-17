package com.example.doljnikprod.viewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.doljnikprod.Room
import com.example.doljnikprod.model.AddCheckData

import com.example.doljnikprod.Routes
import com.example.doljnikprod.model.AddCheckModel

class AddCheckViewModel(val addCheckModel : AddCheckModel) : ViewModel() {

    fun goBackToRoom(navController: NavHostController){
        navController.popBackStack()
        navController.navigate(Routes.Room.route)
    }

    fun addCheckViewModel(data: AddCheckData, thisRoom: Room) {
        addCheckModel.addCheck(data, thisRoom)
    }



}

