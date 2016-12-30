package com.ej1.iedeveloper.ej1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

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

    public static String TAG="AcivityMain";
    @BindView(R.id.text) TextView text;
    @BindView(R.id.recycler) RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.shared_prefs), 0); // 0 - for private mode
        text.setText(pref.getString("token","No se guardó el token"));
        Log.d(TAG,pref.getString("token","No se guardó el token"));

    }

}
/*
[
        {
        "id_cliente": 1,
        "nombre_cte": "MARIA ISABEL DE LA TORRE OLIVAS",
        "observaciones": null,
        "fecha_alta": "2016-09-28",
        "zona_id": 13,
        "ruta_id": 3,
        "created_at": "2016-09-28 06:23:40",
        "updated_at": "2016-11-28 16:01:13",
        "deleted_at": null,
        "proratear_abono": "0",
        "desc_ruta": "Ruta Pruebas",
        "desc_zona": "ZONA PALMITO",
        "domicilios": [
        {
        "id": 11,
        "id_cliente": 1,
        "calle": "calle no se",
        "num_ext": "5432",
        "num_int": null,
        "cp": "32767",
        "id_zona": 2,
        "id_ruta": 3,
        "id_colonia": 16531,
        "contacto_cliente_domicilio": null,
        "observaciones_cliente_dom": null,
        "lat": 24.816372,
        "lng": -107.376886,
        "created_at": "2016-11-17 11:22:49",
        "updated_at": "2016-11-28 16:01:13",
        "deleted_at": null
        }
        ]
        },
        {
        "id_cliente": 107,
        "nombre_cte": "PRUEBAS IE",
        "observaciones": null,
        "fecha_alta": "2016-09-30",
        "zona_id": 2,
        "ruta_id": 3,
        "created_at": "2016-09-30 07:07:25",
        "updated_at": "2016-10-05 17:11:50",
        "deleted_at": null,
        "proratear_abono": "0",
        "desc_ruta": "Ruta Pruebas",
        "desc_zona": "ZONA QUINTAS",
        "domicilios": []
        },
        {
        "id_cliente": 108,
        "nombre_cte": "PRUEBAS IE RUTAS",
        "observaciones": null,
        "fecha_alta": "2016-10-03",
        "zona_id": 2,
        "ruta_id": 3,
        "created_at": "2016-10-03 10:49:31",
        "updated_at": "2016-10-13 22:35:55",
        "deleted_at": null,
        "proratear_abono": "0",
        "desc_ruta": "Ruta Pruebas",
        "desc_zona": "ZONA QUINTAS",
        "domicilios": []
        }
        ]*/
