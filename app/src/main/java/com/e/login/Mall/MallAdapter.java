package com.e.login.Mall;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.BusTimeClass.BusTimeModel;
import com.e.login.R;
import com.e.login.SmallBusClass.SmallBusActivity;

import java.util.List;

public class MallAdapter extends RecyclerView.Adapter<MallAdapter.ViewHolder> {



    List<Mallmodel> mallmodelList;
    private Context context;


    public MallAdapter(Context context, List<Mallmodel> mallmodelList) {
        this.context = context;
        this.mallmodelList= mallmodelList;
    }

    @NonNull
    @Override
    public MallAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_twenty,parent,false);


        return new MallAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.img.setImageResource(R.drawable.brooke_mall);
    }


    // total number of rows
    @Override
    public int getItemCount() {

        return 3;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.mall_first_img);
            txt = itemView.findViewById(R.id.brooke_txt);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),Malls_One.class);
                    view.getContext().startActivity(intent);
                }
            });

        }


        }



    }


