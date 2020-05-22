package com.laon.snack_spring.repository.history

import com.laon.snack_spring.entity.history.History
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import java.time.LocalDateTime


interface HistoryJpaRepository: JpaRepository<History , Long>, PagingAndSortingRepository<History, Long> {
//    fun findAllByUser_idAndPayment(id: Long, payment: Boolean): Array<History>

    fun findAllByUser_id(user_id: Long, pageable: Pageable): Page<History>

    fun findAllByUser_idAndCreatedDateBetween(user_id: Long, start: LocalDateTime, end: LocalDateTime): List<History>

    fun findAllByUser_idAndPaymentAndCreatedDateBetween(user_id: Long, payment: Boolean, start: LocalDateTime, end: LocalDateTime): List<History>
}