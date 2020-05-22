package com.laon.snack_spring.service.notice

import com.laon.snack_spring.common.define.ApiErrorCode
import com.laon.snack_spring.common.exception.ApiException
import com.laon.snack_spring.common.lib.logger
import com.laon.snack_spring.entity.notice.Notice
import com.laon.snack_spring.repository.notice.NoticeJpaRepository
import org.springframework.stereotype.Service
import java.lang.Exception

@Service("NoticeService")
class NoticeServiceImpl(
        private val noticeJpaRepository: NoticeJpaRepository
) : NoticeService {

    companion object {
        private val log = logger()
    }

    @Throws(Exception::class)
    override fun readNoticeList(): Map<String, Any> {
        val returnMap : MutableMap<String, Any> = HashMap()

        val notices = noticeJpaRepository.findAll()

        run {
            returnMap["notices"] = notices
        }

        return returnMap
    }

    @Throws(Exception::class)
    override fun readNotice(id: Long): Map<String, Any> {
        val returnMap : MutableMap<String, Any> = HashMap()

        val notice = noticeJpaRepository.findById(id).orElseThrow { throw ApiException(ApiErrorCode.NOTICE_NOT_FOUND) }

        run {
            returnMap["notice"] = notice
        }

        return returnMap
    }

    @Throws(Exception::class)
    override fun createNotice(title: String, article: String): Map<String, Any> {
        val returnMap : MutableMap<String, Any> = HashMap()

        val notice = Notice(id = null, title = title, article = article)

        noticeJpaRepository.save(notice)

        run {
            returnMap["msg"] = "공지사항 생성이 완료되었습니다."
        }

        return returnMap
    }



}