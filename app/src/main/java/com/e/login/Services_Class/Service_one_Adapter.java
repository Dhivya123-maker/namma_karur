package com.e.login.Services_Class;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.R;
import com.e.login.ShopscreenClass.ShopScreenModel;
import com.e.login.ShopscreenClass.ShopsScreenFragment;

import java.util.List;

public class Service_one_Adapter extends RecyclerView.Adapter<Service_one_Adapter.ViewHolder> {



    List<Service_OneModel> serviceOneModelList;
    private Context context;

    public static ServiceAdapter.OnItemClickListener mListener1;




    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(ServiceAdapter.OnItemClickListener listener){

        mListener1 = listener;

    }


//    public static Service_one_Adapter.OnItemClickListener mListener1;
//
//
//
//    public interface OnItemClickListener{
//        void onItemClick(int position);
//    }
//
//    public void setOnItemClickListener(Service_One_activity listener){
//
//        mListener1 = (OnItemClickListener) listener;
//
//    }

    public Service_one_Adapter(Context context, List<Service_OneModel> serviceOneModelList) {
        this.context = context;
        this.serviceOneModelList=serviceOneModelList;
    }

    @NonNull
    @Override
    public Service_one_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fifty_six,parent,false);


        return new Service_one_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(serviceOneModelList.get(position).getText());
        holder.textView1.setText(serviceOneModelList.get(position).getText_one());
        holder.textView2.setText(serviceOneModelList.get(position).getText_two());
        holder.textView3.setText(serviceOneModelList.get(position).getText_three());
        holder.textView4.setText(serviceOneModelList.get(position).getText_four());

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
            img = itemView.findViewById(R.id.service_img);
            img1 = itemView.findViewById(R.id.service_star);
            img2 = itemView.findViewById(R.id.service_verify_img);
            img3 = itemView.findViewById(R.id.service_time_img);

            textView = itemView.findViewById(R.id.service_txt_one);
            textView1 = itemView.findViewById(R.id.service_txt_two);
            textView2 = itemView.findViewById(R.id.service_time_txt);
            textView3 = itemView.findViewById(R.id.service_verified_txt);
            textView4 = itemView.findViewById(R.id.service_txt_three);


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