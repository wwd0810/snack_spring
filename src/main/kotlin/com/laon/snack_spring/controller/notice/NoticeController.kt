package com.laon.snack_spring.controller.notice

import com.laon.snack_spring.common.define.ApiErrorCode
import com.laon.snack_spring.common.exception.ApiException
import com.laon.snack_spring.controller.common.BaseController
import com.laon.snack_spring.service.notice.NoticeService
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/notices")
class NoticeController(
        private val noticeService: NoticeService
) : BaseController() {

    @CrossOrigin
    @RequestMapping( method = [RequestMethod.GET])
    fun readNoticeList(
            res: HttpServletResponse
    ): Map<String, Any> {

        val returnMap: MutableMap<String, Any> = HashMap()

        try {
            returnMap["result"] = 1
            returnMap["data"] = noticeService.readNoticeList()
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
    @RequestMapping( "notice", method = [RequestMethod.GET])
    fun readNotice(
            @RequestParam id: Long,
            res: HttpServletResponse
    ) : Map<String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()

        try {
            returnMap["result"] = 1
            returnMap["data"] = noticeService.readNotice(id)
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
    @RequestMapping( method = [RequestMethod.POST])
    fun createNotice(
            @RequestParam title: String,
            @RequestParam article: String,
            res: HttpServletResponse
    ) : Map<String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()

        try {
            returnMap["result"] = 1
            returnMap["data"] = noticeService.createNotice(title, article)
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