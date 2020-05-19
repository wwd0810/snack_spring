package com.laon.snack_spring.controller.market

import com.laon.snack_spring.common.define.ApiErrorCode
import com.laon.snack_spring.common.exception.ApiException
import com.laon.snack_spring.controller.common.BaseController
import com.laon.snack_spring.service.snack.SnackService
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/snacks")
class SnackController(
        private val snackService: SnackService
) : BaseController() {

    //   SnackList API
    @CrossOrigin
    @RequestMapping(method = [RequestMethod.GET])
    fun readSnackList(
            @RequestParam(required = false) query: String?,
            res: HttpServletResponse
    ): Map<String, Any> {

        val returnMap: MutableMap<String, Any> = HashMap()

        try {
            returnMap["result"] = 1
            returnMap["data"] = snackService.readSnackList(query)
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

    //    snackCreate API
    @CrossOrigin
    @RequestMapping("create", method = [RequestMethod.POST])
    fun createSnack(
            @RequestParam name: String,
            @RequestParam price: Long,
            @RequestParam quantity: Long,
            @RequestParam type: String,
            @RequestParam remain: Long,
            @RequestParam image: MultipartFile,
            res: HttpServletResponse
    ): Map<String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()

        try {
            res.status = HttpStatus.CREATED.value()
            returnMap["result"] = 1
            returnMap["data"] = snackService.createSnack(name, price, quantity, type, remain, image)
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

    //    SnackBuy API
    @CrossOrigin
    @RequestMapping(method = [RequestMethod.POST])
    fun createPurchaseRequest(
            @RequestParam user: Long,
            @RequestParam snack: String,
            @RequestParam quantity: String,
            res: HttpServletResponse
    ): Map<String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()

        try {
            res.status = HttpStatus.CREATED.value()
            returnMap["result"] = 1
            returnMap["data"] = snackService.createSale(user, snack, quantity)
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
    @RequestMapping(method = [RequestMethod.PATCH])
    fun modifySnack(
            @RequestParam id: Long,
            @RequestParam price: Long,
            @RequestParam quantity: Long,
            @RequestParam remain: Long,
            res: HttpServletResponse
    ) : Map<String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()

        try {
            res.status = HttpStatus.CREATED.value()
            returnMap["result"] = 1
            returnMap["data"] = snackService.modifySnack(id, price,quantity,remain)
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

//    @RequestMapping("/image", method = [RequestMethod.GET], produces = [MediaType.IMAGE_JPEG_VALUE])
//    fun readImage(
//    @RequestParam imageName: String,
//    res: HttpServletResponse
//    ) {
//        val imgFile = ClassPathResource("image/$imageName.jpg")
//
//        res.contentType = MediaType.IMAGE_JPEG_VALUE
//
//        StreamUtils.copy(imgFile.inputStream, res.outputStream)
//    }

}