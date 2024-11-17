package com.example.doljnikprod;

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import java.util.Date

data class Transaction(
    val name: String,
    val money: Int,
    val description: String,
    val date: String = ""
)

class Person(val name: String) {
    // Map (key: person name, mean: dips)
    val debtors = mutableMapOf<String, Int>()
    val history = mutableListOf<Transaction>()

    fun addPerson(name: String) {
        debtors[name] = 0
    }

    @SuppressLint("SimpleDateFormat")
    fun addDebtPlus(person: String, debt: Int, description: String) {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        debtors[person] = debtors[person]!! + debt
        history.add(0, Transaction(person, debt, description, currentDate))
    }

    @SuppressLint("SimpleDateFormat")
    fun addDebtMinus(person: String, debt: Int, description: String) {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        debtors[person] = debtors[person]!! - debt
        history.add(0, Transaction(person, -debt, description, currentDate))
    }

}