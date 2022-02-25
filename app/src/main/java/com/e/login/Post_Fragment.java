package com.e.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Post_Fragment extends Fragment {






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View root =  inflater.inflate(R.layout.fragment_post_, container, false);
//
//      ac_one = root.findViewById(R.id.ac_one);
//
//        ac_one.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), Home_Fragment_Class.class);
//                startActivity(intent);
//            }
//        });


                return root;
    }
}