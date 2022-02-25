package com.e.login.HospitalClass;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.R;
import com.e.login.TransportClass.Transport_Services_Model;

import java.util.List;

public class Hospital_ServicesFragmentAdapter extends RecyclerView.Adapter<Hospital_ServicesFragmentAdapter.ViewHolder> {



    List<Hospital_Services_Model> hospitalServicesModelList;
    private Context context;





    public static Hospital_ServicesFragmentAdapter.OnItemClickListener mListener;



    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(Hospital_ServicesFragmentAdapter.OnItemClickListener listener){

        mListener = listener;

    }

    public Hospital_ServicesFragmentAdapter(Context context, List<Hospital_Services_Model> hospitalServicesModelList) {
        this.context = context;
        this.hospitalServicesModelList = hospitalServicesModelList;
    }

    @NonNull
    @Override
    public Hospital_ServicesFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_seventy_two,parent,false);


        return new Hospital_ServicesFragmentAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(hospitalServicesModelList.get(position).getText());
        holder.textView1.setText(hospitalServicesModelList.get(position).getText_one());
       holder.btn.setText(hospitalServicesModelList.get(position).getButton());
        //holder.img.setImageResource(R.drawable.ac);

        Glide.with(context)
                .load(hospitalServicesModelList.get(position).getImage())
                .into(holder.img);

    }



    // total number of rows
    @Override
    public int getItemCount() {

        return hospitalServicesModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        Button btn;
        TextView textView, textView1;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.hospital);
           btn = itemView.findViewById(R.id.hoss_button);

            textView = itemView.findViewById(R.id.hoss_txt_one);
            textView1 = itemView.findViewById(R.id.hoss_txt_two);



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