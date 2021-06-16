package com.coderzheaven.tutorialprojects.models

data class AppServiceResponse<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {

    companion object {

        fun <T> success(data: T?): AppServiceResponse<T> {
            return AppServiceResponse(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String?, data: T?): AppServiceResponse<T> {
            return AppServiceResponse(Status.ERROR, data, msg)
        }

        fun <T> loading(): AppServiceResponse<T> {
            return AppServiceResponse(Status.LOADING, null, null)
        }

    }

}