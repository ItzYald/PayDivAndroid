package com.example.doljnikprod

class Room(val id: Int, val name: String, val password: String, creatorName: String) {
    val users = ArrayList<Person>()

    init {
        addPerson(creatorName)
    }

    fun addPerson(personName: String) {
        users.add(Person(personName))

        val size = users.size - 1
        for (i in 0..size) {
            users[users.size - 1].addPerson(users[i].name)
        }
        for (i in 0..size) {
            users[i].addPerson(users[users.size - 1].name)
        }
    }

    fun setDebt(person1: Person, person2: Person, debt: Int, description: String = "") {
        person1.addDebtPlus(person2.name, debt, description)
        person2.addDebtMinus(person1.name, debt, description)
    }

}