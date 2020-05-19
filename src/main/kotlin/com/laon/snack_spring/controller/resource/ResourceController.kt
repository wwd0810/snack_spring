package com.laon.snack_spring.controller.resource

import com.laon.snack_spring.common.define.ApiErrorCode
import com.laon.snack_spring.common.exception.ApiException
import com.laon.snack_spring.controller.common.BaseController
import com.laon.snack_spring.service.resource.ResourceService
import org.springframework.core.io.InputStreamResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.FileInputStream
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/resource")
class ResourceController(
        private val resourceService: ResourceService
) :BaseController() {
    //   SnackList API
    @CrossOrigin
    @RequestMapping(method = [RequestMethod.GET])
    fun readSnackList(
            @RequestParam imageName: String,
            res: HttpServletResponse
    ): Any {

        val returnMap: MutableMap<String, Any> = HashMap()

        try {

            val photo = resourceService.findSnackProfile(imageName)

            val resource = InputStreamResource(FileInputStream(photo))

            return ResponseEntity.ok().contentLength(photo.length()).contentType(MediaType.IMAGE_JPEG).body(resource)

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