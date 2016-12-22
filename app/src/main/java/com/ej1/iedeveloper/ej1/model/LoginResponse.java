package com.ej1.iedeveloper.ej1.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by iedeveloper on 20/12/16.
 */

public class LoginResponse {
    @SerializedName("id")
    private int id;
    @SerializedName("email")
    private String email;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("proratear_abono")
    private int prorratear;
    @SerializedName("id_ruta")
    private int idRuta;
    @SerializedName("nombre_ruta")
    private String nombreRuta;
    @SerializedName("id_vehiculo")
    private int idVehiculo;
    @SerializedName("nombre_vehiculo")
    private String nombreVehiculo;
    @SerializedName("id_almacen")
    private int idAlmacen;
    @SerializedName("id_caja")
    private int idCaja;
    @SerializedName("id_sucursal")
    private int idSucursal;
    @SerializedName("nombre_sucursal")
    private String nombreSucursal;
    @SerializedName("telefonos")
    private String telefonos;
    @SerializedName("token")
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getProrratear() {
        return prorratear;
    }

    public void setProrratear(int prorratear) {
        this.prorratear = prorratear;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getNombreVehiculo() {
        return nombreVehiculo;
    }

    public void setNombreVehiculo(String nombreVehiculo) {
        this.nombreVehiculo = nombreVehiculo;
    }

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public int getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}