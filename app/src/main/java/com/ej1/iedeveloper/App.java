package com.ej1.iedeveloper;

import android.app.Application;


import com.ej1.iedeveloper.db.DaoMaster;
import com.ej1.iedeveloper.db.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by iedeveloper on 27/12/16.
 */

public class App extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,"db-example");
        Database db=helper.getWritableDb();
        daoSession=new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
