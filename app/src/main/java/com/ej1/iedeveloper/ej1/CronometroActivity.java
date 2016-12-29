package com.ej1.iedeveloper.ej1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ej1.iedeveloper.ej1.adapter.ExpandableAdapter;
import com.ej1.iedeveloper.ej1.model.Hijo;
import com.ej1.iedeveloper.ej1.model.Padre;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iedeveloper on 29/12/16.
 */

public class CronometroActivity extends AppCompatActivity {


    @BindView(R.id.recycler)  RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);
        ButterKnife.bind(this);
        ExpandableAdapter adapter = new ExpandableAdapter(this,Arrays.asList(initializeAdapter()));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public Padre initializeAdapter(){
        Hijo hijo=new Hijo("Aqui los controles");
        Padre padre=new Padre(Arrays.asList(hijo),"1","Texto con info");
        return padre;
    }
}
