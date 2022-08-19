package com.revature.daggerconductorexample.domain

import com.revature.daggerconductorexample.data.User
import com.revature.daggerconductorexample.presentation.di.scope.PerActivity
import javax.inject.Inject

interface UserRepository {

    val userList:MutableList<User>

    fun getUsers():List<User>
    fun createUser(name:String,email:String):Int

    fun getUser(id:Int):User?
}
@PerActivity
class UserDataRepository @Inject constructor():UserRepository{
    override val userList: MutableList<User> = mutableListOf()

    override fun getUsers() = userList
    override fun createUser(name: String, email: String):Int {
        val user = User(userList.size,name,email)
        addUser(user)
        return user.id
    }

    private fun addUser(user:User) {
        userList.add(user)
    }

    override fun getUser(id: Int): User? {
        return userList.getOrNull(id)
    }

}