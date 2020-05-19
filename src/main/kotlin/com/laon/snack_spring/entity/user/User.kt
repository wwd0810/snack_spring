package com.laon.snack_spring.entity.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.laon.snack_spring.entity.common.BaseEntity
import com.laon.snack_spring.entity.team.Team
import javax.persistence.*

@Table(name = "tbl_user")
@Entity
data class User(
        @get:Id
        @get:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @get:Column
        var username: String,

        @get:Column
        var nickname: String


) : BaseEntity() {

        @get:Column
        @JsonIgnore
        var team_id: Long? = null

        @get:OneToOne
        @get:JoinColumn(name = "team_id", insertable = false, updatable = false)
        var team: Team? = null
}