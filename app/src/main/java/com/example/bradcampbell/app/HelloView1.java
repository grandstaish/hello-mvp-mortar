package com.example.bradcampbell.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HelloView1 extends LinearLayout {
    private final HelloPresenter1 presenter;
    private final Callbacks callbacks;

    private TextView textView;
    private Button button;

    public HelloView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        presenter = (HelloPresenter1) context.getSystemService(HelloPresenter1.class.getName());
        callbacks = (Callbacks) ((ContextWrapper)context).getBaseContext();
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        textView = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);
    }

    @Override protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.takeView(this);
        button.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(@NonNull View v) {
                    presenter.buttonPressed();
                }
            });
    }

    @Override protected void onDetachedFromWindow() {
        presenter.dropView(this);
        super.onDetachedFromWindow();
    }

    public void show(CharSequence stuff) {
        textView.setText(stuff);
    }

    public void goToNextScreen() {
        callbacks.onButtonPressed();
    }

    public interface Callbacks {
        void onButtonPressed();
    }
}
