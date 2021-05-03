package com.example.swipe_refresh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> itemList;

    public void SetData(List<Item> items){
        itemList = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);

        if(item == null){
            return;
        }

        holder.textViewName.setText(item.getName());
    }

    @Override
    public int getItemCount() {

        if(itemList != null){
            return itemList.size();
        }

        return 0;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewName;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            InitView(itemView);
        }

        private void InitView(View view){
            textViewName = view.findViewById(R.id.textView);
        }
    }
}
