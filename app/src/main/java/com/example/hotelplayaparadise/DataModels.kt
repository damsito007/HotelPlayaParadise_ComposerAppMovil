package com.example.hotelplayaparadise
import com.google.gson.annotations.SerializedName

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

data class NewReservationData(
    @SerializedName("_id") val id: String,
    @SerializedName("totalReservaciones") val totalReservaciones: Int
)

data class IngresoData(
    @SerializedName("_id") val id: String,
    @SerializedName("totalIngresos") val totalIngresos: Double
)

data class ReservacionTotal(
    @SerializedName("_id") val id: String,
    @SerializedName("habitacion_id") val habitacionId: String,
    @SerializedName("paquete") val paquete: Paquete,
    @SerializedName("estado_reservacion") val estadoReservacion: String,
    @SerializedName("fechas") val fechas: Fechas,
    @SerializedName("Factura_id") val facturaId: String,
    @SerializedName("Clientes_id") val clientesId: String
)

data class ReservacionFactura(
    @SerializedName("_id") val id: String,
    @SerializedName("fecha") val fecha: String,
    @SerializedName("Reservacion_id") val reservacionId: String,
    @SerializedName("tipo_transaccion") val tipoTransaccion: TipoTransaccion,
    @SerializedName("detalle_monto") val detalleMonto: DetalleMonto,
    @SerializedName("historial_pagos") val historialPagos: List<HistorialPago>,
    @SerializedName("estado") val estado: String,
    @SerializedName("clientes_id") val clientesId: String
)

data class TipoTransaccion(
    @SerializedName("metodo_pago") val metodoPago: String,
    @SerializedName("informacion_pago") val informacionPago: String,
    @SerializedName("cambio_devuelto") val cambioDevuelto: Int,
    @SerializedName("moneda") val moneda: String,
    @SerializedName("ajuste") val ajuste: Int
)

data class DetalleMonto(
    @SerializedName("monto_base") val montoBase: Int,
    @SerializedName("impuestos") val impuestos: Int,
    @SerializedName("descuentos") val descuentos: Int,
    @SerializedName("monto_total") val montoTotal: Int,
    @SerializedName("costo_paquetes") val costoPaquetes: Int
)

data class HistorialPago(
    @SerializedName("fecha") val fecha: String,
    @SerializedName("monto") val monto: Int,
    @SerializedName("metodo_pago") val metodoPago: String,
    @SerializedName("informacion_pago") val informacionPago: String
)

data class PagoTotalPorMetodo(
    @SerializedName("_id") val id: String,
    @SerializedName("totalPagos") val totalPagos: Int
)

data class ClienteIngreso(
    @SerializedName("Clientes[Nombre Clientes]") val nombreCliente: String,
    @SerializedName("[IngresoTotal]") val ingresoTotal: Double
)