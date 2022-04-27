package com.e.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.BlankFragment.Blank_Model;
import com.e.login.HomeClass.Home;

import java.util.List;

public class ProfileAdapter  extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {


    List<ProfileModel> profileModelList;
    private Context context;


    public ProfileAdapter(Context context, List<ProfileModel> profileModelList) {
        this.context = context;
        this.profileModelList = profileModelList;
    }

    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_recyclerview, parent, false);


        return new ProfileAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {

        if (profileModelList.get(position).getType().equals("edu")) {
            holder.institute.setText(profileModelList.get(position).getInstitute());
            holder.degree.setText(profileModelList.get(position).getDegree());
            holder.year.setText(profileModelList.get(position).getYear());

            holder.education_view.setVisibility(View.VISIBLE);
            holder.experience_view.setVisibility(View.GONE);
            holder.skill_view.setVisibility(View.GONE);
        }
        if (profileModelList.get(position).getType().equals("exp")) {
            holder.company.setText(profileModelList.get(position).getCompany());
            holder.experience.setText(profileModelList.get(position).getExperience_data());
            holder.position.setText(profileModelList.get(position).getPosition());

            holder.education_view.setVisibility(View.GONE);
            holder.experience_view.setVisibility(View.VISIBLE);
            holder.skill_view.setVisibility(View.GONE);
        }

        if (profileModelList.get(position).getType().equals("ski")) {
            holder.skill.setText(profileModelList.get(position).getSkills_data());

            holder.education_view.setVisibility(View.GONE);
            holder.experience_view.setVisibility(View.GONE);
            holder.skill_view.setVisibility(View.VISIBLE);

        }
    }


    // total number of rows
    @Override
    public int getItemCount() {

        return profileModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView year,institute,degree,company,experience,position,skill;
        String type;
        LinearLayout education_view,experience_view,skill_view;

        ViewHolder(View itemView) {
            super(itemView);
            year = itemView.findViewById(R.id.year);
            institute = itemView.findViewById(R.id.institute);

            degree = itemView.findViewById(R.id.degree);
            company = itemView.findViewById(R.id.company);
            experience= itemView.findViewById(R.id.experience);
            position = itemView.findViewById(R.id.position);
            skill= itemView.findViewById(R.id.skill);


            education_view= itemView.findViewById(R.id.education_view);
            experience_view= itemView.findViewById(R.id.experience_view);
            skill_view= itemView.findViewById(R.id.skill_view);


        }


    }

}
