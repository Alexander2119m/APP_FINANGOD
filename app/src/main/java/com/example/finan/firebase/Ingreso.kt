package com.example.finan.firebase

data class Ingreso(
    val monto: Double = 0.0,
    val fecha: String = "",
    val id: String? = null
) {
    fun toMap(): Map<String, Any> {
        val result = mutableMapOf<String, Any>(
            "monto" to this.monto,
            "fecha" to this.fecha
        )
        id?.let {
            result["id"] = it
        }
        return result
    }

    companion object {
        fun fromMap(map: Map<String, Any>): Ingreso {
            return Ingreso(
                monto = map["monto"] as Double,
                fecha = map["fecha"] as String,
                id = map["id"] as String?
            )
        }
    }
}
