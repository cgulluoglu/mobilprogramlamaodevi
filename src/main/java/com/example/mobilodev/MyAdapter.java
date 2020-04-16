package com.example.mobilodev;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder>{

    public Context c;
    public ArrayList<User> users;


    public MyAdapter(Context c, ArrayList<User> users) {
        this.c = c;
        this.users = users;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        myHolder.mUsername.setText(users.get(i).getUsername());
        myHolder.mPw.setText(users.get(i).getPassword());
        myHolder.mImageView.setImageResource(users.get(i).getImg());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
