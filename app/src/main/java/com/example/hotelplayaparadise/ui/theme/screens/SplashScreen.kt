package com.example.hotelplayaparadise.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen( navController: NavHostController) {
    // Simula 3 segundos de espera
    LaunchedEffect(Unit) {
        delay(3000L)
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true } // Elimina pantalla de splash del stack
        }
    }

    // Contenido de la SplashScreen
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.sweepGradient(
                    0.0f to Color(0xFF0D47A1), // Azul oscuro
                    0.5f to Color(0xFF6A1B9A), // Púrpura oscuro
                    1.0f to Color(0xFF0D47A1), // Azul oscuro
                    center = Offset(0.5f, 0.5f)
                )
            ), // Color de fondo
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Barra de carga redonda
            CircularProgressIndicator(
                modifier = Modifier.size(64.dp), // Tamaño del indicador circular
                color = Color.White, // Color de la barra
                strokeWidth = 6.dp // Grosor de la barra de progreso
            )

            // Espaciado entre la barra y el texto
            Spacer(modifier = Modifier.height(16.dp))

            // Texto de carga
            Text(
                text = "Cargando...",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}
// Función de previsualización
@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    // Utilizamos rememberNavController para crear una instancia simulada de NavController
    val navController = rememberNavController()
    SplashScreen(navController = navController)
}