package com.laon.snack_spring.entity.history

import com.fasterxml.jackson.annotation.JsonIgnore
import com.laon.snack_spring.entity.common.BaseEntity
import com.laon.snack_spring.entity.market.Snack
import com.laon.snack_spring.entity.user.User
import javax.persistence.*

@Table(name = "tbl_history")
@Entity
data class History(
        @get:Id
        @get:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @get:Column
        var quantity: Long,

        @get:Column
        var payment: Boolean

) : BaseEntity() {
    @get:Column
    @JsonIgnore
    var user_id: Long? = null

    @get:Column
    @JsonIgnore
    var snack_id: Long? = null

    @get:OneToOne
    @JsonIgnore
    @get:JoinColumn(name = "user_id", insertable = false, updatable = false)
    var user: User? = null

    @get:OneToOne
    @get:JoinColumn(name = "snack_id", insertable = false, updatable = false)
    var snack: Snack? = null
}