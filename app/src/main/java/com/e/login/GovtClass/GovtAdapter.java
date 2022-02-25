package com.e.login.GovtClass;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.e.login.R;

import java.util.List;

public class GovtAdapter extends RecyclerView.Adapter<com.e.login.GovtClass.GovtAdapter.ViewHolder> {



    List<GovtModel> govtModelList;
    private Context context;


    public GovtAdapter(Context context, List<GovtModel> govtModelList) {
        this.context = context;
        this.govtModelList=govtModelList;
    }

    @NonNull
    @Override
    public com.e.login.GovtClass.GovtAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_seven,parent,false);


        return new com.e.login.GovtClass.GovtAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(govtModelList.get(position).getText());

//        holder.img.setImageResource(R.drawable.fo);
        Glide.with(context).load(govtModelList.get(position).getImage()).into(holder.img);


    }




    // total number of rows
    @Override
    public int getItemCount() {

        return govtModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView, textView1;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.hand_img);

            textView = itemView.findViewById(R.id.ngo_txt);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();

                    String id = govtModelList.get(position).getId();

                    Intent intent = new Intent(view.getContext(), NGOActivity.class);
                    intent.putExtra("id",id);
                    view.getContext().startActivity(intent);
                }
            });
//
       }



    }


}