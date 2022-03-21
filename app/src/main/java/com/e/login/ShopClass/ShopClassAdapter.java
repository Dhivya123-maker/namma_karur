package com.e.login.ShopClass;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.R;

import java.util.List;

public class ShopClassAdapter extends RecyclerView.Adapter<ShopClassAdapter.ViewHolder> {

    String num;
    String num1;

    List<ShopModel> shopModelList;
     private Context context;
    private static final int REQUEST_CALL = 1 ;
    Dialog dialog;

    public static OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;

    }


    public ShopClassAdapter(Context context, List<ShopModel> shopModelList) {
        this.context = context;
        this.shopModelList =shopModelList;



    }




    @NonNull
    @Override
    public ShopClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_three,parent,false);


        return new ViewHolder(view);




    }

    public void onClick(DialogInterface dialogInterface, int which) {
        Dialog dialog  = (Dialog) dialogInterface;
        Activity activity = dialog.getOwnerActivity();

    }


    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        if (shopModelList.get(position).getCategory().equals("AmbulanceCatalog")){

            holder.ades.setText(shopModelList.get(position).getAdes());
            holder.aname.setText(shopModelList.get(position).getAname());
            holder.apri.setText(shopModelList.get(position).getApri());
            holder.asec.setText(shopModelList.get(position).getAsec());




            Glide.with(context)
                    .load(shopModelList.get(position).getAimg())
                    .into(holder.aimg);
            holder.linearLayout.setVisibility(View.VISIBLE);
            holder.relativeLayout.setVisibility(View.GONE);
            holder.market_linear.setVisibility(View.GONE);
            holder.bustime_linear.setVisibility(View.GONE);
            holder.n_liear.setVisibility(View.GONE);


        }else if (shopModelList.get(position).getCategory().equals("MarketCatalog")) {

            holder.mname.setText(shopModelList.get(position).getMname());
            holder.mview_count.setText(shopModelList.get(position).getMview_count());


            Glide.with(context)
                    .load(shopModelList.get(position).getMimg())
                    .into(holder.mimg);

            holder.linearLayout.setVisibility(View.GONE);
            holder.relativeLayout.setVisibility(View.GONE);
            holder.market_linear.setVisibility(View.VISIBLE);
            holder.bustime_linear.setVisibility(View.GONE);
            holder.n_liear.setVisibility(View.GONE);

        }else if (shopModelList.get(position).getCategory().equals("BusTimeCatalog")){

            holder.linearLayout.setVisibility(View.GONE);
            holder.relativeLayout.setVisibility(View.GONE);
            holder.market_linear.setVisibility(View.GONE);
            holder.bustime_linear.setVisibility(View.VISIBLE);
            holder.n_liear.setVisibility(View.GONE);


            holder.bname.setText(shopModelList.get(position).getBname());
            holder.bdes.setText(shopModelList.get(position).getBdes());

            Glide.with(context)
                    .load(shopModelList.get(position).getBimg())
                    .into(holder.bimg);


        }
        else if (shopModelList.get(position).getCategory().equals("GovtNgoCatalog")){

            holder.linearLayout.setVisibility(View.GONE);
            holder.relativeLayout.setVisibility(View.GONE);
            holder.market_linear.setVisibility(View.GONE);
            holder.bustime_linear.setVisibility(View.GONE);
            holder.n_liear.setVisibility(View.VISIBLE);


            holder.nname.setText(shopModelList.get(position).getNname());

            Glide.with(context)
                    .load(shopModelList.get(position).getNimage())
                    .into(holder.nimg);



        }else if (shopModelList.get(position).getCategory().equals("ATMCatalog")) {

            holder.linearLayout.setVisibility(View.GONE);
            holder.relativeLayout.setVisibility(View.VISIBLE);
            holder.market_linear.setVisibility(View.GONE);
            holder.bustime_linear.setVisibility(View.GONE);
            holder.n_liear.setVisibility(View.GONE);

            holder.textView1.setVisibility(View.GONE);
            holder.eye.setVisibility(View.GONE);



            holder.textView.setText(shopModelList.get(position).getText());
            holder.textView1.setText(shopModelList.get(position).getText_one());

            Glide.with(context)
                    .load(shopModelList.get(position).getImage())
                    .into(holder.imageView);

        }

        else {

                holder.linearLayout.setVisibility(View.GONE);
                holder.relativeLayout.setVisibility(View.VISIBLE);
                holder.market_linear.setVisibility(View.GONE);
                holder.bustime_linear.setVisibility(View.GONE);
                holder.n_liear.setVisibility(View.GONE);



                holder.textView.setText(shopModelList.get(position).getText());
                holder.textView1.setText(shopModelList.get(position).getText_one());

                Glide.with(context)
                        .load(shopModelList.get(position).getImage())
                        .into(holder.imageView);

            }
        }




    // total number of rows
    @Override
    public int getItemCount() {

        return shopModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,aimg,mimg,bimg,nimg,eye;
        TextView textView, textView1,aname,ades,mname,mview_count,bname,bdes,nname;
        Button apri,asec;
        LinearLayout linearLayout,market_linear,bustime_linear,n_liear;
        RelativeLayout relativeLayout;

        ViewHolder(View itemView) {
            super(itemView);

             dialog = new Dialog(itemView.getContext());


            imageView = itemView.findViewById(R.id.shop_image);
            textView = itemView.findViewById(R.id.shop_txt_one);
            textView1 = itemView.findViewById(R.id.shop_txt_two);
            aname= itemView.findViewById(R.id.ambulance_txt);
            ades= itemView.findViewById(R.id.ambulance_txt_one);
            aimg= itemView.findViewById(R.id.ambulance_img);
            apri = itemView.findViewById(R.id.tn_btn);
            asec = itemView.findViewById(R.id.call_btn);
            linearLayout = itemView.findViewById(R.id.as_amb);
            relativeLayout = itemView.findViewById(R.id.bg);

            eye = itemView.findViewById(R.id.eye_icon);



            mimg = itemView.findViewById(R.id.veg_img);
            mname = itemView.findViewById(R.id.veg_txt);
            mview_count = itemView.findViewById(R.id.veg_txt_one);
            market_linear = itemView.findViewById(R.id.l_market);


            bimg = itemView.findViewById(R.id.bus_img);

//            textView = itemView.findViewById(R.id.bus_txt_one);
            bname = itemView.findViewById(R.id.bus_txt_two);
            bdes = itemView.findViewById(R.id.bus_txt_three);
            bustime_linear = itemView.findViewById(R.id.s1);


            nimg = itemView.findViewById(R.id.government_img);
            nname = itemView.findViewById(R.id.government_txt);
            n_liear =  itemView.findViewById(R.id.govtt);



            asec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                     num = shopModelList.get(position).getNum();
                     num1 =shopModelList.get(position).getNum_one();

                     dialog();




                }
            });



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }



    }
    private void makePhoneCall() {
        String number = num;

        if (number.trim().length() > 0) {



            if (ContextCompat.checkSelfPermission(context.getApplicationContext(),
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity)this.getApplicationContext(),
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }
            else {
                String dial = "tel:" + number;
                context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }
    }

    private Context getApplicationContext() {
        return context;
    }


    private void makePhoneCall_one() {
        String number = num1;

        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(context.getApplicationContext(),
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity)this.getApplicationContext(),
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }
            else {
                String dial = "tel:" + number;
                context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(context.getApplicationContext(), "Permission Denied to make a call" + "", Toast.LENGTH_SHORT).show();
            }
        }
    }




    public  void dialog(){
        dialog.setContentView(R.layout.ambulance_call_recycle);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView txt = dialog.findViewById(R.id.primary_con_txt);
        TextView txt1 = dialog.findViewById(R.id.sec_con_txt);
        ImageView img = dialog.findViewById(R.id.primary_con_img);
        ImageView img1 = dialog.findViewById(R.id.sec_img);
        Button btn = dialog.findViewById(R.id.cancel_buttonn);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                makePhoneCall();
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall_one();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.cancel();
            }
        });

        dialog.show();
    }

}