package ru.practicum.android.diploma.network.dto.responses

open class Response {
    companion object{
        const val SUCCESS = 200
        const val FAILURE = 500
    }
    var resultCode = 0
}
