package com.e.login.BlankFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.e.login.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class Sliderblank_Top_Adapter extends SliderViewAdapter<Sliderblank_Top_Adapter.Holder>{

    int[] images;

    public Sliderblank_Top_Adapter(int[] images){

        this.images = images;

    }

    @Override
    public Sliderblank_Top_Adapter.Holder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slider_item,parent,false);
        return new Sliderblank_Top_Adapter.Holder(view);
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


