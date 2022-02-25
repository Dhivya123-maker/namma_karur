package com.e.login.HomeClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.e.login.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

//public class Slider_Bottom_Adapter extends SliderViewAdapter<Slider_Bottom_Adapter.SliderAdapterViewHolder> {
//
//    // list for storing urls of images.
//    private  List<Slider_Bottom_Model> sliderBottomModels;
//
//    // Constructor
//    public Slider_Bottom_Adapter(Context context, ArrayList<Slider_Bottom_Model> sliderBottomModels) {
//        this.sliderBottomModels = sliderBottomModels;
//    }
//
//    // We are inflating the slider_layout
//    // inside on Create View Holder method.
//    @Override
//    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
//        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom, null);
//        return new SliderAdapterViewHolder(inflate);
//    }
//
//    // Inside on bind view holder we will
//    // set data to item of Slider View.
//    @Override
//    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {
//
//        final Slider_Bottom_Model sliderItem = sliderBottomModels.get(position);
//
//        // Glide is use to load image
//        // from url in your imageview.
//        Glide.with(viewHolder.itemView)
//                .load(sliderItem.getImage())
//                .fitCenter()
//                .into(viewHolder.imageViewBackground);
//    }
//
//    // this method will return
//    // the count of our list.
//    @Override
//    public int getCount() {
//        return sliderBottomModels.size();
//    }
//
//    static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
//        // Adapter class for initializing
//        // the views of our slider view.
//        View itemView;
//        ImageView imageViewBackground;
//
//        public SliderAdapterViewHolder(View itemView) {
//            super(itemView);
//            imageViewBackground = itemView.findViewById(R.id.myimage);
//            this.itemView = itemView;
//        }
//    }
//
//}
public class Slider_Bottom_Adapter extends SliderViewAdapter<Slider_Bottom_Adapter.Holder>{

    int[] images;

    public Slider_Bottom_Adapter(int[] images){

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



