package com.laon.snack_spring.service.resource

import java.io.File

interface ResourceService {

    fun findSnackProfile(query: String?) : File

}