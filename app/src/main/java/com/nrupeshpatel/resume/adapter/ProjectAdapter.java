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

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.MyViewHolder> {

    private List<Project> projectList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView projectName, platform, projectDuration, projectLocation;
        public ImageView projectLogo;

        public MyViewHolder(View view) {
            super(view);
            projectName = (TextView) view.findViewById(R.id.projectName);
            platform = (TextView) view.findViewById(R.id.platform);
            projectDuration = (TextView) view.findViewById(R.id.projectDuration);
            projectLocation = (TextView) view.findViewById(R.id.projectLocation);
            projectLogo = (ImageView) view.findViewById(R.id.projectLogo);
        }
    }


    public ProjectAdapter(List<Project> projectList) {
        this.projectList = projectList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Project project = projectList.get(position);
        holder.projectName.setText(project.getName());
        holder.platform.setText(project.getPlatform());
        holder.projectDuration.setText(project.getDuration());
        holder.projectLocation.setText(project.getLocation());
        Glide.with(holder.projectLogo.getContext())
                .load(holder.projectLogo.getContext().getResources().getIdentifier(project.getLogo(), "drawable", holder.projectLogo.getContext().getPackageName()))
                .into(holder.projectLogo);
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }
}
