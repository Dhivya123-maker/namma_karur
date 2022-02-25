package com.e.login.HomeClass;

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
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;
//
//public class Slider_Top_Adapter extends RecyclerView.Adapter<Slider_Top_Adapter.ViewHolder> {
//
//
//    int[] images;
//    List<Slider_Top_Model> sliderTopModelList;
//    private Context context;
//
//
//    public Slider_Top_Adapter(Context context, List<Slider_Top_Model> sliderTopModelList) {
//        this.context = context;
//        this.sliderTopModelList = sliderTopModelList;
//    }
//
//    public Slider_Top_Adapter(int[] images) {
//        this.images = images;
//    }
//
//    @NonNull
//    @Override
//    public Slider_Top_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item,parent,false);
//
//
//        return new Slider_Top_Adapter.ViewHolder(view);
//
//
//    }
//
//
//
//    @Override
//    public void onBindViewHolder(@NonNull Slider_Top_Adapter.ViewHolder holder, int position) {
//
////        holder.img.setImageResource(R.drawable.first_one);
//        holder.img.setImageResource(images[position]);
//    }
//
//
//
//
//    // total number of rows
//    @Override
//    public int getItemCount() {
//
//        return images.length;
//
//    }
//
//
//    // stores and recycles views as they are scrolled off screen
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView img;
//
//        ViewHolder(View itemView) {
//            super(itemView);
//            img = itemView.findViewById(R.id.image_view);
//
//
//
//
//        }
//
//
//
//    }}

public class Slider_Top_Adapter extends SliderViewAdapter<Slider_Top_Adapter.Holder>{

    int[] images;

    public Slider_Top_Adapter(int[] images){

        this.images = images;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slider_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {

        viewHolder.imageView.setImageResource(images[position]);

    }

    @Override
    public int getCount() {
        return images.length;
    }

    public class Holder extends  SliderViewAdapter.ViewHolder{

        ImageView imageView;

        public Holder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);

        }
    }

}

