package com.laon.snack_spring.repository.request

import com.laon.snack_spring.entity.request.Request

import org.springframework.data.jpa.repository.JpaRepository

interface RequestJpaRepository: JpaRepository<Request, Long> {
}