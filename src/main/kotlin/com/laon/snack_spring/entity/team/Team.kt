package com.laon.snack_spring.entity.team

import com.laon.snack_spring.entity.common.BaseEntity
import javax.persistence.*

@Table(name = "tbl_team")
@Entity
data class Team (
        @get:Id
        @get:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @get:Column
        var name: String,

        @get:Column
        var color: String

): BaseEntity() {
}