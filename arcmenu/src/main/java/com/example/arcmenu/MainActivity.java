package com.example.arcmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.sa90.materialarcmenu.ArcMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUser;
    private ArcMenu arcMenu;
    private UserAdapter userAdapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         InitView();

        // Xử lý ẩn arcMenu khi user roll xuống
        recyclerViewUser.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // Roll xuống
                if(dy > 0) {
                    arcMenu.setVisibility(View.GONE);
                }
                else // Roll lên
                {
                    arcMenu.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void InitView(){
        recyclerViewUser = (RecyclerView) findViewById(R.id.recyclerViewUser);
        arcMenu = (ArcMenu) findViewById(R.id.arcMenu);

        userAdapter = new UserAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerViewUser.setLayoutManager(linearLayoutManager);

        userList = GetUsers();

        userAdapter.SetData(userList);

        recyclerViewUser.setAdapter(userAdapter);
    }

    private List<User> GetUsers(){
        List<User> users = new ArrayList<>();

        for (int i = 1; i <= 20; i++){
            users.add(new User("The User" + i));
        }

        return users;
    }
}