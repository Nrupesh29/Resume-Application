package com.nrupeshpatel.resume.adapter;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nrupeshpatel.resume.R;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.MyViewHolder> {

    private List<Education> educationList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView instituteName, degree, educationDuration, instituteLocation;
        public ImageView instituteLogo;

        public MyViewHolder(View view) {
            super(view);
            instituteName = (TextView) view.findViewById(R.id.instituteName);
            degree = (TextView) view.findViewById(R.id.degree);
            educationDuration = (TextView) view.findViewById(R.id.educationDuration);
            instituteLocation = (TextView) view.findViewById(R.id.instituteLocation);
            instituteLogo = (ImageView) view.findViewById(R.id.instituteLogo);
        }
    }


    public EducationAdapter(List<Education> educationList) {
        this.educationList = educationList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.education_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Education education = educationList.get(position);
        holder.instituteName.setText(education.getName());
        holder.degree.setText(education.getDegree());
        holder.educationDuration.setText(education.getDuration());
        holder.instituteLocation.setText(education.getLocation());
        Glide.with(holder.instituteLogo.getContext())
                .load(holder.instituteLogo.getContext().getResources().getIdentifier(education.getLogo(), "drawable", holder.instituteLogo.getContext().getPackageName()))
                .into(holder.instituteLogo);
    }

    @Override
    public int getItemCount() {
        return educationList.size();
    }
}
