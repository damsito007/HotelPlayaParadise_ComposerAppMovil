package com.example.hotelplayaparadise.ui.theme.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Define your data model for the API response
data class ReservationData(
    @SerializedName("_id") val id: String,
    @SerializedName("habitacion_id") val habitacionId: String,
    @SerializedName("paquete") val paquete: Paquete,
    @SerializedName("estado_reservacion") val estadoReservacion: String,
    @SerializedName("fechas") val fechas: Fechas,
    @SerializedName("Factura_id") val facturaId: String,
    @SerializedName("Clientes_id") val clientesId: String
)

data class Paquete(
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("costo") val costo: Double,
    @SerializedName("nombre_paquete") val nombrePaquete: String,
    @SerializedName("tiempo_dias") val tiempoDias: Int
)

data class Fechas(
    @SerializedName("llegada") val llegada: String,
    @SerializedName("salida") val salida: String,
    @SerializedName("fecha_reservacion") val fechaReservacion: String
)

// Retrofit service interface to fetch reservation data from the API
interface ApiService {
    @GET("Reservacion/confirmadas")
    fun getReservationData(): Call<List<ReservationData>>
}

// Retrofit instance to interact with the API
object RetrofitInstance {
    private const val BASE_URL = "https://4slz48p3-5000.use2.devtunnels.ms/"
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

@Composable
fun ReportScreen(navController: NavHostController) {
    // Fetch data from the API and display it
    var reservations by remember { mutableStateOf<List<ReservationData>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    // Fetch reservation data on Composable load
    LaunchedEffect(Unit) {
        RetrofitInstance.apiService.getReservationData().enqueue(object : Callback<List<ReservationData>> {
            override fun onResponse(call: Call<List<ReservationData>>, response: Response<List<ReservationData>>) {
                if (response.isSuccessful) {
                    reservations = response.body() ?: emptyList()
                    loading = false
                } else {
                    error = "Error: ${response.message()}"
                    loading = false
                }
            }

            override fun onFailure(call: Call<List<ReservationData>>, t: Throwable) {
                error = "Failed to load data: ${t.message}"
                loading = false
            }
        })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Informe de Reservaciones",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Display loading or error message
        if (loading) {
            Text("Cargando...", style = MaterialTheme.typography.bodyLarge)
        } else if (error != null) {
            Text("Error: $error", style = MaterialTheme.typography.bodyLarge.copy(color = Color.Red))
        } else {
            // Content displayed as a list of reservations
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(reservations) { reservation ->
                    ReservationCard(reservation)
                }
            }
        }

        // Button to go back to the home screen
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
fun ReservationCard(reservation: ReservationData) {
    // Reservation card displaying each reservation's details
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
            Text("ID de Reserva: ${reservation.id}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Habitación ID: ${reservation.habitacionId}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Estado: ${reservation.estadoReservacion}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Paquete: ${reservation.paquete.nombrePaquete}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Descripción: ${reservation.paquete.descripcion}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Costo: \$${reservation.paquete.costo}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Días: ${reservation.paquete.tiempoDias} días", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Fecha de Llegada: ${reservation.fechas.llegada}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Fecha de Salida: ${reservation.fechas.salida}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

// Preview for the ReportScreen
@Preview(showBackground = true)
@Composable
fun ReportScreenPreview() {
    val navController = rememberNavController()
    ReportScreen(navController = navController)
}
