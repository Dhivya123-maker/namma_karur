package com.e.login.Blog_Class;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.login.R;

import java.util.ArrayList;
import java.util.List;


public class Videos extends Fragment {
    RecyclerView recyclerView;
    Adapter adapter;
    List<Youtube_Model> youtube_models;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_videos, container, false);

        recyclerView =root.findViewById(R.id.recycler);


        videos();

        return  root;
    }

    public void videos(){

        youtube_models = new ArrayList<>();

        Youtube_Model model = new Youtube_Model();
        model.setVideoUrl("https://www.youtube.com/watch?v=XLbwd_zPbpw");

        youtube_models.add(model);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter =  new Adapter(getContext(),youtube_models);

        recyclerView.setAdapter(adapter);
    }
}