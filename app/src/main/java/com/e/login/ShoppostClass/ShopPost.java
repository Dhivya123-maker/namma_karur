package com.e.login.ShoppostClass;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.login.ProductsFragmentClass.ProductsAdapter;
import com.e.login.ProductsFragmentClass.ProductsModel;
import com.e.login.R;
import com.e.login.ShopscreenClass.ShopScreenAdapter;
import com.e.login.ShopscreenClass.ShopsScreenFragment;

import java.util.ArrayList;
import java.util.List;

public class ShopPost extends Fragment {
    List<ShopPostModel> shopPostModelList;
    ShopPostAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_shop_post, container, false);

        shopPostModelList = new ArrayList<>();

        RecyclerView recyclerView = root.findViewById(R.id.sam_shop);


        for (int i = 0; i < 2; i++) {

           ShopPostModel viewmodel = new ShopPostModel();

//
            viewmodel.setText("SR Air Conditioner");
//            viewmodel.setText_one("Senguthapuram, karur.");
            viewmodel.setText_two("5 days ago");
            viewmodel.setText_three("Lorem ispum is simply dummy text of printing");
            viewmodel.setImage("1");
            viewmodel.setImage1("2");
            viewmodel.setImage2("3");

            viewmodel.setImage3("4");
            viewmodel.setImage4("5");




            shopPostModelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter =  new ShopPostAdapter(getActivity(),shopPostModelList);
        recyclerView.setAdapter(adapter);



        return root;
    }
}