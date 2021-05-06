package com.example.activity_send_data_to_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    private EditText edtEmail, edtUserName;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        initView();

        // edtEmail.setText(getIntent().getStringExtra("key_email"));

        User user = (User) getIntent().getExtras().get("object_user");

        // Set to View
        edtEmail.setText(user.getEmail());
        edtUserName.setText(user.getUserName());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity();
            }
        });
    }

    private void initView(){
        edtEmail = findViewById(R.id.edt_email);
        edtUserName = findViewById(R.id.edt_user_name);
        btnUpdate = findViewById(R.id.btn_update);
    }

    private void backActivity() {

        String strEmailUpdate = edtEmail.getText().toString().trim();
        String strUserNameUpdate = edtUserName.getText().toString().trim();

        Intent returnIntent = new Intent();
        // returnIntent.putExtra("key_email", strEmailUpdate);

        User user = new User(strEmailUpdate, strUserNameUpdate);

        Bundle bundle = new Bundle();
        bundle.putSerializable("object_user", user);

        returnIntent.putExtras(bundle);

        setResult(Activity.RESULT_OK, returnIntent);

        finish();
    }
}