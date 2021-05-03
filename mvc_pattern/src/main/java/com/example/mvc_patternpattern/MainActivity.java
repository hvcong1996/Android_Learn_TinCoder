package com.example.mvc_patternpattern;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    private void InitView(){
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewMessage = findViewById(R.id.textViewMessage);
    }

    private void Login(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        textViewMessage.setVisibility(View.VISIBLE);

        User user = new User(email, password);
        if(user.isValidEmail() && user.isValidPassword()){
            textViewMessage.setText("Login successful");
            textViewMessage.setTextColor(getResources().getColor(R.color.black));
        }
        else {
            textViewMessage.setText("Login fail");
        }
    }
}