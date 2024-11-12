package com.example.doljnikprod

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainClass {

    private val rooms = mutableListOf<Room>()

    private val thisRoomIndex: MutableState<Int>

    private var userGivedDebt : MutableState<Person>

    private var thisTransaction: MutableState<Transaction>

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
        thisRoomIndex = mutableStateOf(0)
        userGivedDebt = mutableStateOf<Person>(rooms[0].users[1])
        thisTransaction = mutableStateOf(Transaction("", 0, "", ""))
    }

    @Composable
    fun MainFunc() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routes.ListRooms.route) {

            composable(Routes.Room.route) {
                var userIndex: Int = 0
                for ((i, u) in rooms[thisRoomIndex.value].users.withIndex()) {
                    if (u.name == user.name) {
                        userIndex = i
                        break
                    }
                }
                user = rooms[thisRoomIndex.value].users[userIndex]
                RoomWindow(navController, rooms[thisRoomIndex.value], user, userGivedDebt)
            }
            composable(Routes.ListRooms.route) {
                ListRoomsWindow(navController, rooms, thisRoomIndex)
            }
            composable(Routes.AddCheck.route) {
                AddCheckWindow(navController, rooms[thisRoomIndex.value], user)
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
                HistoryWindow(navController, user.history, thisTransaction)
            }
            composable(Routes.Repayment.route) {
                RepaymentWindow(navController, rooms[thisRoomIndex.value], userGivedDebt.value, user)
            }
            composable(Routes.JoinToRoom.route) {
                JoinToRoomWindow(navController, rooms)
            }
            composable(Routes.StartWindow.route) {
                StartWindow(navController)
            }
            composable(Routes.ViewingTransaction.route) {
                ViewingTransactionWindow(navController, thisTransaction)
            }
        }
    }
}