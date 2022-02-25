package com.e.login.FoodClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.e.login.R;

import java.util.ArrayList;
import java.util.List;

public class Readmore_Clas extends AppCompatActivity {
    List<Readmore_Model> readmoreModelList;
    Readmore_Adapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readmore_clas);


        readmoreModelList = new ArrayList<>();

        RecyclerView recyclerView =findViewById(R.id.read_more_screen);



          Readmore_Model viewmodel = new Readmore_Model();



            viewmodel.setImg("1");
            viewmodel.setImg1("2");
            viewmodel.setImg2("3");
            viewmodel.setImg3("4");
            viewmodel.setImg4("5");
            viewmodel.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vehicula non est et tempor. Cras viverra, tortor et commodo hendrerit, neque purus dignissim turpis, sed laoreet libero magna sit amet leo. Aliquam ultrices vulputate hendrerit. Pellentesque in quam pellentesque, posuere risus in, rhoncus nunc. Ut cursus enim mauris, et vehicula risus mollis id. Integer suscipit, ligula eget fringilla commodo, felis metus fermentum dui, id bibendum augue nisl ut sapien. In et molestie ipsum. Duis eu placerat enim.");


           readmoreModelList.add(viewmodel);


        recyclerView.setLayoutManager(new LinearLayoutManager(Readmore_Clas.this));

        adapter =  new Readmore_Adapter(Readmore_Clas.this,readmoreModelList);

        recyclerView.setAdapter(adapter);




    }
}