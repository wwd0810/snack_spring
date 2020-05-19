package com.laon.snack_spring.service.snack

import org.springframework.web.multipart.MultipartFile

interface SnackService {

    fun readSnackList(query: String?) : Map<String, Any>

    fun createSnack(name: String, price: Long, quantity: Long, type: String, remain: Long, img: MultipartFile)

    fun createSale(user: Long, snack: String, quantity: String) : Map<String, Any>

    fun modifySnack(id: Long, price: Long, quantity: Long, remain: Long) : Map<String, Any>


}