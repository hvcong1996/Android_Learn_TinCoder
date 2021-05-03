package com.example.room_database;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.room_database.database.UserDatabase;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int UPDATE_USER_REQUEST_CODE = 900;
    private EditText editTextUserName, editTextDescription, editTextYear, editTextSearch;
    private Button buttonAddUser;
    private TextView textViewDeleteAll;
    private RecyclerView recyclerView;

    private List<User> userList;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();

        userList = new ArrayList<>();

        userAdapter = new UserAdapter(new IClickItemUser() {
            @Override
            public void updateUser(User user) {
                UpdateUser(user);
            }

            @Override
            public void deleteUser(User user) {
                DeleteUser(user);
            }
        });
        userAdapter.SetData(userList);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(userAdapter);

        LoadUser();

        buttonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddUser();
            }
        });

        textViewDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteAllUser();
            }
        });

        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // Event khi user click vào button seach trên keyboard
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    // Search
                    SearchUser();
                }

                return false;
            }
        });
    }

    private void InitView(){
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextYear = findViewById(R.id.editTextYear);
        buttonAddUser = findViewById(R.id.buttonAddUser);
        textViewDeleteAll = findViewById(R.id.textViewDeleteAllUser);
        editTextSearch = findViewById(R.id.editTextSearch);
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void AddUser(){
        String userName = editTextUserName.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String year = editTextYear.getText().toString().trim();

        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(description)){
            Toast.makeText(MainActivity.this, "The user name or description is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(userName, description, year);

        // Nếu tồn tại thì không add user
        if(IsExistUser(user)){
            Toast.makeText(MainActivity.this, "Exist user, can't create the user", Toast.LENGTH_SHORT).show();
            return;
        }

        // Add user to database
        UserDatabase.getInstance(MainActivity.this).iUserDAO().insertUser(user);

        Toast.makeText(MainActivity.this, "Add user successful", Toast.LENGTH_SHORT).show();

        // Clear userName and password in View
        editTextUserName.setText("");
        editTextDescription.setText("");
        editTextYear.setText("");

        // Close keyboard
        HideKeyBoard();

        // Show data on recyclerView
        LoadUser();
    }

    private void LoadUser(){
        // Show data on recyclerView
        userList = UserDatabase.getInstance(MainActivity.this).iUserDAO().getUsers();
        userAdapter.SetData(userList);
    }

    private boolean IsExistUser(User user){
        boolean isExist = false;
        List<User> users = UserDatabase.getInstance(MainActivity.this).iUserDAO().checkUser(user.getName());

        if(users != null && !users.isEmpty()){
            isExist = true;
        }

        return isExist;
    }

    private void UpdateUser(User user){
        Intent intent = new Intent(MainActivity.this, UpdateUserActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);

        intent.putExtras(bundle);
        startActivityForResult(intent, UPDATE_USER_REQUEST_CODE);
    }

    private void DeleteUser(User user){
        // Show alertDiaLog to confirm
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Confirm Delete User")
                .setMessage("Are you sure delete user " + user.getName() + " ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Delete user
                        UserDatabase.getInstance(MainActivity.this).iUserDAO().DeleteUser(user);

                        Toast.makeText(MainActivity.this, "Delete user successful", Toast.LENGTH_SHORT).show();

                        LoadUser();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void DeleteAllUser(){
        // Show alertDiaLog to confirm
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Confirm Delete User")
                .setMessage("Are you sure delete all users ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Delete user
                        UserDatabase.getInstance(MainActivity.this).iUserDAO().DeleteAllUser();

                        Toast.makeText(MainActivity.this, "Delete all user successful", Toast.LENGTH_SHORT).show();

                        LoadUser();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void SearchUser(){
        String userName = editTextSearch.getText().toString().trim();
        List<User> users = UserDatabase.getInstance(MainActivity.this).iUserDAO().SearchUser(userName);

        // Clear list user hiện tại và set = list user search
        userList.clear();
        userList = users;

        userAdapter.SetData(userList);

        HideKeyBoard();
    }

    private void HideKeyBoard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    // Method callback để lấy all IntentResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == UPDATE_USER_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            LoadUser();
        }
    }
}