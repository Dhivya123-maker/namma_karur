package com.e.login.Mall;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.e.login.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class Slider_mall_Adapter extends SliderViewAdapter<Slider_mall_Adapter.Holder> {

    int[] images;

    public Slider_mall_Adapter(int[] images){

        this.images = images;

    }

    @Override
    public Slider_mall_Adapter.Holder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slider_item,parent,false);
        return new Slider_mall_Adapter.Holder(view);
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