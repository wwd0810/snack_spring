package com.laon.snack_spring.entity.common

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class BaseEntity {

    @get:CreatedDate
    lateinit var createdDate: LocalDateTime

    @get:LastModifiedDate
    var modifiedDate: LocalDateTime? = null

}