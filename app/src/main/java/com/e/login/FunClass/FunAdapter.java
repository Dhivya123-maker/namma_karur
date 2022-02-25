package com.e.login.FunClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.Articles;
import com.e.login.BusTimeClass.BusTimeModel;
import com.e.login.R;
import com.e.login.SmallBusClass.SmallBusActivity;

import java.util.List;

public class FunAdapter extends RecyclerView.Adapter<com.e.login.FunClass.FunAdapter.ViewHolder> {



    List<FunModel> funModelList;
    private Context context;


    public FunAdapter(Context context, List<FunModel> funModelList) {
        this.context = context;
        this.funModelList = funModelList;
    }

    @NonNull
    @Override
    public com.e.login.FunClass.FunAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_eleven,parent,false);


        return new com.e.login.FunClass.FunAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(funModelList.get(position).getText());

        holder.img.setImageResource(R.drawable.articles);
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
            img = itemView.findViewById(R.id.articles_img);

            textView = itemView.findViewById(R.id.articles_txt);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(), Articles.class);
                    view.getContext().startActivity(intent);
                }
            });

        }



    }


}