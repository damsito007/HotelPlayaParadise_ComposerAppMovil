package com.example.hotelplayaparadise.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hotelplayaparadise.R
import com.example.hotelplayaparadise.ui.theme.theme.greenstrong

@Composable
fun LoginScreen(navController: NavHostController) {
    // Main Column layout
    // Main Column layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp) // Add spacing between items
    ) {
        // Title text
        Text(
            text = "Iniciar sesión",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = greenstrong,
            modifier = Modifier.padding(vertical = 50.dp)
        )

        // Username input field
        OutlinedTextField(
            value = "", // Bind this with your viewmodel or state
            onValueChange = {},
            label = { Text("Usuario") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_person_24), // Add your drawable resource
                    contentDescription = "Usuario Icon",
                    tint = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            )
        )

        // Password input field
        OutlinedTextField(
            value = "", // Bind this with your viewmodel or state
            onValueChange = {},
            label = { Text("Contraseña") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_password_24), // Add your drawable resource
                    contentDescription = "Password Icon",
                    tint = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        // Row for "Recuérdame" and "Olvidaste tu contraseña"
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween, // Distribute space between items
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Remember me checkbox
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = false, // Bind this with your state
                    onCheckedChange = {},
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Recuérdame")
            }

            // Forgot password text aligned to the right
            Text(
                text = "¿Olvidaste tu contraseña?",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = greenstrong, // GreenBaby
                modifier = Modifier
                    .padding(top = 8.dp),
                textAlign = TextAlign.End
            )
        }

        // Add some space before the login button to push it down
        Spacer(modifier = Modifier.height(24.dp))

        // Login button with navigation
        Button(
            onClick = { navController.navigate("home") }, // Navigate to home screen
            modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally) // Ajusta el botón al contenido
                .height(48.dp), // Mantener la altura que prefieras
            colors = ButtonDefaults.buttonColors(containerColor = greenstrong)
        ) {
            Text(text = "Iniciar Sesión", color = Color.White)
        }
    }
}

// Preview for the ReportScreen
@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}