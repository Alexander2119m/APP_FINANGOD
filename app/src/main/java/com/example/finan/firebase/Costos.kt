package com.example.finan.firebase

data class Costos(
    val detalle: String = "",
    val idCategoria: String = "",
    val precio: Double = 0.0,
    val id: String? = null
) {

    fun toMap(): Map<String, Any> {
        val result = mutableMapOf<String, Any>(
            "detalle" to this.detalle,
            "idCategoria" to this.idCategoria,
            "precio" to this.precio
        )
        id?.let {
            result["id"] = it
        }
        return result
    }

    companion object {

        fun fromMap(map: Map<String, Any>): Costos {
            return Costos(
                detalle = map["detalle"] as String,
                idCategoria = map["idCategoria"] as String,
                precio = map["precio"] as Double,
                id = map["id"] as String?
            )
        }
    }
}

//Solo me sale el detalle y la categoria
//Me gustaria que categoria tenga opciones desplejable en esa seccion
//Las opciones salen de la data class CategoriaMaestra
//Ademas falto lo mas importante el precio