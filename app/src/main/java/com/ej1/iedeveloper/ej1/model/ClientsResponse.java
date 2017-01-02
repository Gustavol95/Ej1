package com.ej1.iedeveloper.ej1.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ClientsResponse {


    @SerializedName("id_cliente")
    private int id;
    @SerializedName("nombre_cte")
    private String nombre;
    @SerializedName("observaciones")
    private String observaciones;
    @SerializedName("fecha_alta")
    private String fechaAlta;
    @SerializedName("zona_id")
    private int idZona;
    @SerializedName("ruta_id")
    private int idRuta;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("deleted_at")
    private String deletedAt;
    @SerializedName("proratear_abono")
    private int proratearAbono;
    @SerializedName("desc_ruta")
    private String descRuta;
    @SerializedName("desc_zona")
    private String descZona;
    private List<Domicilio> domicilios;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public int getProratearAbono() {
        return proratearAbono;
    }

    public void setProratearAbono(int proratearAbono) {
        this.proratearAbono = proratearAbono;
    }

    public String getDescRuta() {
        return descRuta;
    }

    public void setDescRuta(String descRuta) {
        this.descRuta = descRuta;
    }

    public String getDescZona() {
        return descZona;
    }

    public void setDescZona(String descZona) {
        this.descZona = descZona;
    }

    public List<Domicilio> getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(List<Domicilio> domicilios) {
        this.domicilios = domicilios;
    }

    @Override
    public String toString() {
        return "ClientsResponse{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", fechaAlta='" + fechaAlta + '\'' +
                ", idZona=" + idZona +
                ", idRuta=" + idRuta +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", deletedAt='" + deletedAt + '\'' +
                ", proratearAbono=" + proratearAbono +
                ", descRuta='" + descRuta + '\'' +
                ", descZona='" + descZona + '\'' +
                ", domicilios=" + domicilios +
                '}';
    }


    private class Domicilio{


        private int id;
        @SerializedName("id_cliente")
        private int idCliente;
        private String calle;
        @SerializedName("num_ext")
        private int numExt;
        @SerializedName("num_int")
        private int numInt;
        private int cp;
        @SerializedName("id_zona")
        private int idZona;
        @SerializedName("id_ruta")
        private int idRuta;
        @SerializedName("id_colonia")
        private int idColonia;
        @SerializedName("contacto_cliente_domicilio")
        private String contacto;
        @SerializedName("observaciones_cliente_dom")
        private String observaciones;
        private double lat;
        private double lng;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private String updatedAt;
        @SerializedName("deleted_at")
        private String deletedAt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIdCliente() {
            return idCliente;
        }

        public void setIdCliente(int idCliente) {
            this.idCliente = idCliente;
        }

        public String getCalle() {
            return calle;
        }

        public void setCalle(String calle) {
            this.calle = calle;
        }

        public int getNumExt() {
            return numExt;
        }

        public void setNumExt(int numExt) {
            this.numExt = numExt;
        }

        public int getNumInt() {
            return numInt;
        }

        public void setNumInt(int numInt) {
            this.numInt = numInt;
        }

        public int getCp() {
            return cp;
        }

        public void setCp(int cp) {
            this.cp = cp;
        }

        public int getIdZona() {
            return idZona;
        }

        public void setIdZona(int idZona) {
            this.idZona = idZona;
        }

        public int getIdRuta() {
            return idRuta;
        }

        public void setIdRuta(int idRuta) {
            this.idRuta = idRuta;
        }

        public int getIdColonia() {
            return idColonia;
        }

        public void setIdColonia(int idColonia) {
            this.idColonia = idColonia;
        }

        public String getContacto() {
            return contacto;
        }

        public void setContacto(String contacto) {
            this.contacto = contacto;
        }

        public String getObservaciones() {
            return observaciones;
        }

        public void setObservaciones(String observaciones) {
            this.observaciones = observaciones;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getDeletedAt() {
            return deletedAt;
        }

        public void setDeletedAt(String deletedAt) {
            this.deletedAt = deletedAt;
        }

        @Override
        public String toString() {
            return "Domicilio{" +
                    "id=" + id +
                    ", idCliente=" + idCliente +
                    ", calle='" + calle + '\'' +
                    ", numExt=" + numExt +
                    ", numInt=" + numInt +
                    ", cp=" + cp +
                    ", idZona=" + idZona +
                    ", idRuta=" + idRuta +
                    ", idColonia=" + idColonia +
                    ", contacto='" + contacto + '\'' +
                    ", observaciones='" + observaciones + '\'' +
                    ", lat=" + lat +
                    ", lng=" + lng +
                    ", createdAt='" + createdAt + '\'' +
                    ", updatedAt='" + updatedAt + '\'' +
                    ", deletedAt='" + deletedAt + '\'' +
                    '}';
        }
    }

}
