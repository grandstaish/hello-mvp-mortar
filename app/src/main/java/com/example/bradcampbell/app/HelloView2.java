package com.example.bradcampbell.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HelloView2 extends LinearLayout {
    private final HelloPresenter2 presenter;

    private TextView textView;

    public HelloView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        presenter = (HelloPresenter2) context.getSystemService(HelloPresenter2.class.getName());
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        textView = (TextView) findViewById(R.id.text);
    }

    @Override protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.takeView(this);
    }

    @Override protected void onDetachedFromWindow() {
        presenter.dropView(this);
        super.onDetachedFromWindow();
    }

    public void show(CharSequence stuff) {
        textView.setText(stuff);
    }
}
