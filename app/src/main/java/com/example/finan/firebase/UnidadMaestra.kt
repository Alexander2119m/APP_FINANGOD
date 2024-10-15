package com.example.finan.firebase

data class UnidadMaestra(
    val nombreUnidad: String = "",
    val id: String? = null
) {
    fun toMap(): Map<String, Any> {
        val result = mutableMapOf<String, Any>(
            "nombreUnidad" to this.nombreUnidad
        )
        id?.let {
            result["id"] = it
        }
        return result
    }

    companion object {
        fun fromMap(map: Map<String, Any>): UnidadMaestra {
            return UnidadMaestra(
                nombreUnidad = map["nombreUnidad"] as String,
                id = map["id"] as String?
            )
        }
    }
}
