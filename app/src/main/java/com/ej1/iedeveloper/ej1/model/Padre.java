package com.ej1.iedeveloper.ej1.model;

import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 * Created by iedeveloper on 28/12/16.
 */

public class Padre implements ParentListItem {

    private List hijos;
    private String icono;
    private String texto;

    public Padre() {
    }

    public Padre(List hijos, String icono, String texto) {
        this.hijos = hijos;
        this.icono = icono;
        this.texto = texto;
    }

    public Padre(List hijos) {
        this.hijos = hijos;
    }

    @Override
    public List<?> getChildItemList() {
        return hijos;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public List getHijos() {
        return hijos;
    }

    public void setHijos(List hijos) {
        this.hijos = hijos;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
