package com.e.login.Mall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.e.login.EventClass.Event_Comments_Adapter;
import com.e.login.FunClass.Fun;
import com.e.login.FunClass.FunAdapter;
import com.e.login.FunClass.FunModel;
import com.e.login.R;

import java.util.ArrayList;
import java.util.List;

public class MallClass extends AppCompatActivity {
    List<Mallmodel> mallmodelList;
    MallAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_class);

        mallmodelList= new ArrayList<>();

        RecyclerView recyclerView =findViewById(R.id.mall_screen);


        for (int i = 0; i < 4; i++) {

           Mallmodel viewmodel = new Mallmodel();



            viewmodel.setImage("1");
            viewmodel.setTxt("Brookefields");


            mallmodelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(MallClass.this));

        adapter =  new MallAdapter(MallClass.this,mallmodelList);
        recyclerView.setAdapter(adapter);




    }
}