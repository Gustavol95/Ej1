package com.ej1.iedeveloper.ej1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ej1.iedeveloper.ej1.greendao.Cliente;
import com.ej1.iedeveloper.ej1.greendao.ClienteDao;
import com.ej1.iedeveloper.ej1.greendao.DaoSession;
import com.ej1.iedeveloper.ej1.interfaces.ServicioWeb;
import com.ej1.iedeveloper.ej1.model.ClientsResponse;

import org.greenrobot.greendao.query.Query;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
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

    public static String TAG="ActivityMain";
    @BindView(R.id.text) TextView text;
    @BindView(R.id.botonRetrieveClients) AppCompatButton botonRetrieveClients;
    SharedPreferences pref;
    private ServicioWeb servicioWeb;
    private ClienteDao clienteDao;
    private Query<Cliente> notesQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        pref = getApplicationContext().getSharedPreferences(getString(R.string.shared_prefs), 0); // 0 - for private mode
        text.setText(pref.getString("token","No se guardó el token"));
        Log.d(TAG,pref.getString("token","No se guardó el token"));
        setRetrofit();
        botonRetrieveClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveClientsRequest();
            }
        });
        // get the note DAO
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        clienteDao=daoSession.getClienteDao();

    }

    public void setRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginActivity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        servicioWeb = retrofit.create(ServicioWeb.class);
    }

    public void retrieveClientsRequest(){
        Log.d(TAG,LoginActivity.URL+"Bearer "+pref.getString("token","No se guardó el token"));
        Call<List<ClientsResponse>> call=servicioWeb.getClients("Bearer "+pref.getString("token","No se guardó el token"));
        call.enqueue(new Callback<List<ClientsResponse>>() {
            @Override
            public void onResponse(Call<List<ClientsResponse>> call, Response<List<ClientsResponse>> response) {
                Log.d(TAG, "onResponse - Status : " + response.code());
                Log.d(TAG,response.body().toString()+" ");
                insertOrUpdateToDb(response.body());
            }

            @Override
            public void onFailure(Call<List<ClientsResponse>> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });
    }
    public void insertOrUpdateToDb(List<ClientsResponse> response){
        for(ClientsResponse cliente:response){
            Cliente temp=new Cliente();
            temp.setId(cliente.getId());
            temp.setNombre(cliente.getNombre());
            temp.setObservaciones(cliente.getObservaciones());
            temp.setFechaAlta(cliente.getFechaAlta());
            temp.setIdZona(cliente.getIdZona());
            temp.setIdRuta(cliente.getIdRuta());
            temp.setCreatedAt(cliente.getCreatedAt());
            temp.setUpdatedAt(cliente.getUpdatedAt());
            temp.setDeletedAt(cliente.getDeletedAt());
            temp.setProratearAbono(cliente.getProratearAbono());
            temp.setDescZona(cliente.getDescZona());
            temp.setDescRuta(cliente.getDescRuta());
            clienteDao.insertOrReplace(temp);
        }


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
