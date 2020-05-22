package com.laon.snack_spring.controller.request

import com.laon.snack_spring.common.define.ApiErrorCode
import com.laon.snack_spring.common.exception.ApiException
import com.laon.snack_spring.controller.common.BaseController
import com.laon.snack_spring.service.request.RequestService
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/requests")
class RequestController(
        private val requestService: RequestService
): BaseController() {

    @CrossOrigin
    @RequestMapping(method = [RequestMethod.GET])
    fun readRequestList(
            res: HttpServletResponse
    ): Map<String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()

        try {
            returnMap["result"] = 1
            returnMap["data"] = requestService.readRequestList()
        } catch (e: ApiException) {
            res.status = e.status
            returnMap["result"] = 0
            returnMap["resultCode"] = e.code
            returnMap["resultMsg"] = e.msg
        } catch (e: Exception) {
            res.status = 500
            returnMap["result"] = 0
            returnMap["resultCode"] = ApiErrorCode.UNKNOWN.code
            returnMap["resultMsg"] = ApiErrorCode.UNKNOWN.msg
        }

        return returnMap
    }

    @CrossOrigin
    @RequestMapping("request", method = [RequestMethod.GET])
    fun readRequest(
            @RequestParam id: Long,
            res: HttpServletResponse
    ): Map<String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()

        try {
            returnMap["result"] = 1
            returnMap["data"] = requestService.readRequest(id)
        } catch (e: ApiException) {
            res.status = e.status
            returnMap["result"] = 0
            returnMap["resultCode"] = e.code
            returnMap["resultMsg"] = e.msg
        } catch (e: Exception) {
            res.status = 500
            returnMap["result"] = 0
            returnMap["resultCode"] = ApiErrorCode.UNKNOWN.code
            returnMap["resultMsg"] = ApiErrorCode.UNKNOWN.msg
        }

        return returnMap
    }

    @CrossOrigin
    @RequestMapping(method = [RequestMethod.POST])
    fun createRequest(
            @RequestParam user_id: Long,
            @RequestParam model: String,
            @RequestParam brand: String,
            @RequestParam quantity: Long,
            res: HttpServletResponse
    ): Map<String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()

        try {
            returnMap["result"] = 1
            returnMap["data"] = requestService.createRequest(user_id, model, brand, quantity)
        } catch (e: ApiException) {
            res.status = e.status
            returnMap["result"] = 0
            returnMap["resultCode"] = e.code
            returnMap["resultMsg"] = e.msg
        } catch (e: Exception) {
            res.status = 500
            returnMap["result"] = 0
            returnMap["resultCode"] = ApiErrorCode.UNKNOWN.code
            returnMap["resultMsg"] = ApiErrorCode.UNKNOWN.msg
        }

        return returnMap
    }

}