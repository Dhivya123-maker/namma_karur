package com.e.login.Bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.e.login.AmbulanceClass.Ambulance;
import com.e.login.AmbulanceClass.AmbulanceAdapter;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.R;

import java.util.ArrayList;
import java.util.List;

public class BankActivity extends AppCompatActivity {
    List<Bank_Model> bank_modelList;
    Bank_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

       bank_modelList = new ArrayList<>();

        RecyclerView recyclerView =findViewById(R.id.bank_screen);


        for (int i = 0; i < 5; i++) {

            Bank_Model viewmodel = new Bank_Model();



            viewmodel.setImage("1");

            viewmodel.setText("Axis Bank");

            bank_modelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(BankActivity.this));

        adapter =  new Bank_Adapter(BankActivity.this,bank_modelList);
        recyclerView.setAdapter(adapter);

    }
}