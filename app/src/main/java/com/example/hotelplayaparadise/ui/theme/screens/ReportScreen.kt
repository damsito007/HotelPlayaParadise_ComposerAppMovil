package com.example.hotelplayaparadise.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun ReportScreen(navController: NavHostController) {
    // Pantalla de Informe de Satisfacción del Cliente
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Informe de MongoDB",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Contenido desplazable con LazyColumn
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            item {
                ReportSection(
                    title = "Facturas",
                    content = "Aquí se muestra el contenido de las facturas con la API."
                )
                ReportSection(
                    title = "Reservaciones",
                    content = "Aquí se muestra el contenido de las reservaciones con la API."
                )
                ReportSection(
                    title = "Comentarios",
                    content = "Aquí se muestran los comentarios de los clientes con la API."
                )
                ReportSection(
                    title = "Habitaciones",
                    content = "Aquí se muestra la información de las habitaciones con la API."
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para volver, fijo en la parte inferior
        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47A1))
        ) {
            Text("Volver", color = Color.White)
        }
    }
}

@Composable
fun ReportSection(title: String, content: String) {
    var expanded by remember { mutableStateOf(false) } // Estado para controlar si está desplegado o no

    // Contenedor para la sección del menú
    Column(modifier = Modifier.fillMaxWidth()) {
        // Título del menú desplegable
        TextButton(
            onClick = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }

        // Contenido del menú desplegable
        if (expanded) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = content) // Aquí se reemplazaría con los datos reales de la API
                }
            }
        }
    }
}
@Composable
fun KeyIndicator(title: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(Color(0xFFE0F7FA), shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
            .size(100.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold))
    }
}

@Composable
fun DataRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
        Text(text = value, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
    }
}

// Función de previsualización
@Preview(showBackground = true)
@Composable
fun ReportScreenPreview() {
    val navController = rememberNavController()
    ReportScreen(navController = navController)
}