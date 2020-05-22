package com.laon.snack_spring.service.user




interface UserService {

    fun login (id: String, password: String): Map<String, Any>

    fun readUser(id : Long): Map<String, Any>

    fun readUserSnackList(id: Long, yearMonth: String): Map<String, Any>

    fun readUserList() : Map<String, Any>

    fun readUserPageList(page: Int, size: Int): Map<String, Any>

    fun createUser(username: String, nickname: String, team: Long ) : Map<String, Any>

}