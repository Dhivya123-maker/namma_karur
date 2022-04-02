package com.e.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Popup_Adapter extends RecyclerView.Adapter<Popup_Adapter.MyViewHolder>{

    private Context mContext;
    private ArrayList<String> data;

    public Popup_Adapter(Context mContext, ArrayList<String> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.popup, parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.ins.setText(data.get(position));
//        holder.deg.setText(data.get(position));
//        holder.year.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    //View Holder
    public class MyViewHolder extends RecyclerView.ViewHolder {

       TextView ins;

        public MyViewHolder(View itemView) {
            super(itemView);

            ins = itemView.findViewById(R.id.inss);
//            deg = itemView.findViewById(R.id.deg);
//            year = itemView.findViewById(R.id.yr);


        }
    }
}