package com.example.finalproj_doctor.Adapter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproj_doctor.Model.Review;
import com.example.finalproj_doctor.R;
import com.example.finalproj_doctor.Ui.Qr_Scan.Qr_Scan;

import java.util.ArrayList;
import java.util.List;

public class Review_Adapter extends RecyclerView.Adapter<Review_Adapter.holderr> {

    private List<Review> arrayList = new ArrayList<>();


    @NonNull
    @Override
    public holderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, null);
        holderr viewHolder = new holderr(layout);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull holderr holder, final int position) {

        holder.name.setText(arrayList.get(position).getUsername());
        holder.rating.setRating(arrayList.get(position).getRating());
        holder.title.setText(arrayList.get(position).getTitle());
        holder.subject.setText(arrayList.get(position).getText());

    }

    public static class holderr extends RecyclerView.ViewHolder {

        TextView name , title , subject;
        RatingBar rating;

        public holderr(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            title = itemView.findViewById(R.id.title);
            subject = itemView.findViewById(R.id.subject);
            rating = itemView.findViewById(R.id.ratingBar);

        }
    }

    public void setList(List<Review> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
