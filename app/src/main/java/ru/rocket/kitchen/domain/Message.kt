package ru.rocket.kitchen.domain

data class Message(
    var date: Long? = null,
    var from: String? = null,
    var message: String? = null
)