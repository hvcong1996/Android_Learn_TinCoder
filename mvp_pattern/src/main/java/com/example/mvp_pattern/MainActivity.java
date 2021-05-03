package com.example.mvp_pattern;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ILogin{

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private TextView textViewMessage;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();

        loginPresenter = new LoginPresenter(MainActivity.this);

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

        User user = new User(email, password);

        loginPresenter.Login(user);
    }

    @Override
    public void LoginSuccess() {
        textViewMessage.setVisibility(View.VISIBLE);

        textViewMessage.setText("Login successful");
        textViewMessage.setTextColor(getResources().getColor(R.color.black));
    }

    @Override
    public void LoginFail() {
        textViewMessage.setVisibility(View.VISIBLE);

        textViewMessage.setText("Login fail");
    }
}