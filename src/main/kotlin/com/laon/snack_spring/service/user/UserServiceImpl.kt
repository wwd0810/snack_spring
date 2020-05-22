package com.laon.snack_spring.service.user

import com.laon.snack_spring.common.define.ApiErrorCode
import com.laon.snack_spring.common.exception.ApiException
import com.laon.snack_spring.common.lib.logger
import com.laon.snack_spring.entity.common.Paging
import com.laon.snack_spring.entity.history.History
import com.laon.snack_spring.entity.market.Snack
import com.laon.snack_spring.entity.user.User
import com.laon.snack_spring.repository.history.HistoryJpaRepository
import com.laon.snack_spring.repository.snack.SnackJpaRepository
import com.laon.snack_spring.repository.team.TeamJpaRepositoty
import com.laon.snack_spring.repository.user.UserJpaRepository
import org.hibernate.criterion.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO
import org.springframework.stereotype.Service
import java.lang.Exception
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*
import java.time.temporal.TemporalAdjusters.lastDayOfMonth

import kotlin.collections.HashMap

@Service("UserService")
class UserServiceImpl(
        private val userJpaRepository: UserJpaRepository,
        private val teamJpaRepositoty: TeamJpaRepositoty,
        private val historyJpaRepository: HistoryJpaRepository,
        private val snackJpaRepository: SnackJpaRepository

): UserService {

    companion object {
        private val log = logger()
    }

    @Throws(Exception::class)
    override fun readUserSnackList(id: Long, yearMonth: String): Map<String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()
//        val date = LocalDateTime.parse(yearMonth, DateTimeFormatter.ISO_DATE_TIME)

        val tmp = yearMonth.split("-")

        if (tmp.size == 2) {
            try {
                val start = LocalDateTime.of(tmp[0].toInt(), tmp[1].toInt(),1,0,0)
                val end = start.with(lastDayOfMonth())

                val histories = historyJpaRepository.findAllByUser_idAndCreatedDateBetween(id, start, end)

                var payment: Long? = 0

                if (payment != null) {
                    histories.forEach {
                        payment += it.snack?.price!! * it.quantity
                    }
                }

                run {
                    returnMap["monthPayment"] = payment!!
                    returnMap["histories"] = histories.reversed()
                }
            } catch (e: Exception) {
                throw ApiException(ApiErrorCode.INVALID_PARAMETER)
            }

        } else {
            throw ApiException(ApiErrorCode.INVALID_PARAMETER)
        }
//        val now = LocalDateTime.now()`
//        val start = now.withDayOfMonth(1)
//        val end = now.with(lastDayOfMonth())`


         return returnMap
    }

    @Throws(Exception::class)
    override fun login(id: String, password: String): Map<String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()



        val user = userJpaRepository.findByNickname(id).orElseThrow { throw ApiException(ApiErrorCode.USER_NOT_FOUND) }

        if (user.password != password) {
            throw ApiException(ApiErrorCode.USER_PASS_INVALID)
        } else {
            run {
                returnMap["user"] = user
            }
        }



        return returnMap
    }


    @Throws(Exception::class)
    override fun readUserPageList(page: Int, size: Int): Map<String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()

        val pageRequest =  PageRequest.of(page, size)

        val data: Page<User>

        data = userJpaRepository.findAll(pageRequest)

        val paging = Paging.parse(data)

        run {
            returnMap["content"] = data.content
            returnMap["paging"] = paging
        }

        return returnMap
    }

    @Throws(Exception::class)
    override fun readUser(id: Long): Map<String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()

        val user = userJpaRepository.findById(id).orElseThrow { throw ApiException(ApiErrorCode.USER_NOT_FOUND) }

        var sort: Sort = Sort.by("createdDate")

        sort = sort.descending()

        val pageRequest = PageRequest.of(0, 3, sort)

        val data: Page<History>

        data = historyJpaRepository.findAllByUser_id(id, pageRequest)

        val now = LocalDateTime.now()
        val start = now.withDayOfMonth(1)
        val end = now.with(lastDayOfMonth())

        val histories = historyJpaRepository.findAllByUser_idAndPaymentAndCreatedDateBetween(id, false, start, end )

        var payment: Long? = 0

        if (payment != null) {
            histories.forEach {
                payment += it.snack?.price!! * it.quantity
            }
        }

        run {
            returnMap["user"] = user
            returnMap["nowMothPayment"] = payment!!
            returnMap["recentHistory"] = data.content
        }

        return returnMap
    }

    @Throws(Exception::class)
    override fun readUserList(

    ): Map <String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()


        val users = userJpaRepository.findAll()



        run {
            returnMap["users"] = users
        }

        return returnMap
    }

    @Throws(Exception::class)
    override fun createUser(username: String, nickname: String, team: Long): Map<String, Any> {
        val returnMap: MutableMap<String, Any> = HashMap()

        val group =  teamJpaRepositoty.findById(team).orElseThrow { throw ApiException(ApiErrorCode.TEAM_NOT_FOUND) }



        val user = User(id = null, username = username, nickname = nickname, password = "laonpp00")
        user.team_id = group.id

        userJpaRepository.save(user)

        run {
            returnMap["msg"] = "유저생성이 완료되었습니다."
        }

        return returnMap
    }
}