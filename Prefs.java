package com.example.dictionary.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private Context context;
    private SharedPreferences sharedPreferences;

    public Prefs(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("Prefs1", Context.MODE_PRIVATE);
    }

    private boolean isFirstUser = true;

    public void setFirstUser(boolean value) {
        sharedPreferences.edit().putBoolean("user", value).apply();
    }

    public boolean getFirstUser() {
        return sharedPreferences.getBoolean("user", isFirstUser);
    }

}


