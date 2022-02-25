package com.e.login.EventClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.e.login.Mall.MallAdapter;
import com.e.login.Mall.MallClass;
import com.e.login.Mall.Mallmodel;
import com.e.login.R;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity {

    List<EventModel> eventModelList;
    EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        eventModelList= new ArrayList<>();

        RecyclerView recyclerView =findViewById(R.id.event_screen);


        for (int i = 0; i < 3; i++) {

            EventModel viewmodel = new EventModel();



            viewmodel.setImage("1");
            viewmodel.setText("Hotels");

          eventModelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(EventActivity.this));

        adapter =  new EventAdapter(EventActivity.this,eventModelList);
        recyclerView.setAdapter(adapter);


    }
}