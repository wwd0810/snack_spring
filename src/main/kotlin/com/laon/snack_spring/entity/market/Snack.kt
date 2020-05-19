package com.laon.snack_spring.entity.market

import com.laon.snack_spring.entity.common.BaseEntity
import javax.persistence.*

@Table(name = "tbl_snack")
@Entity
data class Snack(
        @get:Id
        @get:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @get:Column
        var type: String,

        @get:Column
        var name: String,

        @get:Column
        var price: Long,

        @get:Column
        var quantity: Long,

        @get:Column
        var remain: Long,

        @get:Column
        var img_path: String?

) : BaseEntity() {

}