package com.example.doljnikprod

import android.os.Parcel
import android.os.Parcelable

class Room(val id: Int, val name: String, val password: String, val creatorName: String) : Parcelable  {
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

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeString(password)
        dest.writeString(creatorName)
    }

    companion object CREATOR : Parcelable.Creator<Room> {
        override fun createFromParcel(parcel: Parcel): Room? {
            return parcel.readString()
                ?.let { Room(parcel.readInt(), it, parcel.readString()!!, parcel.readString()!!) }
        }
        override fun newArray(size: Int): Array<Room?> {
            return arrayOfNulls(size)
        }
    }

}