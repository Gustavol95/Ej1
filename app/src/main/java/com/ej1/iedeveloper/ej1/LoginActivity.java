package com.ej1.iedeveloper.ej1;

import android.content.Context;
import android.content.Intent;
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
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import rx.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity implements View.OnFocusChangeListener {
    public static String TAG = "ActivityLogin";
    private static String URL = "http://lab.ie-soluciones.com/tapanosa/apiv2/movil/";
    private Observable<LoginResponse> observableLogin;
    private Subscription subscription;
    private ImageView imageViewLogo;
    private AppCompatButton buttonIniciarSesion;
    private TextInputLayout textInputEditTextUsuario;
    private TextInputLayout textInputEditTextContrasena;
    private AppCompatEditText editTextUsuario;
    private AppCompatEditText editTextContrasena;
    private ServicioWeb servicioWeb;
    private LoginResponse loginActual;
    private ProgressBar progressBar;
    private boolean emailOk = false;
    private boolean passwordOk = false;

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
        editTextUsuario.setOnFocusChangeListener(this);
        editTextContrasena.setOnFocusChangeListener(this);
        editTextContrasena.setImeOptions(EditorInfo.IME_ACTION_DONE);
        editTextContrasena.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {

                    case EditorInfo.IME_ACTION_DONE:
                        View view = LoginActivity.this.getCurrentFocus();
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                        editTextContrasena.clearFocus();
                        Log.i(TAG, "Enter");
                        return true;
                    default:
                        return false;
                }
            }
        });

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
                Intent i=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
                finish();
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

    @Override
    public void onBackPressed() {

        Log.i(TAG, "Tiene focus contraseña");
        View view = LoginActivity.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            editTextContrasena.clearFocus();

        }

        super.onBackPressed();
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
                    SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.shared_prefs), 0); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("token", response.body().getToken());
                    editor.apply();
                    Log.i(TAG, "Entró token: " + response.body().getToken());
                    Toast.makeText(LoginActivity.this, "Se registró token", Toast.LENGTH_SHORT).show();
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
    }

    public LoginResponse getLoginActual() {
        return loginActual;
    }

    public void setLoginActual(LoginResponse loginActual) {
        this.loginActual = loginActual;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            switch (v.getId()) {
                case R.id.editText_usuario:
                    if (!isValidEmail(editTextUsuario.getText().toString().trim())) {
                        textInputEditTextUsuario.setErrorEnabled(true);
                        textInputEditTextUsuario.setError("E-mail incorrecto.");
                        emailOk = false;

                    } else {
                        textInputEditTextUsuario.setErrorEnabled(false);
                        emailOk = true;
                    }
                    break;
                case R.id.editText_contrasena:
                    if (editTextContrasena.getText().toString().isEmpty()) {
                        textInputEditTextContrasena.setErrorEnabled(true);
                        textInputEditTextContrasena.setError("No puede ser vacío");
                        passwordOk = false;

                    } else {
                        passwordOk = true;
                        textInputEditTextContrasena.setErrorEnabled(false);

                    }
                    break;
            }
            validarBoton();
        }


    }

    public final boolean isValidEmail(String target) {
        if (target == null) {
            return false;
        } else {
            //android Regex to check the email address Validation
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public void validarBoton() {
        Log.i(TAG, "validar Boton " + emailOk + passwordOk);
        if (emailOk && passwordOk) {
            buttonIniciarSesion.setEnabled(true);
            buttonIniciarSesion.setTextColor(Color.WHITE);
            buttonIniciarSesion.setBackgroundResource(R.color.azulDesbloqueado);
        } else {
            buttonIniciarSesion.setEnabled(false);
            buttonIniciarSesion.setTextColor(getResources().getColor(R.color.grisBloqueado));
            buttonIniciarSesion.setBackgroundResource(R.color.grisBloqueadoFondo);
        }
    }


}
