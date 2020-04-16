package com.example.mobilodev;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {

    ImageView mImageView;
    TextView mUsername, mPw;

    public MyHolder(@NonNull View itemView){
        super(itemView);

        this.mImageView = itemView.findViewById(R.id.imageIv);
        this.mUsername = itemView.findViewById(R.id.usernameTv);
        this.mPw = itemView.findViewById(R.id.passwordTv);
    }
}
