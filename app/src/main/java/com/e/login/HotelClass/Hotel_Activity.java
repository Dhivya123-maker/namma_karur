package com.e.login.HotelClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.e.login.FunClass.Fun;
import com.e.login.FunClass.FunAdapter;
import com.e.login.FunClass.FunModel;
import com.e.login.R;

import java.util.ArrayList;
import java.util.List;

public class Hotel_Activity extends AppCompatActivity {
    List<HotelModel> hotelModelList;
    Hotel_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);


        hotelModelList= new ArrayList<>();

        RecyclerView recyclerView =findViewById(R.id.hotel_screen);


        for (int i = 0; i < 3; i++) {

           HotelModel viewmodel = new HotelModel();



            viewmodel.setImage("1");
            viewmodel.setText("Cafe");

            hotelModelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(Hotel_Activity.this));

        adapter =  new Hotel_Adapter(Hotel_Activity.this,hotelModelList);
        recyclerView.setAdapter(adapter);


    }
}