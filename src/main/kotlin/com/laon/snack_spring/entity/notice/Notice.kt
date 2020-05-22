package com.laon.snack_spring.entity.notice

import com.fasterxml.jackson.annotation.JsonIgnore
import com.laon.snack_spring.entity.common.BaseEntity
import com.laon.snack_spring.entity.team.Team
import com.laon.snack_spring.entity.user.User
import javax.persistence.*

@Table(name = "tbl_notice")
@Entity
data class Notice (
        @get:Id
        @get:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @get:Column
        var title: String,

        @get:Column
        var article: String

) : BaseEntity()  {

}