package com.laon.snack_spring.service.request

import com.laon.snack_spring.common.define.ApiErrorCode
import com.laon.snack_spring.common.exception.ApiException
import com.laon.snack_spring.common.lib.logger
import com.laon.snack_spring.entity.request.Request
import com.laon.snack_spring.repository.request.RequestJpaRepository
import org.springframework.stereotype.Service
import java.lang.Exception

@Service("RequestService")
class RequestServiceImpl(
        private val requestJpaRepository: RequestJpaRepository
) : RequestService {

    companion object {
        val log = logger()
    }

    @Throws(Exception::class)
    override fun readRequestList(): Map<String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()

        val requests = requestJpaRepository.findAll()

        run {
            returnMap["requests"] = requests
        }

        return returnMap
    }

    @Throws(Exception::class)
    override fun readRequest(id: Long): Map<String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()

        val request = requestJpaRepository.findById(id).orElseThrow {throw ApiException(ApiErrorCode.REQUEST_NOT_FOUND)}

        run {
            returnMap["request"] = request
        }

        return returnMap
    }

    @Throws(Exception::class)
    override fun createRequest(user_id: Long, model: String, brand: String, quantity: Long): Map<String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()

        val request = Request(id = null, model = model, brand = brand, quantity = quantity)
        request.user_id = user_id

        requestJpaRepository.save(request)

        run {
            returnMap["msg"] = "success"
        }

        return returnMap
    }

}