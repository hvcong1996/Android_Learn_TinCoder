package com.example.swipe_refresh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        itemAdapter = new ItemAdapter();

        // First data : 10 item
        List<Item> items = GetItems(10);

        itemAdapter.SetData(items);

        recyclerView.setAdapter(itemAdapter);

        // Tạo line giữa các item
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        // Set Event Refresh
        swipeRefreshLayout.setOnRefreshListener(MainActivity.this);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.design_default_color_secondary_variant));
    }

    private void InitView(){
        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
    }

    private List<Item> GetItems(int data){
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < data; i++){
            items.add(new Item("Item " + i));
        }

        return items;
    }

    @Override
    public void onRefresh() {

        // Refresh data : 20 item
        List<Item> items = GetItems(20);

        itemAdapter.SetData(items);

        swipeRefreshLayout.setRefreshing(false);

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                // Refresh data : 20 item
//                List<Item> items = GetItems(20);
//
//                itemAdapter.SetData(items);
//
//                swipeRefreshLayout.setRefreshing(false);
//
//            }
//        }, 1000);
    }
}