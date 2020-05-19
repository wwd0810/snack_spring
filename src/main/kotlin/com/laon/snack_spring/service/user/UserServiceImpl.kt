package com.laon.snack_spring.service.user

import com.laon.snack_spring.common.define.ApiErrorCode
import com.laon.snack_spring.common.exception.ApiException
import com.laon.snack_spring.common.lib.logger
import com.laon.snack_spring.entity.common.Paging
import com.laon.snack_spring.entity.user.User
import com.laon.snack_spring.repository.team.TeamJpaRepositoty
import com.laon.snack_spring.repository.user.UserJpaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.lang.Exception

@Service("UserService")
class UserServiceImpl(
        private val userJpaRepository: UserJpaRepository,
        private val teamJpaRepositoty: TeamJpaRepositoty

): UserService {

    companion object {
        private val log = logger()
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



        val user = User(id = null, username = username, nickname = nickname)
        user.team_id = group.id

        userJpaRepository.save(user)

        run {
            returnMap["msg"] = "유저생성이 완료되었습니다."
        }

        return returnMap
    }
}