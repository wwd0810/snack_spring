package com.laon.snack_spring.entity.request

import com.fasterxml.jackson.annotation.JsonIgnore
import com.laon.snack_spring.entity.common.BaseEntity
import com.laon.snack_spring.entity.user.User
import javax.persistence.*

@Table(name = "tbl_request")
@Entity
data class Request (

        @get:Id
        @get:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @get:Column
        var model: String,

        @get:Column
        var brand: String,

        @get:Column
        var quantity: Long

) : BaseEntity() {
        @get:Column
        @JsonIgnore
        var user_id: Long? = null

        @get:OneToOne
        @get:JoinColumn(name = "user_id", insertable = false, updatable = false)
        var user: User? = null
}