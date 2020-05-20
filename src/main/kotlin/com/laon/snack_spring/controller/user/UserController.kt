package com.laon.snack_spring.controller.user

import com.laon.snack_spring.common.define.ApiErrorCode
import com.laon.snack_spring.common.exception.ApiException
import com.laon.snack_spring.controller.common.BaseController
import com.laon.snack_spring.service.user.UserService
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/users")
class UserController(
        private val userService: UserService
) : BaseController() {

    //    admin 유저 리스트 불러오기
    @CrossOrigin
    @RequestMapping( "admin", method = [RequestMethod.GET])
    fun readUserPageList(
            @RequestParam(required = false, defaultValue = "0") page: Int,
            @RequestParam(required = false, defaultValue = "10") size: Int,
            res: HttpServletResponse
    ): Map<String, Any> {

        val returnMap: MutableMap<String, Any> = HashMap()

        try {
            returnMap["result"] = 1
            returnMap["data"] = userService.readUserPageList(page, size)
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

    //    ========================================================================================================================
    //    ========================================================================================================================
    //    ========================================================================================================================

    @CrossOrigin
    @RequestMapping("me", method = [RequestMethod.GET])
    fun readUser(
            @RequestParam id: Long,
            res: HttpServletResponse
    ): Map<String, Any> {

        val returnMap: MutableMap<String, Any> = HashMap()

        try {
            returnMap["result"] = 1
            returnMap["data"] = userService.readUser(id)
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
    @RequestMapping(method = [RequestMethod.GET])
    fun readUserList(
            res: HttpServletResponse
    ): Map<String, Any> {

        val returnMap: MutableMap<String, Any> = HashMap()

        try {
            returnMap["result"] = 1
            returnMap["data"] = userService.readUserList()
        } catch (e: ApiException) {
            res.status = e.status
            returnMap["result"] = 0
            returnMap["resultCode"] = e.code
            returnMap["resultMsg"] = e.msg
        } catch (e: Exception) {
            log.error("", e)
            res.status = 500
            returnMap["result"] = 0
            returnMap["resultCode"] = ApiErrorCode.UNKNOWN.code
            returnMap["resultMsg"] = ApiErrorCode.UNKNOWN.msg
        }

        return returnMap

    }

    //  유저생성
    @CrossOrigin
    @RequestMapping(method = [RequestMethod.POST])
    fun createUser(
            @RequestParam username: String,
            @RequestParam nickname: String,
            @RequestParam team: Long,
            res: HttpServletResponse
    ): Map<String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()

        try {
            returnMap["result"] = 1
            returnMap["data"] = userService.createUser(username, nickname, team)
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