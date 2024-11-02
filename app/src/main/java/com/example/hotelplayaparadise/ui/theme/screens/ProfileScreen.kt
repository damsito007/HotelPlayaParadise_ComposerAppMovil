package com.example.hotelplayaparadise.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hotelplayaparadise.R


@Composable
fun ProfileScreen(navController: NavHostController) {
    // Pantalla de Perfil mejorada
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Brush.verticalGradient(listOf(Color(0xFF0D47A1), Color(0xFF6A1B9A)))), // Fondo gradiente
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top // Organiza elementos desde la parte superior
    ) {
        // Imagen de perfil circular
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Reemplaza con un recurso de imagen válido
            contentDescription = "Imagen de perfil",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nombre de usuario
        Text(
            text = "Juan Pérez", // Reemplaza con el nombre del usuario
            style = MaterialTheme.typography.headlineMedium.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Email del usuario
        Text(
            text = "juan.perez@example.com", // Reemplaza con el correo del usuario
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.LightGray)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botones de acciones: Editar perfil y Cerrar sesión
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Botón de editar perfil
            Button(
                onClick = { /* Acción para editar perfil */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5))
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Editar perfil", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Editar Perfil", color = Color.White)
            }

            // Botón de cerrar sesión
            Button(
                onClick = { /* Acción para cerrar sesión */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
            ) {
                Icon(Icons.Default.ExitToApp, contentDescription = "Cerrar sesión", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Cerrar Sesión", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botón para volver a la pantalla principal
        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47A1))
        ) {
            Text(text = "Volver", color = Color.White)
        }
    }
}