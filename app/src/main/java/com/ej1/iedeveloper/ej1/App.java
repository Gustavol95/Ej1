package com.ej1.iedeveloper.ej1;

import android.app.Application;

import com.ej1.iedeveloper.ej1.greendao.DaoMaster;
import com.ej1.iedeveloper.ej1.greendao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by iedeveloper on 02/01/17.
 */


public class App extends Application {

    private DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,"db-test");
        Database db=helper.getWritableDb();
        daoSession=new DaoMaster(db).newSession();

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
