package com.example.fragment_send_data_to_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ISendDataListener, IUpdateDataListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_content_first, new FirstFragment());
        fragmentTransaction.add(R.id.frame_content_second, new SecondFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void sendData(String email) {
        // FirstFragment callback to send data for SecondFragment
        SecondFragment secondFragment = (SecondFragment) getSupportFragmentManager().findFragmentById(R.id.frame_content_second);
        secondFragment.getDataFromFirstFragment(email);
    }

    @Override
    public void updateData(String emailUpdate) {
        // SecondFragment callback to update data for FirstFragment
        FirstFragment firstFragment = (FirstFragment) getSupportFragmentManager().findFragmentById(R.id.frame_content_first);
        firstFragment.getDataFromSecondFragment(emailUpdate);
    }
}