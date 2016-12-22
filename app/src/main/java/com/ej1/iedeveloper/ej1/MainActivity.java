package com.ej1.iedeveloper.ej1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView) findViewById(R.id.text);
        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.shared_prefs), 0); // 0 - for private mode
        text.setText(pref.getString("token","No se guardó el token"));


    }
}
