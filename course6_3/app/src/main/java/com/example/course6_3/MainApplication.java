package com.example.course6_3;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.Room;

import java.util.HashMap;

public class MainApplication extends Application {
    private static MainApplication mApp;
    public HashMap<String,String> infoMap = new HashMap<String, String>();
    private BookDatabase bookDatabase;
    public static MainApplication getInstance(){
        return mApp;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("legion","MainApplication onCreate");
        mApp = this;
        bookDatabase = Room.databaseBuilder(this,BookDatabase.class,"book")
                .addMigrations()
                .allowMainThreadQueries()
                .build();
    }
    public BookDatabase getBookDB() {return bookDatabase;}
    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d("legion","MainApplication onTerminate");
    }
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("legion","onConfigurationChanged");
    }
}
