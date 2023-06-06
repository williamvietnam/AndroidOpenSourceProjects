package com.android.container;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.R;
import com.android.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}