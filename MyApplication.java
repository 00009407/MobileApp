package com.example.dictionary;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MyApplication extends Application {
//onCreate is used to start an activity. super is used to call the parent class constructor.//
// setContentView is used to set the xml.
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
