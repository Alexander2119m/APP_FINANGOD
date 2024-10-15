package com.example.finan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.finan.navegacion.SetupNavGraph
import com.example.finan.interfaces.categoriamaestra.CategoriaMaestraViewModel
import com.example.finan.interfaces.costos.CostosViewModel
import com.example.finan.interfaces.unidadmaestra.UnidadMaestraViewModel
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    private lateinit var categoriaMaestraViewModel: CategoriaMaestraViewModel
    private lateinit var costosViewModel: CostosViewModel
    private lateinit var unidadesViewModel: UnidadMaestraViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        categoriaMaestraViewModel = ViewModelProvider(this).get(CategoriaMaestraViewModel::class.java)
        costosViewModel = ViewModelProvider(this).get(CostosViewModel::class.java)
        unidadesViewModel = ViewModelProvider(this).get(UnidadMaestraViewModel::class.java)

        categoriaMaestraViewModel.cargarCategorias()
        costosViewModel.cargarCostos()
        unidadesViewModel.cargarUnidades()

        setContent {
            val navController = rememberNavController()
            Surface(color = MaterialTheme.colorScheme.background) {
                SetupNavGraph(navController = navController)
            }
        }
    }
}
