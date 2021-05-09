package com.example.activity_send_data_to_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements ISendDataListener{

    private EditText edtEmail, edtName;
    private Button btnSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToFragment();
            }
        });
    }

    private void InitView(){
        edtEmail = findViewById(R.id.edt_email);
        edtName = findViewById(R.id.edt_name);
        btnSend = findViewById(R.id.btn_send);
    }

    private void sendDataToFragment() {
        String strEmail = edtEmail.getText().toString().trim();
        String strName = edtName.getText().toString().trim();

        User user = new User(strEmail, strName);


        HomeFragment homeFragment = new HomeFragment();

        Bundle bundle = new Bundle();
        // bundle.putString("key_email", strEmail);
        bundle.putSerializable("object_user", user);

        homeFragment.setArguments(bundle);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // replace(layout, fragment để load vào layout)
        fragmentTransaction.replace(R.id.frame_content, homeFragment);
        fragmentTransaction.commit();

    }

    @Override
    public void sendData(User user) {
        edtEmail.setText(user.getEmail());
        edtName.setText(user.getName());
    }
}