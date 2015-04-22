package com.example.bradcampbell.app;

import android.os.Bundle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.squareup.mortar.ViewPresenter;

class HelloPresenter1 extends ViewPresenter<HelloView1> {
    private final DateFormat format = new SimpleDateFormat();
    private int serial = -1;

    @Override protected void onLoad(Bundle savedInstanceState) {
        if (savedInstanceState != null && serial == -1) serial = savedInstanceState.getInt("serial");
        getView().show("Update #" + ++serial + " at " + format.format(new Date()));
    }

    @Override protected void onSave(Bundle outState) {
        outState.putInt("serial", serial);
    }

    public void buttonPressed() {
        getView().goToNextScreen();
    }
}