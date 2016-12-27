package com.ej1.iedeveloper.ej1.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by iedeveloper on 27/12/16.
 */

public class User extends RealmObject{
    @PrimaryKey
    int id;
    String nombre;
    String token;
    RealmList<Reporte> reportes;

    public User() {
    }

    public User(int id, String nombre, String token, RealmList<Reporte> reportes) {
        this.id = id;
        this.nombre = nombre;
        this.token = token;
        this.reportes = reportes;
    }

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RealmList<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(RealmList<Reporte> reportes) {
        this.reportes = reportes;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", token='" + token + '\'' +
                ", reportes=" + reportes +
                '}';
    }
}
