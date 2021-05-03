package com.example.room_database;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.room_database.database.UserDatabase;

public class UpdateUserActivity extends AppCompatActivity {

    private EditText editTextUserName, editTextDescription;

    private Button buttonUpdateUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        InitView();

        // Get user from MainActivity by Intent
        User user = (User) getIntent().getExtras().get("user");
        if(user != null){
            editTextUserName.setText(user.getName());
            editTextDescription.setText(user.getDescription());
        }

        buttonUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateUser(user);
            }
        });
    }

    private void InitView(){
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextDescription = findViewById(R.id.editTextDescription);

        buttonUpdateUser = findViewById(R.id.buttonUpdateUser);
    }

    private void UpdateUser(User user){
        String userName = editTextUserName.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();

        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(description)){
            Toast.makeText(UpdateUserActivity.this, "The user name or description is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Update user for database
        user.setName(userName);
        user.setDescription(description);
        // user Entity được update là user được get từ MainAcitivity
        UserDatabase.getInstance(UpdateUserActivity.this).iUserDAO().UpdateUser(user);

        Toast.makeText(UpdateUserActivity.this, "Update user successful", Toast.LENGTH_SHORT).show();

        // Return intentResult for MainActivity
        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK, intentResult);

        // Destroy activity
        finish();
    }
}