package com.ej1.iedeveloper.ej1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;

import com.ej1.iedeveloper.ej1.adapter.TestRecyclerAdapter;
import com.ej1.iedeveloper.ej1.model.RecyclerViewTestItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/*
* LIBRERIAS USADAS.
* - Retrofit 2.0 (Peticiones HTTP)  https://square.github.io/retrofit/
* - RxJava 2.0 (Reactive) https://github.com/ReactiveX/RxJava
* - Greendao 3.0 (ORM Database) http://greenrobot.org/greendao/
* - ButterKnife (Binding de vistas xml con activities)
* - Expandable RecyclerView https://github.com/bignerdranch/expandable-recycler-view
* - TextDrawable (Como Gmail)  https://github.com/amulyakhare/TextDrawable
*
* MODIFICACIONES EN GRADLE
* - Java 8, compilador Jack para uso de Expresiones lambda de Java 8. Instant run deshabilitado https://developer.android.com/guide/platform/j8-jack.html?hl=es
* */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text) TextView text;
    @BindView(R.id.recycler) RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.shared_prefs), 0); // 0 - for private mode
        text.setText(pref.getString("token","No se guardó el token"));
        StaggeredGridLayoutManager sglm=new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        LinearLayoutManager llm=new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(sglm);
        recyclerView.setLayoutManager(llm);
        TestRecyclerAdapter adapter=new TestRecyclerAdapter(initializeAdapter(),this);
        recyclerView.setAdapter(adapter);
    }
    public List<RecyclerViewTestItem> initializeAdapter(){
        List<RecyclerViewTestItem> items=new ArrayList<>();
        for(int i=0;i<100;i++)
        items.add(new RecyclerViewTestItem("Item "+(i+1)));

        return items;
    }
}
