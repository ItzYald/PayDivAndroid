package com.example.doljnikprod

import com.example.doljnikprod.viewModel.AddCheckViewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doljnikprod.model.AddCheckModel
import com.example.doljnikprod.model.RepaymentModel
import com.example.doljnikprod.view.AddCheckWindow
import com.example.doljnikprod.viewModel.HistoryViewModel
import com.example.doljnikprod.viewModel.RepaymentViewModel

class MainClass {

    private val rooms = mutableListOf<Room>()

    private val roomIndex: MutableState<Int>

    private var user = Person("Петя")

    init {
        rooms.add(Room(0, "должники лины ", "lol", user.name))
        rooms.add(Room(rooms.size - 1, "должники лины2", "lalka", user.name))
        rooms[0].addPerson("Вася")
        rooms[0].addPerson("Геннадий")
        rooms[0].addPerson("Лина")
        rooms[0].addPerson("Вася Пупкин")
        rooms[0].addPerson("Джастин Бибер")
        rooms[0].addPerson("Т банк")
        rooms[0].addPerson("Максим")
        rooms[0].addPerson("Бланк")
        rooms[0].addPerson("Илья")
        rooms[0].addPerson("Илья2")
        rooms[0].addPerson("Илья3")
        rooms[0].addPerson("Илья4")

        rooms[1].addPerson("фыва")
        rooms[1].addPerson("ячсм")
        rooms[1].addPerson("ывап")
        roomIndex = mutableStateOf(0)
    }

    @Composable
    fun MainFunc() {
        val navController = rememberNavController()

        val addCheckModel = AddCheckModel()
        val addCheckViewModel = AddCheckViewModel(addCheckModel)

        val historyViewModel = HistoryViewModel()

        val repaymentModel = RepaymentModel(rooms[roomIndex.value], user, mutableStateOf<Person>(rooms[0].users[1]))
        val repaymentViewModel = RepaymentViewModel(repaymentModel)

        NavHost(navController = navController, startDestination = Routes.ListRooms.route) {
            composable(Routes.Room.route) {

                var userIndex: Int = 0
                for ((i, u) in rooms[roomIndex.value].users.withIndex()) {
                    if (u.name == user.name) {
                        userIndex = i
                        break
                    }
                }
                user = rooms[roomIndex.value].users[userIndex]
                RoomWindow(navController, rooms[roomIndex.value], user, repaymentModel.userGivedDebt)
            }
            composable(Routes.ListRooms.route) {
                ListRoomsWindow(navController, rooms, roomIndex)
            }
            composable(Routes.AddCheck.route) {
                AddCheckWindow(navController, rooms[roomIndex.value], user, addCheckViewModel)
            }
            composable(Routes.SignIn.route) {
                SignInWindow(navController)
            }
            composable(Routes.SignUp.route) {
                SignUpWindow(navController)
            }
            composable(Routes.CreateRoom.route) {
                CreateRoomWindow(navController, rooms, user.name)
            }
            composable(Routes.History.route) {
                HistoryWindow(navController, user.history, historyViewModel)
            }
            composable(Routes.Repayment.route) {
                repaymentModel.room = rooms[roomIndex.value]
                repaymentModel.userRepairer = user
                RepaymentWindow(
                    navController,
                    repaymentViewModel
                )
            }
            composable(Routes.JoinToRoom.route) {
                JoinToRoomWindow(navController, rooms)
            }
            composable(Routes.StartWindow.route) {
                StartWindow(navController)
            }
            composable(Routes.ViewingTransaction.route) {
                ViewingTransactionWindow(navController, historyViewModel.transaction)
            }
        }
    }
}