package com.e.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;


public class SliderAdapter extends PagerAdapter {


    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public SliderAdapter() {
    }

    int images[] = {

            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,

    };

//    int headings[] = {
//            R.string.first_slide_title,
//            R.string.second_slide_title,
//            R.string.third_slide_title,
//            R.string.fourth_slide_title,
//
//
//    };
//
//    int description[] = {
//
//            R.string.first_slide_desc,
//            R.string.second_slide_desc,
//            R.string.third_slide_desc,
//            R.string.fourth_slide_desc,
//    };
//

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

//    to initilize those methods instantititms

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        We ReQUeSTeD THE SYSTM TO US SOM LAYOUT

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.activity_slides_layout, container, false);

//        get those images text heaadings desc imags  hooks
        ImageView imageView = view.findViewById(R.id.slider_image);
//        TextView heading = view.findViewById(R.id.slider_heading);
//        TextView desc = view.findViewById(R.id.slider_desc);

//        set those hooks
        imageView.setImageResource(images[position]);
//        heading.setText(headings[position]);
//        desc.setText(description[position]);


//        place everything in th container
        container.addView(view);


        return view;


    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }


}

