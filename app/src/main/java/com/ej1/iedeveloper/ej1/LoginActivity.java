package com.ej1.iedeveloper.ej1;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ej1.iedeveloper.ej1.interfaces.ServicioWeb;
import com.ej1.iedeveloper.ej1.model.LoginResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by iedeveloper on 20/12/16.
 */

public class LoginActivity extends AppCompatActivity {

    public static String TAG="ActivityLogin";
    private static String URL = "http://lab.ie-soluciones.com/tapanosa/apiv2/movil/";
    private Subscription subscription;
    private ImageView imageViewLogo;
    private AppCompatButton buttonIniciarSesion;
    private TextInputLayout textInputEditTextUsuario;
    private TextInputLayout textInputEditTextContrasena;
    private AppCompatEditText editTextUsuario;
    private AppCompatEditText editTextContrasena;
    private ServicioWeb servicioWeb;
    private LoginResponse loginActual;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imageViewLogo = (ImageView) findViewById(R.id.imageViewLogo);
        textInputEditTextUsuario = (TextInputLayout) findViewById(R.id.input_layout_usuario);
        textInputEditTextContrasena = (TextInputLayout) findViewById(R.id.input_layout_contrasena);
        editTextUsuario = (AppCompatEditText) findViewById(R.id.editText_usuario);
        editTextContrasena = (AppCompatEditText) findViewById(R.id.editText_contrasena);
        buttonIniciarSesion = (AppCompatButton) findViewById(R.id.buttonLogin);
        buttonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRxJava();
            }
        });

        setRetrofit();
        editTextUsuario.addTextChangedListener(new MyTextWatcher(editTextUsuario));
        editTextContrasena.addTextChangedListener(new MyTextWatcher(editTextContrasena));

    }



    public void setRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        servicioWeb = retrofit.create(ServicioWeb.class);

    }

    public void setRxJava(){
        subscription=getLoginResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,""+e.toString());
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {

                    }
                });
    }

    public Observable<LoginResponse> getLoginResponse(){
        return Observable.defer(new Func0<Observable<LoginResponse>>() {
            @Override
            public Observable<LoginResponse> call() {
                try {
                    return Observable.just(sendRequest());
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });


    }

    public LoginResponse sendRequest() throws IOException {
        Call<LoginResponse> call = servicioWeb.login(editTextUsuario.getText().toString().trim(), editTextContrasena.getText().toString().trim());
        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.i(TAG, response.message());
                if (response.isSuccessful() &&response.code()==200) {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.shared_prefs), 0); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("token",response.body().getToken());
                    editor.commit();
                    Log.i(TAG, "Entró token: " +response.body().getToken());
                    Toast.makeText(LoginActivity.this, "Se registró token", Toast.LENGTH_SHORT).show();
                }
                else if(response.code()!=200){
                    Toast.makeText(LoginActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                }
                setLoginActual(response.body());

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "No hay conexión", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "error: "+t.toString());
            }

        });
        return getLoginActual();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(subscription!=null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

    public LoginResponse getLoginActual() {
        return loginActual;
    }

    public void setLoginActual(LoginResponse loginActual) {
        this.loginActual = loginActual;
    }



    private class MyTextWatcher implements TextWatcher {


        private View view;
        private boolean emailOk=false;
        private boolean passwordOk=false;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {

                    if(!isValidEmail(editTextUsuario.getText().toString().trim())){
                        textInputEditTextUsuario.setErrorEnabled(true);
                        textInputEditTextUsuario.setError("E-mail incorrecto.");
                        emailOk=false;

                }
                    else{
                        textInputEditTextUsuario.setErrorEnabled(false);
                        emailOk=true;
                    }


                    if(editTextContrasena.getText().toString().isEmpty()){
                        textInputEditTextContrasena.setErrorEnabled(true);
                        textInputEditTextContrasena.setError("No puede ser vacío");
                        passwordOk=false;

                    }
                    else{
                        passwordOk=true;
                        textInputEditTextContrasena.setErrorEnabled(false);

                    }



            validarBoton();

        }

        public final  boolean isValidEmail(String target) {
            if (target == null) {
                return false;
            } else {
                //android Regex to check the email address Validation
                return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
            }
        }
        public void validarBoton(){
            Log.i(TAG,"validar Boton "+emailOk+passwordOk);
            if(emailOk && passwordOk){
                buttonIniciarSesion.setEnabled(true);
                buttonIniciarSesion.setTextColor(Color.WHITE);
                buttonIniciarSesion.setBackgroundResource(R.color.azulDesbloqueado);
            }
            else{
                buttonIniciarSesion.setEnabled(false);
                buttonIniciarSesion.setTextColor(getResources().getColor(R.color.grisBloqueado));
                buttonIniciarSesion.setBackgroundResource(R.color.grisBloqueadoFondo);
            }
        }
    }

}
    /*
    Call<LoginResponse> call = servicioWeb.login(editTextUsuario.getText().toString().trim(), editTextContrasena.getText().toString().trim());
    call.enqueue(new Callback<LoginResponse>() {
    @Override
    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
        Log.i(TAG, response.message());
        if (response.isSuccessful() &&response.code()==200) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.shared_prefs), 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("token",response.body().getToken());
        editor.commit();
        Log.i(TAG, "Entró token: " +response.body().getToken());
        Toast.makeText(LoginActivity.this, "Se registró token", Toast.LENGTH_SHORT).show();
        }
        else if(response.code()!=200){
        Toast.makeText(LoginActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
        }
        }

@Override
public void onFailure(Call<LoginResponse> call, Throwable t) {
        Toast.makeText(LoginActivity.this, "No hay conexión", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "error: "+t.toString());
        }
        });
        */