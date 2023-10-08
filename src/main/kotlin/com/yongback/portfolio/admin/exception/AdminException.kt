package com.yongback.portfolio.admin.exception

import org.springframework.http.HttpStatus

abstract class AdminException(
    httpStatus: HttpStatus,
    message: String
) : RuntimeException(message) {
    val httpStatus: HttpStatus = httpStatus
}

class AdminBadReqeustException(message: String) : AdminException(
    httpStatus = HttpStatus.BAD_REQUEST,
    message = message
)

class AdminInternalServerErrorException(message: String) : AdminException(
    httpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    message = message
)
