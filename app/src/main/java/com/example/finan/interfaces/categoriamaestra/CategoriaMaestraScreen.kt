package com.example.finan.interfaces.categoriamaestra
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finan.firebase.CategoriaMaestra

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriaMaestraScreen(
    categoriaMaestraViewModel: CategoriaMaestraViewModel = viewModel(),
    navController: NavHostController // Agregado para la navegación
) {
    LaunchedEffect(Unit) {
        categoriaMaestraViewModel.cargarCategorias()
    }

    var nombreCategoria by remember { mutableStateOf("") }
    var idCategoriaSeleccionada by remember { mutableStateOf<String?>(null) }

    val categoriasList by categoriaMaestraViewModel.categoriasList.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Agregar/Editar Categoría", style = MaterialTheme.typography.headlineMedium)

        TextField(
            value = nombreCategoria,
            onValueChange = { nombreCategoria = it },
            label = { Text("Nombre de la Categoría") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (nombreCategoria.isNotBlank()) {
                    if (idCategoriaSeleccionada == null) {
                        categoriaMaestraViewModel.agregarCategoria(
                            CategoriaMaestra(
                                nombre = nombreCategoria
                            )
                        )
                    } else {
                        categoriaMaestraViewModel.actualizarCategoria(
                            CategoriaMaestra(
                                nombre = nombreCategoria,
                                id = idCategoriaSeleccionada
                            )
                        )
                    }

                    nombreCategoria = ""
                    idCategoriaSeleccionada = null
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (idCategoriaSeleccionada == null) "Agregar Categoría" else "Actualizar Categoría")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Lista de Categorías", style = MaterialTheme.typography.headlineMedium)

        LazyColumn {
            items(categoriasList) { categoria ->
                CategoriaItem(
                    categoria = categoria,
                    onEdit = {
                        nombreCategoria = categoria.nombre
                        idCategoriaSeleccionada = categoria.id
                    },
                    onDelete = {
                        categoriaMaestraViewModel.eliminarCategoria(categoria.id ?: "")
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate("homeConexion") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("Regresar al Menú")
        }
    }
}

@Composable
fun CategoriaItem(
    categoria: CategoriaMaestra,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = categoria.nombre, modifier = Modifier.weight(1f))

        IconButton(onClick = onEdit) {
            Icon(Icons.Default.Edit, contentDescription = "Editar")
        }

        IconButton(onClick = onDelete) {
            Icon(Icons.Default.Delete, contentDescription = "Eliminar")
        }
    }
}

