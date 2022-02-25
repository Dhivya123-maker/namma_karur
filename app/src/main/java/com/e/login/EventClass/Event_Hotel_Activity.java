package com.e.login.EventClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.e.login.R;
import com.e.login.fragment_dialog.BottomSheetFragment_filter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Event_Hotel_Activity extends AppCompatActivity {
    List<Event_Hotel_Model> event_hotel_modelList;
    Event_Hotel_Adapter adapter;
    Button btPickDate;
    public static final String TAG = "bottom_sheet";
    LinearLayout filter;
    Spinner spinnerLanguages,spinner1,spinner2;
    private DatePickerDialog datePickerDialog;
    private Button dateButton,dateButton1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_hotel);

        initDatePicker();
        dateButton = findViewById(R.id.fromdatepicker);
        dateButton.setText("From Date");
        dateButton = findViewById(R.id.todatepicker);
        dateButton.setText("To Date");
        getTodaysDate();

//        spinnerLanguages=findViewById(R.id.spinner_languages);
//        spinner1=findViewById(R.id.spinner_languages1);
//        spinner2=findViewById(R.id.spinner_languages2);
//
//        ArrayAdapter<CharSequence> event_adapter=ArrayAdapter.createFromResource(this, R.array.Day, android.R.layout.simple_spinner_dropdown_item);
//       // event_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//       spinnerLanguages.setAdapter(event_adapter);
//       spinnerLanguages.setPadding(10,10,10,10);
//
//
//        ArrayAdapter<CharSequence> event_adapter1=ArrayAdapter.createFromResource(this, R.array.Month, android.R.layout.simple_spinner_dropdown_item);
//       spinner1.setAdapter(event_adapter1);
//        spinner1.setPadding(10,10,10,10);
//
//        ArrayAdapter<CharSequence> event_adapter2=ArrayAdapter.createFromResource(this, R.array.Month, android.R.layout.simple_spinner_dropdown_item);
//       spinner2.setAdapter(event_adapter2);
//        spinner2.setPadding(10,10,10,10);
//




        event_hotel_modelList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.event_hotel_screen);


        for (int i = 0; i < 4; i++) {

            Event_Hotel_Model viewmodel = new Event_Hotel_Model();


            viewmodel.setText("KVR Hotels");
            viewmodel.setImage("1");
            viewmodel.setImage1("2");
            viewmodel.setImage2("3");
            viewmodel.setImage3("4");
            viewmodel.setText1("123, Jawahar Bazzar,Near Bus\nStand Karur, TamilNadu 639001");
            viewmodel.setText2("8.00am - 10.00am");
            viewmodel.setText3("Verified");
            viewmodel.setText4("4.6");


            event_hotel_modelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(Event_Hotel_Activity.this));

        adapter = new Event_Hotel_Adapter(Event_Hotel_Activity.this, event_hotel_modelList);
        recyclerView.setAdapter(adapter);

//        btPickDate = findViewById(R.id.date_btn);
//        btPickDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Please note that use your package name here
//
//                com.e.login.EventClass.DatePicker mDatePickerDialogFragment;
//                mDatePickerDialogFragment = new com.e.login.EventClass.DatePicker();
//                mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
//            }
//        });
//    }
//
//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        Calendar mCalendar = Calendar.getInstance();
//        mCalendar.set(Calendar.YEAR, year);
//        mCalendar.set(Calendar.MONTH, month);
//        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//        String selectedDate = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(mCalendar.getTime());
//        btPickDate.setText(selectedDate);
//
//
//
//    }
    }
    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
                dateButton1.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }


}