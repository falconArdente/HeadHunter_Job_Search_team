package ru.practicum.android.diploma.network.data.dto.responses

open class Response {
    companion object {
        const val SUCCESS = 200
        const val FAILURE = 500
        const val NOT_FOUND = 404
    }

    var resultCode = 0
    var errorMessage: String? = null
}
