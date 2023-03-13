package com.example.recycleview.model

import com.github.javafaker.Faker
import java.util.Collections

typealias UsersListener = (users:List<User>)->Unit

class UsersService {
    private var users: MutableList<User> = mutableListOf<User>()

    private val listeners:MutableSet<UsersListener> = mutableSetOf<UsersListener>()

    init {
        val faker = Faker.instance()
        IMAGES.shuffle()
        val generatedUsers: List<User> = (1..100).map {User(
                id = it.toLong(),
                name = faker.name().name(),
                company = faker.company().company(),
                photo = IMAGES[it % IMAGES.size]
            )  }
    }
    fun getUsers():List<User> {  return users  }

    fun deleteUser(user: User){
        val indexToDelete = users.indexOfFirst { it.id==user.id }
        users.removeAt(indexToDelete)
    }

fun moveUser(user: User, moveBy : Int){
    val oldIndex:Int = users.indexOfFirst { it.id ==user.id }
    if (oldIndex==-1) return
    val newIndex:Int = oldIndex+moveBy
    if (newIndex<0||newIndex>=users.size)return
    Collections.swap(users, oldIndex, newIndex)
}

    fun addListener(listener:UsersListener){
        listeners.add(listener)
    }

fun removeListener(listener: UsersListener){
    listeners.remove(listener)
}

    private fun notifyChanges() {

    }


    companion object{
        private val IMAGES = mutableListOf(



        )
    }
}