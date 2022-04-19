package com.e.login.chatClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.e.login.AmbulanceClass.AmbulanceAdapter;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.R;

import java.util.ArrayList;
import java.util.List;

public class Chat_Activity extends AppCompatActivity {
    List<Chat_Model> chatModelList;
    Chat_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);


        chatModelList = new ArrayList<>();

        RecyclerView recyclerView =findViewById(R.id.chat_recycle);


        for (int i = 0; i < 3; i++) {

            Chat_Model viewmodel = new Chat_Model();



           viewmodel.setImg("1");
           viewmodel.setTxt("Namma Karur");
            viewmodel.setTxt1("Lorem ispum may be used as a");
           chatModelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(Chat_Activity.this));

        adapter =  new Chat_Adapter(Chat_Activity.this,chatModelList);
        recyclerView.setAdapter(adapter);

    }
}