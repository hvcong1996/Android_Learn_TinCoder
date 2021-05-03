package com.example.room_database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;

    private IClickItemUser iClickItemUser;

    public UserAdapter(IClickItemUser iClickItemUser) {
        this.iClickItemUser = iClickItemUser;
    }

    public void SetData(List<User> users){
        userList = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);

        if(user == null){
            return;
        }

        holder.textViewName.setText(user.getName());
        holder.textViewDescription.setText(user.getDescription());
        holder.textViewYear.setText(user.getYear());

        holder.buttonUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemUser.updateUser(user);
            }
        });

        holder.buttonDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemUser.deleteUser(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(userList != null) return userList.size();

        return 0;
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewName,textViewDescription, textViewYear;
        private Button buttonUpdateUser, buttonDeleteUser;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            InitView(itemView);
        }

        private void InitView(View view){
            textViewName = view.findViewById(R.id.textViewUserName);
            textViewDescription = view.findViewById(R.id.textViewDescription);
            textViewYear = view.findViewById(R.id.textViewYear);
            buttonUpdateUser = view.findViewById(R.id.buttonUpdateUser);
            buttonDeleteUser = view.findViewById(R.id.buttonDeleteUser);
        }
    }

}
