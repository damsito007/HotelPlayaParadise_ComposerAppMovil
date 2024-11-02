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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hotelplayaparadise.ui.theme.screens.LoginScreen
import com.example.hotelplayaparadise.ui.theme.screens.ProfileScreen
import com.example.hotelplayaparadise.ui.theme.screens.ReportScreen
import com.example.hotelplayaparadise.ui.theme.screens.SplashScreen
import com.example.hotelplayaparadise.ui.theme.theme.HotelPlayaParadiseTheme
import kotlinx.coroutines.launch

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
                    label = { Text("Informe de Satisfacción") },
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Sección de Indicadores con LazyRow
        Text(
            text = "Indicadores de Rendimiento",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {

            items(indicators) { indicator ->
                IndicatorCard(indicator)
            }
        }

        // Espacio para el gráfico (por ahora, un ícono)
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Gráficos de Ventas",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "Placeholder Gráfico",
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        // Sección final: Resumen de actividad o reporte
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Resumen de Actividad",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Datos de actividad reciente...",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// Datos de ejemplo para los indicadores
val indicators = listOf(
    Indicator("Ventas", "$12,000", Icons.Default.ShoppingCart, Color(0xFF4CAF50)),
    Indicator("Ganancias", "$8,000", Icons.Default.KeyboardArrowUp, Color(0xFFFFC107)),
    Indicator("Clientes", "350", Icons.Default.AccountBox, Color(0xFF03A9F4))
)

@Composable
fun IndicatorCard(indicator: Indicator) {
    Card(
        modifier = Modifier.size(width = 150.dp, height = 100.dp),
        colors = CardDefaults.cardColors(containerColor = indicator.color),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = indicator.icon,
                contentDescription = indicator.title,
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
            Text(text = indicator.title, color = Color.White, fontSize = 16.sp)
            Text(text = indicator.value, color = Color.White, fontSize = 18.sp, style = MaterialTheme.typography.titleMedium)
        }
    }
}

// Clase para los indicadores
data class Indicator(
    val title: String,
    val value: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val color: Color
)
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