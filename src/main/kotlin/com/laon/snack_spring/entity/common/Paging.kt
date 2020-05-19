package com.laon.snack_spring.entity.common

import org.springframework.data.domain.Page

data class Paging(
        var page: Int,
        var limit: Int = 10
) {

    companion object {
        fun parse(page: Page<*>): Paging {
            val paging = Paging(page.pageable.pageNumber, page.numberOfElements)
            paging.count = page.totalElements

            return paging
        }
    }

//    val offset: Int
//        get() {
//            return page * limit
//        }

    var count: Long = 0
}