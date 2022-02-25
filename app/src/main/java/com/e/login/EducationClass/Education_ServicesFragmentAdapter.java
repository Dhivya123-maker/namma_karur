package com.e.login.EducationClass;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.R;
import com.e.login.Services_Class.Fragment_Model;

import java.util.List;

public class Education_ServicesFragmentAdapter extends RecyclerView.Adapter<Education_ServicesFragmentAdapter.ViewHolder> {



    List<Education_Services_Model> educationServicesModelList;
    private Context context;





    public static Education_ServicesFragmentAdapter.OnItemClickListener mListener;



    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(Education_ServicesFragmentAdapter.OnItemClickListener listener){

        mListener = listener;

    }

    public Education_ServicesFragmentAdapter(Context context, List<Education_Services_Model> educationServicesModelList) {
        this.context = context;
        this.educationServicesModelList = educationServicesModelList;
    }

    @NonNull
    @Override
    public Education_ServicesFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_sixty_eight,parent,false);


        return new Education_ServicesFragmentAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(educationServicesModelList.get(position).getText());
        holder.textView1.setText(educationServicesModelList.get(position).getText_one());
       holder.btn.setText(educationServicesModelList.get(position).getButton());
        //holder.img.setImageResource(R.drawable.ac);

        Glide.with(context)
                .load(educationServicesModelList.get(position).getImage())
                .into(holder.img);

    }



    // total number of rows
    @Override
    public int getItemCount() {

        return educationServicesModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        Button btn;
        TextView textView, textView1;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.educationn);
           btn = itemView.findViewById(R.id.eduu_button);

            textView = itemView.findViewById(R.id.eduu_txt_one);
            textView1 = itemView.findViewById(R.id.eduu_txt_two);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
//                    Intent intent = new Intent(view.getContext(),Carrier_Activity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                    view.getContext().startActivity(intent);


        }



    }


}