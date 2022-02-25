package com.e.login.Non_govt_Class;




import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.e.login.R;

import java.util.List;

public class Non_govt_Adapter extends RecyclerView.Adapter<Non_govt_Adapter.ViewHolder> {



    List<Non_govt_model> nonGovtModelList;
    private Context context;


    public Non_govt_Adapter(Context context, List<Non_govt_model> nonGovtModelList) {
        this.context = context;
        this.nonGovtModelList=nonGovtModelList;
    }

    @NonNull
    @Override
    public Non_govt_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fourty_five,parent,false);


        return new Non_govt_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(nonGovtModelList.get(position).getText());

        holder.img.setImageResource(R.drawable.fo);
    }




    // total number of rows
    @Override
    public int getItemCount() {

        return 3;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.hand_imgg);

            textView = itemView.findViewById(R.id.ngo_txtt);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(), NGO_two.class);
                    view.getContext().startActivity(intent);
                }
            });
//
        }



    }


}