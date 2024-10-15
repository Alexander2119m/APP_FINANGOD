package com.example.finan.interfaces.categoriamaestra

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finan.firebase.CategoriaMaestra
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.toObject

class CategoriaMaestraViewModel : ViewModel() {
    private val categoriasCollection = FirebaseFirestore.getInstance().collection("categorias")
    private val _categoriasList = MutableLiveData<List<CategoriaMaestra>>()
    val categoriasList: LiveData<List<CategoriaMaestra>> get() = _categoriasList

    private var listenerRegistration: ListenerRegistration? = null

    fun cargarCategorias() {
        listenerRegistration?.remove()
        listenerRegistration = categoriasCollection.addSnapshotListener { snapshots, error ->
            if (error != null) {
                Log.e("Firestore", "Error al cargar las categorías: ${error.message}")
                return@addSnapshotListener
            }

            val categorias = snapshots?.documents?.mapNotNull { it.toObject<CategoriaMaestra>() }
            _categoriasList.value = categorias ?: emptyList()
        }
    }

    fun agregarCategoria(nuevaCategoria: CategoriaMaestra) {
        val id = nuevaCategoria.id ?: categoriasCollection.document().id
        val categoriaConId = nuevaCategoria.copy(id = id)


        categoriasCollection.document(id).set(categoriaConId)
            .addOnSuccessListener {

            }
            .addOnFailureListener { error ->
                Log.e("Firestore", "Error al agregar la categoría: ${error.message}")
            }
    }

    fun actualizarCategoria(categoriaActualizada: CategoriaMaestra) {
        categoriaActualizada.id?.let { id ->
            categoriasCollection.document(id).set(categoriaActualizada)
                .addOnSuccessListener {

                }
                .addOnFailureListener { error ->
                    Log.e("Firestore", "Error al actualizar la categoría: ${error.message}")
                }
        }
    }

    // Función para eliminar una categoría
    fun eliminarCategoria(idCategoria: String) {
        categoriasCollection.document(idCategoria).delete()
            .addOnSuccessListener {

            }
            .addOnFailureListener { error ->
                Log.e("Firestore", "Error al eliminar la categoría: ${error.message}")
            }
    }

    override fun onCleared() {
        super.onCleared()
        listenerRegistration?.remove()
    }
}
