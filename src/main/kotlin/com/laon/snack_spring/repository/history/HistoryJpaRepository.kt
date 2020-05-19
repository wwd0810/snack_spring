package com.laon.snack_spring.repository.history

import com.laon.snack_spring.entity.history.History
import org.springframework.data.jpa.repository.JpaRepository

interface HistoryJpaRepository: JpaRepository<History , Long> {

}