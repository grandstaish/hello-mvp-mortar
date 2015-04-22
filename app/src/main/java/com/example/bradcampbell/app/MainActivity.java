package com.example.bradcampbell.app;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.os.Bundle;

import com.squareup.mortar.MortarScope;
import com.squareup.mortar.bundler.BundleServiceRunner;

import static com.squareup.mortar.MortarScope.buildChild;
import static com.squareup.mortar.MortarScope.findChild;

public class MainActivity extends Activity implements HelloView1.Callbacks {
    @Override public Object getSystemService(@NonNull String name) {
        MortarScope activityScope = findChild(getApplicationContext(), getScopeName());
        if (activityScope == null) {
            activityScope = buildChild(getApplicationContext()) //
                    .withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
                    .build(getScopeName());
        }
        return activityScope.hasService(name) ? activityScope.getService(name)
                : super.getSystemService(name);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BundleServiceRunner.getBundleServiceRunner(this).onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.root, new HelloFragment1())
                    .commit();
        }
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BundleServiceRunner.getBundleServiceRunner(this).onSaveInstanceState(outState);
    }

    @Override protected void onDestroy() {
        if (isFinishing()) {
            MortarScope activityScope = findChild(getApplicationContext(), getScopeName());
            if (activityScope != null) activityScope.destroy();
        }
        super.onDestroy();
    }

    private String getScopeName() {
        return getClass().getName();
    }

    @Override public void onButtonPressed() {
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.root, new HelloFragment2())
                .commit();
    }
}
