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
import com.e.login.TransportClass.Transport_OneModel;

import java.util.List;

public class Hospital_one_Adapter extends RecyclerView.Adapter<Hospital_one_Adapter.ViewHolder> {



    List<Hospital_OneModel> hospitalOneModelList;
    private Context context;

    public static Hospital_one_Adapter.OnItemClickListener mListener1;




    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(Hospital_one_Adapter.OnItemClickListener listener){

        mListener1 = listener;

    }




    public Hospital_one_Adapter(Context context, List<Hospital_OneModel> hospitalOneModelList) {
        this.context = context;
        this.hospitalOneModelList = hospitalOneModelList;
    }

    @NonNull
    @Override
    public Hospital_one_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_sixty_two,parent,false);


        return new Hospital_one_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(hospitalOneModelList.get(position).getText());
        holder.textView1.setText(hospitalOneModelList.get(position).getText_one());
        holder.textView2.setText(hospitalOneModelList.get(position).getText_two());
        holder.textView3.setText(hospitalOneModelList.get(position).getText_three());
        holder.textView4.setText(hospitalOneModelList.get(position).getText_four());

//        holder.img.setImageResource(R.drawable.ac_one);
//
//        Glide.with(context)
//                .load(shopScreenModelList.get(position).getImage())
//                .into(holder.img);

        holder.img1.setImageResource(R.drawable.starr);
        holder.img2.setImageResource(R.drawable.verified);
        holder.img3.setImageResource(R.drawable.clock);

    }


    // total number of rows
    @Override
    public int getItemCount() {

        return 3;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1,img2,img3;
        TextView textView, textView1,textView2,textView3,textView4;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.hospital_img);
            img1 = itemView.findViewById(R.id.hospital_star);
            img2 = itemView.findViewById(R.id.hospital_verify_img);
            img3 = itemView.findViewById(R.id.hospital_time_img);

            textView = itemView.findViewById(R.id.hospital_txt_one);
            textView1 = itemView.findViewById(R.id.hospital_txt_two);
            textView2 = itemView.findViewById(R.id.hospital_time_txt);
            textView3 = itemView.findViewById(R.id.hospital_verified_txt);
            textView4 = itemView.findViewById(R.id.hospital_txt_three);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener1 != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener1.onItemClick(position);
                        }
                    }

                }
            });

        }



    }


}