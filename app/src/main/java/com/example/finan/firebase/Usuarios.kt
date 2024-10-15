package com.example.finan.firebase

data class Usuarios(
    val id: String? = null,
    val email: String = "",
    val password: String = "",
    val name: String = ""
) {
    fun toMap(): MutableMap<String, Any> {
        val result = mutableMapOf<String, Any>(
            "email" to this.email,
            "password" to this.password,
            "name" to this.name
        )
        this.id?.let {
            result["id"] = it
        }
        return result
    }

    companion object {
        fun fromMap(map: Map<String, Any>): Usuarios {
            return Usuarios(
                id = map["id"] as String?,
                email = map["email"] as String,
                password = map["password"] as String,
                name = map["name"] as String
            )
        }
    }
}
