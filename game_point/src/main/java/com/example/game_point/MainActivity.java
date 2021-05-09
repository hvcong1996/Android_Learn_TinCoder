package com.example.game_point;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import org.reactivestreams.Subscriber;
import java.util.Observable;

public class MainActivity extends AppCompatActivity {

    EditText edtData;
    TextView txtValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();



    }

    private void InitView(){
        edtData = findViewById(R.id.edt_data);
        txtValue = findViewById(R.id.txt_value);
    }

    private void Execute(){
    }

}