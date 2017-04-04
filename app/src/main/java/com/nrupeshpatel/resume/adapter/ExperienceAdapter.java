package com.nrupeshpatel.resume.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nrupeshpatel.resume.R;

import java.util.List;

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.MyViewHolder> {

    private List<Experience> experienceList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView companyName, position, companyDuration, companyLocation;
        public ImageView companyLogo;

        public MyViewHolder(View view) {
            super(view);
            companyName = (TextView) view.findViewById(R.id.companyName);
            position = (TextView) view.findViewById(R.id.position);
            companyDuration = (TextView) view.findViewById(R.id.companyDuration);
            companyLocation = (TextView) view.findViewById(R.id.companyLocation);
            companyLogo = (ImageView) view.findViewById(R.id.companyLogo);
        }
    }


    public ExperienceAdapter(List<Experience> experienceList) {
        this.experienceList = experienceList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.experience_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Experience experience = experienceList.get(position);
        holder.companyName.setText(experience.getName());
        holder.position.setText(experience.getPosition());
        holder.companyDuration.setText(experience.getDuration());
        holder.companyLocation.setText(experience.getLocation());
        Glide.with(holder.companyLogo.getContext())
                .load(holder.companyLogo.getContext().getResources().getIdentifier(experience.getLogo(), "drawable", holder.companyLogo.getContext().getPackageName()))
                .into(holder.companyLogo);
    }

    @Override
    public int getItemCount() {
        return experienceList.size();
    }
}
