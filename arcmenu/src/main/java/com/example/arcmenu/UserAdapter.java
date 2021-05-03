package com.example.arcmenu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<User> userList;

    public void SetData(List<User> users){
        userList = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        // Init view item_user
        View view = layoutInflater. inflate(R.layout.item_user, parent, false);

        // Return userViewHolder
        UserViewHolder userViewHolder = new UserViewHolder(view);

        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.textViewName.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        if(userList != null){
            return userList.size();
        }

        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            InitView(itemView);
        }

        private void InitView(View view){
            textViewName = (TextView) view.findViewById(R.id.textViewName);
        }
    }
}
