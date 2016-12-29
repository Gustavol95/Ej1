package com.ej1.iedeveloper.ej1.model;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.ej1.iedeveloper.ej1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iedeveloper on 28/12/16.
 */

public class PadreViewHolder extends ParentViewHolder {
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */

      ImageView letra;
     TextView texto;

    public PadreViewHolder(View itemView) {
        super(itemView);
        letra =(ImageView)itemView.findViewById(R.id.imagenLetra);
        texto=(TextView)itemView.findViewById(R.id.textoPadre);

    }

    public void bind(Padre padre){
        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .textColor(Color.BLACK)
                .useFont(Typeface.DEFAULT)
                .fontSize(30) /* size in px */
                .bold()
                .toUpperCase()
                .endConfig()
                .buildRect(padre.getIcono(), Color.RED);
        letra.setImageDrawable(drawable);
        texto.setText(padre.getTexto());
    }
}
