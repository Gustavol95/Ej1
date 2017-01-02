package com.ej1.iedeveloper.ej1.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by iedeveloper on 02/01/17.
 */
@Entity
public class Domicilio{

    @Id
    private int id;
    private int idCliente;
    private String calle;
    private int numExt;
    private int numInt;
    private int cp;
    private int idZona;
    private int idRuta;
    private int idColonia;
    private String contacto;
    private String observaciones;
    private double lat;
    private double lng;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;

    @Generated(hash = 1087981348)
    public Domicilio(int id, int idCliente, String calle, int numExt, int numInt,
            int cp, int idZona, int idRuta, int idColonia, String contacto,
            String observaciones, double lat, double lng, String createdAt,
            String updatedAt, String deletedAt) {
        this.id = id;
        this.idCliente = idCliente;
        this.calle = calle;
        this.numExt = numExt;
        this.numInt = numInt;
        this.cp = cp;
        this.idZona = idZona;
        this.idRuta = idRuta;
        this.idColonia = idColonia;
        this.contacto = contacto;
        this.observaciones = observaciones;
        this.lat = lat;
        this.lng = lng;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    @Generated(hash = 1467572112)
    public Domicilio() {
    }

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