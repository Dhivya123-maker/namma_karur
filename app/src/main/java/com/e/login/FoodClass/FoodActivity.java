package com.e.login.FoodClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.e.login.BusTimeClass.BusTimeAdapter;
import com.e.login.BusTimeClass.BusTimeModel;
import com.e.login.BusTimeClass.Bus_TimeActivity;
import com.e.login.R;
import com.e.login.fragment_dialog.BottomSheetFragment_filter;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    List<FoodModel> foodModelList;
    FoodAdapter adapter;
    List<FoodOneModel> foodOneModelList;
    FoodOneAdapter adapter1;
    LinearLayout filter;
    public static final String TAG = "bottom_sheet";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        filter = findViewById(R.id.food_filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.e.login.fragment_dialog.BottomSheetFragment_filter fragment = new BottomSheetFragment_filter();
                fragment.show(getSupportFragmentManager(), TAG);
            }
        });
      foodModelList = new ArrayList<>();
      foodOneModelList = new ArrayList<>();

        RecyclerView recyclerView =findViewById(R.id.food_screen);
        RecyclerView recyclerView1 = findViewById(R.id.food_screen_one);


        for (int i = 0; i < 3; i++) {

          FoodModel viewmodel = new FoodModel();



            viewmodel.setImage("1");
            viewmodel.setText("Lorem ispum may be used as a placeholder before the final copy is available.");
            viewmodel.setButton("View");

            foodModelList.add(viewmodel);

        }

        for (int i = 0; i < 2; i++) {

            FoodOneModel viewmodel = new FoodOneModel();



            viewmodel.setImage("1");
            viewmodel.setText("Lorem ispum may be used as a placeholder before the final copy is available.");
            viewmodel.setText_one("Read more...");

            foodOneModelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(FoodActivity.this));

        adapter =  new FoodAdapter(FoodActivity.this,foodModelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(FoodActivity.this, LinearLayoutManager.HORIZONTAL, false));


        recyclerView.setAdapter(adapter);


        recyclerView1.setLayoutManager(new LinearLayoutManager(FoodActivity.this));

        adapter1 =  new FoodOneAdapter(FoodActivity.this,foodOneModelList);

        recyclerView1.setAdapter(adapter1);




//        textView = findViewById(R.id.text_view_show_more);
//
//
//        textView.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");

//        textView.setShowingLine(2);
//        //textView.setShowingChar(30);
//
//        textView.addShowMoreText("Continue");
//        textView.addShowLessText("Less");


    }
}