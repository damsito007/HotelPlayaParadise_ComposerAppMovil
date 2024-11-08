package com.example.hotelplayaparadise


import retrofit2.Call
import retrofit2.http.GET

// Retrofit Service Interface for KPI Data
interface ApiService {
    @GET("Reservacion/confirmadas")
    fun getKPIData(): Call<List<ReservationData>>

}
