package com.e.login.Gallery_Class;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.login.ProductsFragmentClass.ProductsAdapter;
import com.e.login.ProductsFragmentClass.ProductsModel;
import com.e.login.R;

import java.util.ArrayList;
import java.util.List;


public class Gallery_Fragment extends Fragment {


    List<Gallery_Model> galleryModelList;
    Gallery_Adapter adapter;


    List<Gallery_One_Model> galleryOneModelList;
    Gallery_One_Adapter adapter1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_gallery_, container, false);

        galleryModelList = new ArrayList<>();

        RecyclerView recyclerView = root.findViewById(R.id.gallery_recycle);



        for (int i = 0; i < 4; i++) {

            Gallery_Model viewmodel = new Gallery_Model();


            viewmodel.setImg("1");

             galleryModelList.add(viewmodel);

        }

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter =  new Gallery_Adapter(getActivity(),galleryModelList);
        recyclerView.setAdapter(adapter);




        galleryOneModelList = new ArrayList<>();

        RecyclerView recyclerView1 = root.findViewById(R.id.gallery_two_screen);



        for (int i = 0; i < 4; i++) {

            Gallery_One_Model viewmodel = new Gallery_One_Model();


            viewmodel.setImg("1");

            galleryOneModelList.add(viewmodel);

        }

        RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(getActivity(), 2);
        recyclerView1.setLayoutManager(mLayoutManager1);
        adapter1 =  new Gallery_One_Adapter(getActivity(),galleryOneModelList);
        recyclerView1.setAdapter(adapter1);



        return  root;

    }
}