package com.example.press_back_again;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private long backPressTime;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        if(backPressTime + 2000 > System.currentTimeMillis()){
            toast.cancel();
            super.onBackPressed();
            return;
        }
        else {
            toast = Toast.makeText(MainActivity.this, "Press back again to exit the application", Toast.LENGTH_SHORT);
            toast.show();
        }
        backPressTime = System.currentTimeMillis();
    }
}