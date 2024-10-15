package com.example.finan.firebase

data class Gastos(
    val detalle: String = "",
    val precio: Double = 0.0,
    val unidad: String = "",
    val id: String? = null
) {

    fun toMap(): MutableMap<String, Any> {
        val result = mutableMapOf<String, Any>(
            "detalle" to this.detalle,
            "precio" to this.precio,
            "unidad" to this.unidad
        )
        this.id?.let {
            result["id"] = it
        }
        return result
    }


    companion object {
        fun fromMap(map: Map<String, Any>): Gastos {
            return Gastos(
                detalle = map["detalle"] as String,
                precio = map["precio"] as Double,
                unidad = map["unidad"] as String,
                id = map["id"] as String?
            )
        }
    }
}
