package com.e.login.Profile_details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.Profile;
import com.e.login.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.ViewHolder> {



    List<Education_Model> educationModelList;
    private Context context;


    public EducationAdapter(Context context, List<Education_Model> educationModelList, ArrayList<Map<String, Object>> itemDataList) {
        this.context = context;
        this.educationModelList = educationModelList;
    }



    @NonNull
    @Override
    public EducationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popup_input_dialog,parent,false);


        return new EducationAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(educationModelList.get(position).getIns());
        holder.textView1.setText(educationModelList.get(position).getDeg());
        holder.textView2.setText(educationModelList.get(position).getYear());

    }




    // total number of rows
    @Override
    public int getItemCount() {

        return educationModelList.size();

    }



    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {

        EditText textView, textView1,textView2;


        ViewHolder(View itemView) {
            super(itemView);


            textView = itemView.findViewById(R.id.Institute);
            textView1 = itemView.findViewById(R.id.Degree);
            textView2 = itemView.findViewById(R.id.year);







        }



    }


}