package com.ej1.iedeveloper.ej1.model;

import android.view.View;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.ej1.iedeveloper.ej1.R;

import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by iedeveloper on 28/12/16.
 */

public class HijoViewHolder extends ChildViewHolder {
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */

    TextView controles;

    public HijoViewHolder(View itemView) {
        super(itemView);
        controles=(TextView)itemView.findViewById(R.id.textoHijo);
    }

    public void bind(Hijo hijo){
        controles.setText(hijo.getTexto());

    }
}
