package com.example.hotelplayaparadise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hotelplayaparadise.ui.theme.screens.ApiService
import com.example.hotelplayaparadise.ui.theme.screens.FacturasTodasCard
import com.example.hotelplayaparadise.ui.theme.screens.IngresoCard
import com.example.hotelplayaparadise.ui.theme.screens.LoginScreen
import com.example.hotelplayaparadise.ui.theme.screens.NewReservationCard
import com.example.hotelplayaparadise.ui.theme.screens.PagoCard
import com.example.hotelplayaparadise.ui.theme.screens.ProfileScreen
import com.example.hotelplayaparadise.ui.theme.screens.ReportScreen
import com.example.hotelplayaparadise.ui.theme.screens.ReservationCard
import com.example.hotelplayaparadise.ui.theme.screens.ReservationCardall
import com.example.hotelplayaparadise.ui.theme.screens.RetrofitInstance
import com.example.hotelplayaparadise.ui.theme.screens.SplashScreen
import com.example.hotelplayaparadise.ui.theme.theme.HotelPlayaParadiseTheme
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import kotlin.random.Random

//Interface del servicio de la api
interface ApiService2 {
    @GET("Reservacion/Todas")
    fun getingresocliente(): Call<List<ClienteIngreso>>
}
//Instancia de Retrofit
object RetrofitInstance2 {
    private const val BASE_URL = "https://xmcf8cn0-5069.use.devtunnels.ms/"
    val apiService2: ApiService2 by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService2::class.java)
    }
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HotelPlayaParadiseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(modifier = Modifier.padding(innerPadding) )
                }
            }
        }
    }
}

@Composable
fun MyApp( modifier: Modifier = Modifier) {
    //Host de Navegación
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        // Pantalla de cara (Splash Screen)
        composable("splash") { SplashScreen(navController) }
        // Pantalla de Login
        composable("login") { LoginScreen(navController) }
        // Pantalla Home con Menú Desplegable
        composable("home") { HomeScreen(navController) }
        // Pantalla de Configuración
        composable("settings") { SettingsScreen(navController) }
        // Pantalla de Perfil
        composable("profile") { ProfileScreen(navController) }
        // Pantalla de Informe de Satisfacción
        composable("report") { ReportScreen(navController) }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    HotelPlayaParadiseTheme {
        val navController = rememberNavController()
        // SplashScreen(navController)
        //LoginScreen(navController)
        HomeScreen(navController)
        // OpcionesMenuLateral(navController)
        //HomeContent()
        //ProfileScreen(navController)
        //ReportScreen(navController)

    }
}



@Composable
fun OpcionesMenuLateral(navController: NavHostController) {
    ModalDrawerSheet(
        // modifier = Modifier.fillMaxSize(),
        content = {
            // Header del menú lateral con información del usuario
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Foto de perfil o ícono de usuario
                Surface(
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Foto de perfil",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Usuario", style = MaterialTheme.typography.titleLarge)
                Text(text = "usuario@example.com", style = MaterialTheme.typography.bodyMedium)
            }

            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))

            // Opciones del menú
            Column(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(16.dp))

                // Opción Configuración
                NavigationDrawerItem(
                    icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = "Configuración") },
                    label = { Text("Configuración") },
                    selected = false,
                    onClick = { navController.navigate("settings") }
                )

                // Opción Perfil
                NavigationDrawerItem(
                    icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Perfil") },
                    label = { Text("Perfil") },
                    selected = false,
                    onClick = { navController.navigate("profile") }
                )

                // Opción Informe de Satisfacción
                NavigationDrawerItem(
                    icon = { Icon(imageVector = Icons.Default.ThumbUp, contentDescription = "Informe de Satisfacción") },
                    label = { Text("Informe de MongoDB") },
                    selected = false,
                    onClick = { navController.navigate("report") }
                )
            }
        }
    )
}
//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            //Agregamos el componente para el menu lateral
            OpcionesMenuLateral(navController)
        },
        content = {
            Column() {
                IconButton(onClick = { scope.launch { drawerState.open() } }) {
                    Icon(Icons.Filled.Menu, contentDescription = "Icon" )
                }


            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                //Agregamos el contenido del Home
                HomeContent()
            }
        }
    )
}
//Contenido del home
@Composable
fun HomeContent() {
    var clienteingre by remember { mutableStateOf<List<ClienteIngreso>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {

        RetrofitInstance2.apiService2.getingresocliente().enqueue(object :
            Callback<List<ClienteIngreso>> {
            override fun onResponse(call: Call<List<ClienteIngreso>>, response: Response<List<ClienteIngreso>>) {
                if (response.isSuccessful) {
                    clienteingre = response.body() ?: emptyList()
                } else {
                    error = "Error: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<List<ClienteIngreso>>, t: Throwable) {
                error = "Failed to load data: ${t.message}"
            }
        })
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp) // Make space for the fixed button at the bottom
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Informe del Modelo Tabular",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Display loading or error message
            if (loading) {
                // Barra de carga con color primario de MaterialTheme
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp),
                    color = MaterialTheme.colorScheme.primary // Usando color primario
                )
            } else if (error != null) {
                Text("Error: $error", style = MaterialTheme.typography.bodyLarge.copy(color = Color.Red))
            } else {

                // Content displaying all reservation types
                LazyColumn(modifier = Modifier.weight(1f)) {

                    item {
                        Text(
                            text = "Reservaciones Totales:",
                            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            textAlign = TextAlign.Center // Centrar el título
                        )
                    }

                    //items(clienteingre) { reservationall ->
                    //    ReservationCardall(reservationall)
                    }

                }

            }

        }

    }




@Composable
fun SettingsScreen(navController: NavHostController) {
    // Pantalla de Configuración
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Configuración", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("home") }) {
            Text("Volver")
        }
    }
}