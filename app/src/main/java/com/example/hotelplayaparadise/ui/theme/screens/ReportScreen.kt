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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun ReportScreen(navController: NavHostController) {
    // Pantalla de Informe de Satisfacción del Cliente
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top // Iniciar desde arriba
    ) {
        // Título
        Text(
            text = "Informe de Satisfacción del Cliente",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Indicadores Clave
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            KeyIndicator(title = "Satisfacción Promedio", value = "85%")
            KeyIndicator(title = "Total Encuestas", value = "1200")
            KeyIndicator(title = "Clientes Recurrentes", value = "750")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Gráfico Simulado (puedes integrar una biblioteca de gráficos real)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.DateRange,
                contentDescription = "Gráfico de satisfacción",
                //tint = Color.DarkGray,
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Tabla Resumen (simulación de datos)
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Resumen Mensual:",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            DataRow(label = "Enero", value = "80%")
            DataRow(label = "Febrero", value = "82%")
            DataRow(label = "Marzo", value = "85%")
            DataRow(label = "Abril", value = "88%")
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botón para volver
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