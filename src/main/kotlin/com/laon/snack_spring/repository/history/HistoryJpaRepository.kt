package com.laon.snack_spring.repository.history

import com.laon.snack_spring.entity.history.History

import org.springframework.data.jpa.repository.JpaRepository

interface HistoryJpaRepository: JpaRepository<History , Long> {
    fun findAllByUser_idAndPayment(id: Long, payment: Boolean): Array<History>
}