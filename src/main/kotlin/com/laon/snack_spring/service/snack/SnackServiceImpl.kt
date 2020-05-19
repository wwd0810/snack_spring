package com.laon.snack_spring.service.snack

import com.laon.snack_spring.common.define.ApiErrorCode
import com.laon.snack_spring.common.exception.ApiException
import com.laon.snack_spring.common.lib.logger
import com.laon.snack_spring.entity.history.History
import com.laon.snack_spring.entity.market.Snack
import com.laon.snack_spring.repository.history.HistoryJpaRepository
import com.laon.snack_spring.repository.snack.SnackJpaRepository
import com.laon.snack_spring.repository.user.UserJpaRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import java.io.BufferedReader
import java.io.File
import java.lang.Exception
import java.nio.file.Path
import javax.imageio.ImageIO
import javax.transaction.Transactional

@Service("SnackService")
class SnackServiceImpl(
        private val snackJpaRepository: SnackJpaRepository,
        private val userJpaRepository: UserJpaRepository,
        private val historyJpaRepository: HistoryJpaRepository,
        @Value("\${upload.path}")
        private val UPLOAD_ROOT: String
): SnackService {

    companion object {
        private val log = logger()
    }


    @Throws(Exception::class)
    override fun readSnackList(
            query: String?
    ): Map <String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()

        val payload: MutableMap<String, Any?> = HashMap()

        run {
            payload["query"] = query
        }

        val snacks: Array<Snack> = if (query != "") {
            snackJpaRepository.findAllByType(query)
        } else {
            snackJpaRepository.findAll().toTypedArray()
        }

        run {
            returnMap["snack"] = snacks
        }

        return returnMap
    }

    @Throws(Exception::class)
    @Transactional
    override fun createSnack(name: String, price: Long, quantity: Long, type: String, remain: Long, img: MultipartFile) {
        val returnMap: MutableMap<String, Any> = HashMap()

        val ROOT = File(Path.of(UPLOAD_ROOT, "profiles").toString())

        if (!ROOT.exists()) {
            ROOT.mkdir()
        }

        val mime = "jpg"

        val imageName = img.originalFilename

        val image = ImageIO.read(img.inputStream)

        val target = File(Path.of(UPLOAD_ROOT, "profiles", "$imageName").toString())

        try {
            val snack = Snack(id = null, name = name, price = price, quantity = quantity, type = type, remain = remain, img_path = imageName)

            snackJpaRepository.save(snack)

            ImageIO.write(image, mime, target)
        } catch (e: Exception) {
            throw ApiException(ApiErrorCode.INVALID_PARAMETER)
        }




    }

    @Throws(Exception::class)
    @Transactional
    override fun createSale(user: Long, snack: String, quantity: String): Map<String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()

        val snackIds = snack.split(",").map { it.toLong() }

        val quantities = quantity.split(",").map { it.toLong() }

        val user = userJpaRepository.findById(user).orElseThrow { throw ApiException(ApiErrorCode.USER_NOT_FOUND) }

        val snacks = snackJpaRepository.findAllById(snackIds)

            if (snacks.size == quantities.size) {
                snacks.forEachIndexed { index, snack ->
                    val history = History(quantity = quantities[index], id = null)

                    if (snack.remain - quantities[index] >= 0) {
                        snack.remain -= quantities[index]

                        snackJpaRepository.save(snack)

                        history.user_id = user.id
                        history.snack_id = snack.id

                        historyJpaRepository.save(history)
                    } else {
                        throw ApiException(ApiErrorCode.SNACK_NOT_REMAIN)
                    }
                }
            } else {
                throw ApiException(ApiErrorCode.INVALID_PARAMETER)
            }


//        { throw ApiException(ApiErrorCode.USER_NOT_FOUND) }

        run {
            returnMap["msg"] = "구매 등록이 성공하였습니다."
        }

        return returnMap
    }

    @Throws(Exception::class)
    @Transactional
    override fun modifySnack(id: Long, price: Long, quantity: Long, remain: Long): Map<String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()

        val snack = snackJpaRepository.findById(id).orElseThrow { throw ApiException(ApiErrorCode.SNACK_NOT_FOUND) }

        try {
            snack.price = price
            snack.quantity = quantity
            snack.remain = remain
        } catch (e: Exception) {
            throw ApiException(ApiErrorCode.INVALID_PARAMETER)
        }

        run {
            returnMap["msg"] = "상품 업데이트가 성공하였습니다."
        }

        return returnMap
    }

}