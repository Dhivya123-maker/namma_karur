package com.e.login.ShopscreenClass;




import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.Home_Fragment_Class;
import com.e.login.R;
import com.e.login.ShopClass.ShopClassAdapter;
import com.e.login.ShopClass.ShopModel;
import com.e.login.ShopscreenClass.ShopsScreenFragment;

import java.util.List;

public class ShopScreenAdapter extends RecyclerView.Adapter<com.e.login.ShopscreenClass.ShopScreenAdapter.ViewHolder> {



    List<ShopScreenModel> shopScreenModelList;
    private Context context;


    public static ShopScreenAdapter.OnItemClickListener mListener1;




    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(ShopsScreenFragment listener){

        mListener1 = (OnItemClickListener) listener;

    }

    public ShopScreenAdapter(Context context, List<ShopScreenModel> shopScreenModelList) {
        this.context = context;
        this.shopScreenModelList=shopScreenModelList;
    }

    @NonNull
    @Override
    public ShopScreenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_four,parent,false);


        return new com.e.login.ShopscreenClass.ShopScreenAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        if (shopScreenModelList.get(position).getCategory().equals("ATMCatalog")){

            holder.atm_txt.setText(shopScreenModelList.get(position).getText());
            holder.  atm_desc.setText(shopScreenModelList.get(position).getText_one());
            holder.loc.setText(shopScreenModelList.get(position).getText_three());

            Glide.with(context)
                    .load(shopScreenModelList.get(position).getImage())
                    .into(holder.img1);

            holder.lnr.setVisibility(View.GONE);
            holder.atm_lnr.setVisibility(View.VISIBLE);

//            holder.filter.setVisibility(View.GONE);


        }else {

            holder.textView.setText(shopScreenModelList.get(position).getText());
            holder.textView1.setText(shopScreenModelList.get(position).getText_one());
            holder.textView2.setText(shopScreenModelList.get(position).getText_two());
            holder.textView3.setText(shopScreenModelList.get(position).getText_three());
            holder.textView4.setText(shopScreenModelList.get(position).getText_four());

            holder.lnr.setVisibility(View.VISIBLE);
            holder.atm_lnr.setVisibility(View.GONE);
//            holder.filter.setVisibility(View.VISIBLE);

            Glide.with(context)
                    .load(shopScreenModelList.get(position).getImage())
                    .into(holder.img);


        }



    }

    // total number of rows
    @Override
    public int getItemCount() {

        return shopScreenModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1,img2,img3,atm_img;
        LinearLayout lnr,atm_lnr,filter;
        TextView textView, textView1,textView2,textView3,textView4,atm_txt,atm_desc,atm_rat,loc;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.sr_img);
//            img1 = itemView.findViewById(R.id.atm_star);
            img2 = itemView.findViewById(R.id.verify_img);
            img3 = itemView.findViewById(R.id.time_img);
            atm_img = itemView.findViewById(R.id.img_loc);

            lnr = itemView.findViewById(R.id.ac_onee);
            atm_lnr = itemView.findViewById(R.id.atm_lnr);
            filter = itemView.findViewById(R.id.filter_lnr);

            textView = itemView.findViewById(R.id.sr_txt_one);
            textView1 = itemView.findViewById(R.id.sr_txt_two);
            textView2 = itemView.findViewById(R.id.sr_txt_three);
            textView3 = itemView.findViewById(R.id.verified_txt);
            textView4 = itemView.findViewById(R.id.time_txt);
            loc = itemView.findViewById(R.id.locationn);


            atm_txt = itemView.findViewById(R.id.atm_name);
            atm_desc = itemView.findViewById(R.id.atm_address);
            img1 = itemView.findViewById(R.id.atm_img);
//            atm_rat = itemView.findViewById(R.id.atm_rate);



////
            atm_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    String loc = shopScreenModelList.get(position).getLoc();

                    Uri gmmIntentUri = Uri.parse("geo:"+loc);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
//                    mapIntent.setPackage("www.google.com");
                  view.getContext().startActivity(mapIntent);

//




                }


//
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (shopScreenModelList.get(position).getCategory().equals("ATMCatalog")){

                    }else {
                        if (mListener1 != null) {

                            if (position != RecyclerView.NO_POSITION) {
                                mListener1.onItemClick(position);
                            }

                        }
                    }

                }
            });

        }




    }


}