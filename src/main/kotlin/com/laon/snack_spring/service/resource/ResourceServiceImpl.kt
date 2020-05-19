package com.laon.snack_spring.service.resource

import com.laon.snack_spring.common.define.ApiErrorCode
import com.laon.snack_spring.common.exception.ApiException
import com.laon.snack_spring.common.lib.logger
import com.laon.snack_spring.repository.snack.SnackJpaRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File
import java.lang.Exception
import java.nio.file.Path

@Service("ResourceService")
class ResourceServiceImpl (
        private val snackJpaRepository: SnackJpaRepository,
        @Value("\${upload.path}")
        private val UPLOAD_PATH: String
) : ResourceService {

    companion object {
        private val log = logger()
    }

    @Throws(Exception::class)
    override fun findSnackProfile(query: String?): File {
        val file = File(Path.of(UPLOAD_PATH, "/profiles", query).toString())

        if (!file.exists()) {
            throw ApiException(ApiErrorCode.IMAGE_NOT_FOUND)
        }

        return file
    }

}