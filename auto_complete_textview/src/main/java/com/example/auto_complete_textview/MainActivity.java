package com.example.auto_complete_textview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();

        String[] countries = getResources().getStringArray(R.array.countries);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.auto_complete_textview_layout_custom, R.id.textViewCountryName, countries);
        autoCompleteTextView.setAdapter(adapter);
    }

    private void InitView(){
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
    }
}