package com.ej1.iedeveloper.ej1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ej1.iedeveloper.ej1.model.User;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "ActivityMain";
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView) findViewById(R.id.text);
        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.shared_prefs), 0); // 0 - for private mode
        text.setText(pref.getString("token","No se guardó el token"));
        Realm realm=Realm.getDefaultInstance();
        final User usuario=new User(1, "Gustavo",pref.getString("token","No se guardó el token"),null);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(usuario);
            }
        });

        Log.d(TAG,realm.where(User.class).findFirst().toString());
        realm.close();

}
}
