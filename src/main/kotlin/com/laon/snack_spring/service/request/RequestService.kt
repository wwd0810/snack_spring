package com.laon.snack_spring.service.request

interface RequestService {

    fun readRequestList () : Map<String, Any>

    fun readRequest (id: Long) : Map<String, Any>

    fun createRequest (user_id: Long , model : String, brand: String, quantity: Long) : Map<String, Any>

}