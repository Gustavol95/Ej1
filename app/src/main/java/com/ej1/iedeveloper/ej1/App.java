package com.ej1.iedeveloper.ej1;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by iedeveloper on 27/12/16.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
