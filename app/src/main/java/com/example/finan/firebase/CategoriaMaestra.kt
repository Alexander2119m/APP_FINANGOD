package com.example.finan.firebase

data class CategoriaMaestra(
    val id: String? = null,

    val nombre: String = ""
) {
    fun toMap(): Map<String, Any> {
        val result = mutableMapOf<String, Any>(
            "nombre" to this.nombre
        )
        id?.let {
            result["id"] = it
        }
        return result
    }

    companion object {
        fun fromMap(map: Map<String, Any>): CategoriaMaestra {
            return CategoriaMaestra(
                id = map["id"] as String?,
                nombre = map["nombre"] as String
            )
        }
    }
}

