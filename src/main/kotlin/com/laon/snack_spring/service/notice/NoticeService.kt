package com.laon.snack_spring.service.notice

interface NoticeService {

    fun readNoticeList () : Map<String, Any>

    fun readNotice (id: Long) : Map<String, Any>

    fun createNotice (title: String, article: String) : Map<String, Any>

}