package com.example.bradcampbell.app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.mortar.MortarScope;

import static com.squareup.mortar.MortarScope.buildChild;
import static com.squareup.mortar.MortarScope.findChild;

public class HelloFragment1 extends Fragment {
    private boolean isDestroyedBySystem = false;
    private MortarScope fragmentScope;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentScope = findChild(getActivity(), getScopeName());
        if (fragmentScope == null) {
            fragmentScope = buildChild(getActivity())
                    .withService(HelloPresenter1.class.getName(), new HelloPresenter1())
                    .build(getScopeName());
        }
    }

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return LayoutInflater.from(fragmentScope.createContext(getActivity()))
                .inflate(R.layout.fragment_hello1, container, false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        isDestroyedBySystem = true;
    }

    @Override public void onDestroy() {
        super.onDestroy();
        if (!isDestroyedBySystem) {
            fragmentScope.destroy();
        }
    }

    private String getScopeName() {
        return getClass().getName();
    }
}
