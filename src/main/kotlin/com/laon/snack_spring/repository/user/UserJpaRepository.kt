package com.laon.snack_spring.repository.user

import com.laon.snack_spring.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*


interface UserJpaRepository: JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {

    fun findByNickname(nickname: String ): Optional<User>

}