package com.example.activity_send_data_to_activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final int SEND_REQUEST_CODE = 900;
    private EditText edtEmail, edtUserName;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity();
            }
        });
    }

    private void initView(){
        edtEmail = findViewById(R.id.edt_email);
        edtUserName = findViewById(R.id.edt_user_name);
        btnSend = findViewById(R.id.btn_send);
    }

    private void nextActivity(){
        String strEmail = edtEmail.getText().toString().trim();
        String strUserName = edtUserName.getText().toString().trim();

        User user = new User(strEmail, strUserName);

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        // intent.putExtra("key_email", strEmail);

        Bundle bundle = new Bundle();
        bundle.putSerializable("object_user", user);

        intent.putExtras(bundle);

        // Start activity mà không cần get result
        // startActivity(intent);

        // Start activity và get result
        startActivityForResult(intent, SEND_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(SEND_REQUEST_CODE == requestCode && resultCode == Activity.RESULT_OK){
            // edtEmail.setText(data.getStringExtra("key_email"));

            User user = (User) data.getExtras().get("object_user");
            edtEmail.setText(user.getEmail());
            edtUserName.setText(user.getUserName());
        }
    }
}