package com.ej1.iedeveloper.ej1.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by iedeveloper on 02/01/17.
 */
@Entity
public class Cliente {

    @Id
    private long id;
    private String nombre;
    private String observaciones;
    private String fechaAlta;
    private int idZona;
    private int idRuta;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;
    private int proratearAbono;
    private String descRuta;
    private String descZona;

    @Generated(hash = 810806435)
    public Cliente(long id, String nombre, String observaciones, String fechaAlta,
            int idZona, int idRuta, String createdAt, String updatedAt,
            String deletedAt, int proratearAbono, String descRuta,
            String descZona) {
        this.id = id;
        this.nombre = nombre;
        this.observaciones = observaciones;
        this.fechaAlta = fechaAlta;
        this.idZona = idZona;
        this.idRuta = idRuta;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.proratearAbono = proratearAbono;
        this.descRuta = descRuta;
        this.descZona = descZona;
    }


    @Generated(hash = 1805939709)
    public Cliente() {
    }

    public long getId() {
        return id;
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


    public void setId(long id) {
        this.id = id;
    }
}
