package com.e.login.Blog_Class;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.login.AmbulanceClass.Ambulance;
import com.e.login.AmbulanceClass.AmbulanceAdapter;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.FoodClass.FoodAdapter;
import com.e.login.FoodClass.FoodModel;
import com.e.login.R;
import com.e.login.ShopscreenClass.Slidershop_Top_Adapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class Blog_Fragment extends Fragment {

    SliderView sliderView;
    int[] images = {R.drawable.first_one,
            R.drawable.banner,
            R.drawable.bank_banner,
            R.drawable.banner,
            R.drawable.first_one,
    };
    List<BlogModel> blogModelList;
    BlogAdapter adapter;

    List<Blog_One_Model> blogOneModelList;
    Blog_One_Adapter adapter1;


    List<Blog_two_Model> blogTwoModelList;
    Blog_two_Adapter adapter2;

    List<Blog_three_Model> blog_three_modelList;
    Blog_three_Adapter adapter3;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_blog_, container, false);

        sliderView = root. findViewById(R.id.slider_blog);
        Slidershop_Top_Adapter sliderAdapter = new Slidershop_Top_Adapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();


        blogModelList = new ArrayList<>();

        RecyclerView recyclerView = root.findViewById(R.id.blog_recycler);


        for (int i = 0; i < 2; i++) {

            BlogModel viewmodel = new BlogModel();



            viewmodel.setImage("1");

            viewmodel.setText_one("Read more");
            viewmodel.setText("Lorem ispum may be used as a holder\nbefore the final copy is available");



            blogModelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter =  new BlogAdapter(getContext(),blogModelList);
        recyclerView.setAdapter(adapter);




        blogOneModelList = new ArrayList<>();

        RecyclerView recyclerView1 = root.findViewById(R.id.blog1_recycler);


        for (int i = 0; i < 4; i++) {

            Blog_One_Model viewmodel = new Blog_One_Model();



            viewmodel.setImg("1");

            viewmodel.setTxt1("Read more");
            viewmodel.setTxt("Lorem ispum may be used as a placeholder");



            blogOneModelList.add(viewmodel);

        }

        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter1 =  new Blog_One_Adapter(getContext(),blogOneModelList);
        recyclerView1.setAdapter(adapter1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));




        blogTwoModelList = new ArrayList<>();

        RecyclerView recyclerView2 = root.findViewById(R.id.blog2_recycler);


        for (int i = 0; i < 4; i++) {

            Blog_two_Model viewmodel = new Blog_two_Model();



            viewmodel.setImg("1");

            viewmodel.setTxt1("Read more");
            viewmodel.setTxt("Lorem ispum may be used as a placeholder");



            blogTwoModelList.add(viewmodel);

        }

        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter2 =  new Blog_two_Adapter(getContext(),blogTwoModelList);
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));




//
//        blog_three_modelList = new ArrayList<>();
//
//        RecyclerView recyclerView3 = root.findViewById(R.id.blog3_recycler);
//
//
//        for (int i = 0; i < 4; i++) {
//
//            Blog_three_Model viewmodel = new Blog_three_Model();
//
//
//
//            viewmodel.setImg("1");
//
//            viewmodel.setTxt1("Read more");
//            viewmodel.setTxt("Lorem ispum may be used as a placeholder");
//
//
//
//            blog_three_modelList.add(viewmodel);
//
//        }
//
//        recyclerView3.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        adapter3 =  new Blog_three_Adapter(getContext(),blog_three_modelList);
//        recyclerView3.setAdapter(adapter3);
//        recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//
//




        return  root;
    }
}