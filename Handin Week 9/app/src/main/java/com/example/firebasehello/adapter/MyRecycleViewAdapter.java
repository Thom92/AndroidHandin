package com.example.firebasehello.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasehello.R;
import com.example.firebasehello.storage.MemoryStorage;
import com.example.firebasehello.viewHolder.ViewHolder;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout ll = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.simplerow, parent, false);
        return new ViewHolder(ll);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return MemoryStorage.notes.size();
    }
}