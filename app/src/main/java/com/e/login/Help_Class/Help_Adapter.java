package com.e.login.Help_Class;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.AmbulanceClass.AmbulanceModel;

import com.e.login.MarketListClass.MarketActivity;
import com.e.login.R;

import java.util.List;

public class Help_Adapter extends RecyclerView.Adapter<Help_Adapter.ViewHolder> {

    String num;

    List<Helpline_Model> helplineModelList;
    private Context context;
    private static final int REQUEST_CALL = 1 ;

    public Help_Adapter(Context context, List<Helpline_Model> helplineModelList) {
        this.context = context;
        this.helplineModelList = helplineModelList;
    }

    @NonNull
    @Override
    public Help_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.help_recycle,parent,false);


        return new Help_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(helplineModelList.get(position).getTxt());
        holder.img.setBackgroundResource(R.drawable.green_call);
    }





    // total number of rows
    @Override
    public int getItemCount() {

        return helplineModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.call);

            textView = itemView.findViewById(R.id.txt);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                     num = helplineModelList.get(position).getNum();

                     makePhoneCall();



                }
            });




        }



    }

    private void makePhoneCall() {
        String number = num;

        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(context.getApplicationContext(),
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context.getApplicationContext(),
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }
            else {
                String dial = "tel:" + number;
                context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }
    }


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(context.getApplicationContext(), "Permission Denied to make a call" + "", Toast.LENGTH_SHORT).show();
            }
        }
    }




}
