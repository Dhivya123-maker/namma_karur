package com.e.login.TransportClass;


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
import com.e.login.EducationClass.Education_Services_Model;
import com.e.login.R;

import java.util.List;

public class Transport_ServicesFragmentAdapter extends RecyclerView.Adapter<Transport_ServicesFragmentAdapter.ViewHolder> {



    List<Transport_Services_Model> transportServicesModelList;
    private Context context;





    public static Transport_ServicesFragmentAdapter.OnItemClickListener mListener;



    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(Transport_ServicesFragmentAdapter.OnItemClickListener listener){

        mListener = listener;

    }

    public Transport_ServicesFragmentAdapter(Context context, List<Transport_Services_Model> transportServicesModelList) {
        this.context = context;
        this.transportServicesModelList = transportServicesModelList;
    }

    @NonNull
    @Override
    public Transport_ServicesFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_seventy,parent,false);


        return new Transport_ServicesFragmentAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(transportServicesModelList.get(position).getText());
        holder.textView1.setText(transportServicesModelList.get(position).getText_one());
       holder.btn.setText(transportServicesModelList.get(position).getButton());
        //holder.img.setImageResource(R.drawable.ac);

        Glide.with(context)
                .load(transportServicesModelList.get(position).getImage())
                .into(holder.img);

    }



    // total number of rows
    @Override
    public int getItemCount() {

        return transportServicesModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        Button btn;
        TextView textView, textView1;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.transportt);
           btn = itemView.findViewById(R.id.transs_button);

            textView = itemView.findViewById(R.id.transs_txt_one);
            textView1 = itemView.findViewById(R.id.transs_txt_two);



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