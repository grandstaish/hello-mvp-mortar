package com.example.bradcampbell.app;

import android.app.Application;

import com.squareup.mortar.MortarScope;

public class App extends Application {
    private MortarScope rootScope;

    @Override public Object getSystemService(String name) {
        if (rootScope == null) rootScope = MortarScope.buildRootScope().build("Root");

        return rootScope.hasService(name) ? rootScope.getService(name) : super.getSystemService(name);
    }
}
