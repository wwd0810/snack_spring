package com.laon.snack_spring.repository.notice

import com.laon.snack_spring.entity.notice.Notice

import org.springframework.data.jpa.repository.JpaRepository

interface NoticeJpaRepository: JpaRepository<Notice, Long> {
}