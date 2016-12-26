package com.ej1.iedeveloper.ej1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ej1.iedeveloper.ej1.interfaces.ServicioWeb;
import com.ej1.iedeveloper.ej1.model.ControlState;
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
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class LoginActivity extends AppCompatActivity  {
    public static String TAG = "ActivityLogin";
    private static String URL = "http://lab.ie-soluciones.com/tapanosa/apiv2/movil/";
    private Observable<LoginResponse> observableLogin;
    private Subscription subscription;
    private Subscription subBoton;
    private ImageView imageViewLogo;
    private AppCompatButton buttonIniciarSesion;
    private TextInputLayout textInputEditTextUsuario;
    private TextInputLayout textInputEditTextContrasena;
    private AppCompatEditText editTextUsuario;
    private AppCompatEditText editTextContrasena;
    private ServicioWeb servicioWeb;
    private LoginResponse loginActual;
    private ProgressBar progressBar;

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
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        buttonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick");
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(15);
                buttonIniciarSesion.setEnabled(false);
                setRxJava();
            }
        });
        setRetrofit();
        observableLogin = getLoginResponse()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
        setRxInputValidations();
    }

    public void setRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        servicioWeb = retrofit.create(ServicioWeb.class);
    }

    public void setRxJava() {
        subscription = observableLogin.subscribe(new Subscriber<LoginResponse>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "" + e.toString());

            }

            @Override
            public void onNext(LoginResponse loginResponse) {
                Log.i(TAG, "onNext");

            }
        });
    }

    public Observable<String> getTextWatcherObservable(@NonNull final AppCompatEditText editText) {

        final PublishSubject<String> subject = PublishSubject.create();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                subject.onNext(s.toString());
            }
        });

        return subject;
    }

    public Observable<ControlState> getEmailObservable(@NonNull final AppCompatEditText editText) {

        final PublishSubject<ControlState> subject = PublishSubject.create();
        final ControlState control = new ControlState();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                control.setDirty(true);
                control.setValid(isValidEmail(s.toString()));
                subject.onNext(control);
            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus )
                    control.setVisited(true);
                    subject.onNext(control);
            }
        });

        return subject;
    }

    public Observable<LoginResponse> getLoginResponse() {
        return Observable.create(new Observable.OnSubscribe<LoginResponse>() {
            @Override
            public void call(Subscriber<? super LoginResponse> subscriber) {
                try {
                    subscriber.onNext(sendRequest());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                subscriber.onCompleted();
            }
        });
    }

    public LoginResponse sendRequest() throws IOException {
        Call<LoginResponse> call = servicioWeb.login(editTextUsuario.getText().toString().trim(), editTextContrasena.getText().toString().trim());

        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.i(TAG, response.message());
                if (response.isSuccessful() && response.code() == 200) {
                    try{
                    SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.shared_prefs), 0); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("token", response.body().getToken());
                    editor.apply();
                    Log.i(TAG, "Entró token: " + response.body().getToken());
                    Toast.makeText(LoginActivity.this, "Se registró token", Toast.LENGTH_SHORT).show();}
                    finally{
                        Intent i=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                } else if (response.code() != 200) {
                    Toast.makeText(LoginActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                }
                setLoginActual(response.body());
                progressBar.setVisibility(View.INVISIBLE);
                progressBar.setProgress(15);
                buttonIniciarSesion.setEnabled(true);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "No hay conexión", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "error: " + t.toString());
                progressBar.setVisibility(View.INVISIBLE);
                progressBar.setProgress(15);
                buttonIniciarSesion.setEnabled(true);
            }

        });
        return getLoginActual();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        if (subBoton != null && !subBoton.isUnsubscribed()) {
            subBoton.unsubscribe();
        }
    }

    public LoginResponse getLoginActual() {
        return loginActual;
    }

    public void setLoginActual(LoginResponse loginActual) {
        this.loginActual = loginActual;
    }

    public final boolean isValidEmail(String target) {
        if (target == null) {
            return false;
        } else {
            //android Regex to check the email address Validation
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public void activarBoton(){
        buttonIniciarSesion.setEnabled(true);
        buttonIniciarSesion.setTextColor(Color.WHITE);
        buttonIniciarSesion.setBackgroundResource(R.color.azulDesbloqueado);
    }

    public void desactivarBoton(){
        buttonIniciarSesion.setEnabled(false);
        buttonIniciarSesion.setTextColor(getResources().getColor(R.color.grisBloqueado));
        buttonIniciarSesion.setBackgroundResource(R.color.grisBloqueadoFondo);
    }

    public void setRxInputValidations(){
        Observable<ControlState> observableEmail=getEmailObservable(editTextUsuario)
                .map(new Func1<ControlState, ControlState>() {
                    @Override
                    public ControlState call(ControlState controlState) {
                        if(controlState.isVisited() && !controlState.isValid()){
                            textInputEditTextUsuario.setErrorEnabled(true);
                            textInputEditTextUsuario.setError("Email inválido");
                        }
                        else
                            textInputEditTextUsuario.setErrorEnabled(false);

                        Log.i(TAG,"visited (!hasFocus) "+controlState.isVisited());
                        Log.i(TAG, "dirty (textChanged) "+controlState.isDirty());
                        Log.i(TAG, "valid  (isEmailValid)"+controlState.isValid());
                        return controlState;
                    }
                });

        Observable<Boolean> observablePassword=getTextWatcherObservable(editTextContrasena)
                .map(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !s.trim().isEmpty();
                    }
                });
        subBoton=Observable.combineLatest(observableEmail, observablePassword, new Func2<ControlState, Boolean, Boolean>() {
            @Override
            public Boolean call(ControlState controlState, Boolean validPassword) {
                 if(controlState.isDirty() && controlState.isVisited() && !controlState.isValid()){
                     textInputEditTextUsuario.setErrorEnabled(true);
                     textInputEditTextUsuario.setError("Email inválido");
                 }else
                     textInputEditTextUsuario.setErrorEnabled(false);
                return controlState.isDirty() && controlState.isVisited() && controlState.isValid() && validPassword;
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean ambosValidos) {
                if(ambosValidos)
                    activarBoton();
                else
                    desactivarBoton();
            }
        });

    }

}
