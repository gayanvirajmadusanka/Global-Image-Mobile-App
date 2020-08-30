package com.example.globalimage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewHolderPackage extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolderPackage(View itemView) {
        super(itemView);

        mView = itemView;
    }

    //set details to recycler view row
    public void setDetails(Context ctx, String title, String description, String image){

        // views
        TextView mTitleView = mView.findViewById(R.id.rpTitleTv);
        TextView mDetailTv = mView.findViewById(R.id.rpDescriptionTv);
        ImageView mImageTv= mView.findViewById(R.id.rpImageView);

        // set data to views

        mTitleView.setText(title);
        mDetailTv.setText(description);
        Picasso.get().load(image).into(mImageTv);

    }
}
