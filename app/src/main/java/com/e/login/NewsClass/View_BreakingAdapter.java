package com.e.login.NewsClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.R;

import java.util.List;

class View_BreakingAdapter extends RecyclerView.Adapter<View_BreakingAdapter.ViewHolder> {



    List<View_BreakingModel> viewBreakingModelList;
    private Context context;


    public static OnItemClickListener mListener;




    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(FragmentActivity listener){

        mListener = (View_BreakingAdapter.OnItemClickListener) listener;

    }

    public View_BreakingAdapter(Context context, List<View_BreakingModel> viewBreakingModelList) {
        this.context = context;
        this.viewBreakingModelList= viewBreakingModelList;
    }

    @NonNull
    @Override
    public View_BreakingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_sixteen,parent,false);


        return new View_BreakingAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(viewBreakingModelList.get(position).getText());
        holder.textView1.setText(viewBreakingModelList.get(position).getText_one());
        //holder.img.setImageResource(R.drawable.dental);
        Glide.with(context)
                .load(viewBreakingModelList.get(position).getImage())
                .into(holder.img);
        holder.img1.setImageResource(R.drawable.eye);
        holder.btn.setText(viewBreakingModelList.get(position).getButton());
    }



    // total number of rows
    @Override
    public int getItemCount() {

        return viewBreakingModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1;
        TextView textView,textView1;
        Button btn;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.dental_img);
            img1 = itemView.findViewById(R.id.eye_img);

           textView = itemView.findViewById(R.id.dental_para);
           textView1 = itemView.findViewById(R.id.numberr);
           btn = itemView.findViewById(R.id.view_btn);

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