package com.example.roonrxjava;

import android.app.Application;
import android.content.Context;

import com.example.roonrxjava.db.PersonDatabase;

import static com.example.roonrxjava.db.PersonDatabase.getPersonInstance;

public class MyApplication extends Application {

    private static PersonDatabase personDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        Context context = getApplicationContext();
        personDatabase = getPersonInstance(context);
    }

    public static PersonDatabase getPersonDatabase(){
        return personDatabase;
    }
}
