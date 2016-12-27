package com.ej1.iedeveloper.ej1.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by iedeveloper on 27/12/16.
 */

public class Reporte extends RealmObject {

    @PrimaryKey
    int id;
    String titulo;
    String contenido;
    User usuario;

    public Reporte() {
    }

    public Reporte(int id, String titulo, String contenido, User usuario) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
