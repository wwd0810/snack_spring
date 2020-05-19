package com.laon.snack_spring.service.user


interface UserService {

    fun readUserList() : Map<String, Any>

    fun readUserPageList(page: Int, size: Int): Map<String, Any>

    fun createUser(username: String, nickname: String, team: Long ) : Map<String, Any>

}