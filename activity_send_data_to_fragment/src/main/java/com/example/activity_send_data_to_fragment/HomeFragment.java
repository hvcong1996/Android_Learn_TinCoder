package com.example.activity_send_data_to_fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class HomeFragment extends Fragment {

    private EditText edtEmail, edtName;
    private Button btnUpdate;

    private ISendDataListener iSendDataListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        iSendDataListener = (MainActivity) getActivity();


        InitView(view);

        // Get data từ Activity to set for Fragment
        // edtEmail.setText(getArguments().getString("key_email"));

        User user = (User) getArguments().get("object_user");
        edtEmail.setText(user.getEmail());
        edtName.setText(user.getName());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToActivity();
            }
        });

        return view;
    }

    private void InitView(View view) {
        edtEmail = view.findViewById(R.id.edt_email);
        edtName = view.findViewById(R.id.edt_name);
        btnUpdate = view.findViewById(R.id.btn_update);
    }

    private void sendDataToActivity() {
        String strEmailUpdate = edtEmail.getText().toString().trim();
        String strNameUpdate = edtName.getText().toString().trim();

        User user = new User(strEmailUpdate, strNameUpdate);

        iSendDataListener.sendData(user);
    }
}