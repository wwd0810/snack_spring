package com.laon.snack_spring.repository.snack

import com.laon.snack_spring.entity.market.Snack
import org.springframework.data.jpa.repository.JpaRepository

interface SnackJpaRepository : JpaRepository<Snack, Long>{

    fun findAllByType(type: String?): Array<Snack>

//    fun findAllById(ids: List<Long>): Array<Snack>

}