package com.e.login.HospitalClass;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.R;
import com.e.login.TransportClass.TransportModel;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {



    List<HospitalModel> hospitalModelList;
    private Context context;


    public static OnItemClickListener mListener;




    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;

    }


    public HospitalAdapter(Context context, List<HospitalModel> hospitalModelList) {
        this.context = context;
        this.hospitalModelList =hospitalModelList;



    }




    @NonNull
    @Override
    public HospitalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_sixty_one,parent,false);


        return new ViewHolder(view);



    }


    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        String animal = mData.get(position);
//         holder.textView.setText(animal);

        holder.textView.setText(hospitalModelList.get(position).getText());
       holder.textView1.setText(hospitalModelList.get(position).getText_one());
      //  holder.imageView.setImageResource(shopModelList.get(position).getImage());
        holder.img1.setImageResource(R.drawable.eye_icon);

//        Glide.with(context)
//                .load(servicesModelList.get(position).getImage())
//                .into(holder.imageView);




    }

    // total number of rows
    @Override
    public int getItemCount() {

        return hospitalModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,img1;
        TextView textView, textView1;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.hos_image);




            img1 = itemView.findViewById(R.id.eye_icon4);

            textView = itemView.findViewById(R.id.hos_txt_one);
            textView1 = itemView.findViewById(R.id.hos_txt_two);


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

        }



    }


}