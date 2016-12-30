package com.ej1.iedeveloper.ej1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by iedeveloper on 29/12/16.
 */

public class CronometroActivity extends AppCompatActivity {


    @BindView(R.id.recycler)        RecyclerView recyclerView;
    @BindView(R.id.textoCronometro) TextView textoCronometro;
    @BindView(R.id.fab)             FloatingActionButton fab;

    public static final long MILLIS_TO_MINUTES = 60000;
    public static final long MILLS_TO_HOURS = 3600000;
    long startTime;
    Subscription chronometerSub;
    String tiempoActual;

    public static String TAG = "ActivityCronometro";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);
        ButterKnife.bind(this);


        chronometerSub=getChonometerObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String tiempoCorriendo) {
                        tiempoActual=tiempoCorriendo;
                        textoCronometro.setText(tiempoActual);
                    }
                });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    public Observable<String> getChonometerObservable(){
        startTime=System.currentTimeMillis();
        return Observable.interval(15, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, String>() {
                    @Override
                    public String call(Long aLong) {
                        long since = System.currentTimeMillis() - startTime;
                        int seconds = (int) (since / 1000) % 60;
                        int minutes = (int) ((since / (MILLIS_TO_MINUTES)) % 60);
                        //int hours = (int) ((since / (MILLS_TO_HOURS)) % 24); //this resets to  0 after 24 hour!
                        int hours = (int) ((since / (MILLS_TO_HOURS))); //this does not reset to 0!
                        int millis = (int) since % 1000; //the last 3 digits of millisecs

                        return String.format("%02d:%02d:%02d:%03d"
                                , hours, minutes, seconds, millis);

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (chronometerSub != null && !chronometerSub.isUnsubscribed()) {
            chronometerSub.unsubscribe();
        }
    }


}
