package com.e.login.JobsClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.R;

import java.util.List;

public class View_activity_Adapter extends RecyclerView.Adapter<View_activity_Adapter.ViewHolder> {



    List<View_Activity_Model> view_activity_modelList;
    private Context context;


    public View_activity_Adapter(Context context, List<View_Activity_Model> view_activity_modelList) {
        this.context = context;
        this.view_activity_modelList =view_activity_modelList;
    }

    @NonNull
    @Override
    public View_activity_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jobs_view_recycle,parent,false);


        return new View_activity_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.job.setText(view_activity_modelList.get(position).getJob());
        holder.comp.setText(view_activity_modelList.get(position).getComp());
        holder.address.setText(view_activity_modelList.get(position).getAddress());
        holder.gender.setText(view_activity_modelList.get(position).getGender());
        holder.vacany.setText(view_activity_modelList.get(position).getVacancy());
        holder.degree.setText(view_activity_modelList.get(position).getDegree());
        holder.experience.setText(view_activity_modelList.get(position).getExp());
        holder.age.setText(view_activity_modelList.get(position).getAge());
        holder.salary.setText(view_activity_modelList.get(position).getSalary());
        holder.skill.setText(view_activity_modelList.get(position).getSkills());
        holder.start.setText(view_activity_modelList.get(position).getStart_end());
        holder.about.setText(view_activity_modelList.get(position).getAbt());
    }





    // total number of rows
    @Override
    public int getItemCount() {

        return view_activity_modelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView job,comp,address,gender,vacany,degree,experience,age,salary,skill,start,about;

        ViewHolder(View itemView) {
            super(itemView);

         job = itemView.findViewById(R.id.job_name);
         comp= itemView.findViewById(R.id.company_name);
         address= itemView.findViewById(R.id.address_name);
         gender= itemView.findViewById(R.id.gender);
         vacany= itemView.findViewById(R.id.vacancy_name);
         degree= itemView.findViewById(R.id.degree_name);
         experience= itemView.findViewById(R.id.exp);
         age= itemView.findViewById(R.id.age_name);
         salary= itemView.findViewById(R.id.salary);
         skill= itemView.findViewById(R.id.skill_name);
         start = itemView.findViewById(R.id.start_end);
         about= itemView.findViewById(R.id.about);




        }



    }



}
