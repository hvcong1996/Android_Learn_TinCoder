package com.example.mvvm_pattern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.mvvm_pattern.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        LoginViewModel loginViewModel = new LoginViewModel();
        activityMainBinding.setLoginViewModel(loginViewModel);
    }
}