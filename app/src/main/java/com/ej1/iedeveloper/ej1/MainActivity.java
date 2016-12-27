package com.ej1.iedeveloper.ej1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ej1.iedeveloper.App;
import com.ej1.iedeveloper.db.Usuario;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView text;
    SharedPreferences pref;
    public static String TAG = "ActivityMain";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView) findViewById(R.id.text);
         pref = getApplicationContext().getSharedPreferences(getString(R.string.shared_prefs), 0); // 0 - for private mode
        text.setText(pref.getString("token","No se guardó el token"));
        createUser();



    }


    public void createUser(){
        Usuario user=new Usuario((long)1,"Gustavo",pref.getString("token","No se guardó el token"));
        ((App)getApplication()).getDaoSession().getUsuarioDao().insertOrReplace(user);
       List<Usuario> users= ((App)getApplication()).getDaoSession().getUsuarioDao().loadAll();
        for(Usuario userTemp:users){
            Log.d(TAG,userTemp.toString());
        }
    }
}
