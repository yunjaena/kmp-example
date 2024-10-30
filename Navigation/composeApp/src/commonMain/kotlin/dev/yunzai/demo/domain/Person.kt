package dev.yunzai.demo.domain

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Person(
    val name: String,
    val age: Int,
    val address: String
) {
    fun serialize(): String = Json.encodeToString(this)
}
