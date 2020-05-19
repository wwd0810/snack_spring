package com.laon.snack_spring.repository.team

import com.laon.snack_spring.entity.team.Team
import org.springframework.data.jpa.repository.JpaRepository
interface TeamJpaRepositoty : JpaRepository<Team, Long> {

    fun findByName(query: String) : Array<Team>

}