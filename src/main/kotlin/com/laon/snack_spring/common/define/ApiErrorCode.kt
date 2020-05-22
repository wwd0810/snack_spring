package com.laon.snack_spring.common.define

enum class ApiErrorCode(
        val code: String,
        val msg: String
) {
    // Snack
    SNACK_NOT_FOUND("api.error.snack_not_found", "간식정보를 찾을 수 없습니다."),
    SNACK_NOT_REMAIN("api.error.snack_not_remain", "품절된 상품입니다."),

    // User
    USER_NOT_FOUND("api.error.user_not_found", "회원을 찾을 수 없습니다."),
    USER_PASS_INVALID("api.error.user_pass_invalid", "비밀번호가 맞지 않습니다."),

//    Team

    TEAM_NOT_FOUND("api.error.team_not_found", "팀을 찾을 수가 없습니다."),

//    notice
    NOTICE_NOT_FOUND("api.error.notice_not_found", "공지사항을 찾을 수가 없습니다."),

    //    notice
    REQUEST_NOT_FOUND("api.error.request_not_found", "을 찾을 수가 없습니다."),


    //COMMON
    INVALID_PARAMETER("api.error.invalid_parameter", "파라미터가 잘못되었습니다."),

    PAGE_NOT_FOUND("api.error.page_not_found", "페이지를 찾을 수 없습니다."),

    METHOD_NOT_ALLOWED("api.error.method_not_allowed", "지원하지 않는 요청 메소드입니다."),

    CLIENT_ABORT("api.error.client_abort", "사용자가 연결을 종료했습니다."),

    IMAGE_NOT_FOUND("api.error.image_not_found", "이미지를 찾을 수가 없니다."),

    UNKNOWN("api.error.unknown", "알 수 없는 에러입니당.");

    override fun toString(): String {
        return this.code
    }
}