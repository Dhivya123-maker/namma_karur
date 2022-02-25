package com.e.login.ProductsFragmentClass;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.e.login.BlankFragment.Blank_PostFragment;
import com.e.login.CarrierClass.CarrierViewPagerAdapter;
import com.e.login.CarrierClass.Carrier_Activity;
import com.e.login.CarrierClass.Carrier_Fragment;
import com.e.login.MoreInfoClass.MoreInfo;
import com.e.login.New;
import com.e.login.R;
import com.e.login.ShopClass.ShopClassAdapter;
import com.e.login.ShoppostClass.ShopPost;
import com.e.login.ShopscreenClass.ShopScreenAdapter;
import com.e.login.ShopscreenClass.ShopsScreenFragment;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<com.e.login.ProductsFragmentClass.ProductsAdapter.ViewHolder> {



    List<ProductsModel> productsModelList;
    private Context context;





//    public static ProductsAdapter.OnItemClickListener mListener;
//
//
//
//
//    public interface OnItemClickListener{
//        void onItemClick(int position);
//    }
//
//    public void setOnItemClickListener(ProductsAdapter.OnItemClickListener listener){
//
//        mListener = listener;
//
//    }

    public ProductsAdapter(Context context, List<ProductsModel> productsModelList) {
        this.context = context;
        this.productsModelList = productsModelList;
    }

    @NonNull
    @Override
    public com.e.login.ProductsFragmentClass.ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_five,parent,false);


        return new com.e.login.ProductsFragmentClass.ProductsAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(productsModelList.get(position).getText());
        holder.textView1.setText(productsModelList.get(position).getText_one());
       holder.btn.setText(productsModelList.get(position).getButton());
        //holder.img.setImageResource(R.drawable.ac);

        Glide.with(context)
                .load(productsModelList.get(position).getImage())
                .into(holder.img);

    }



    // total number of rows
    @Override
    public int getItemCount() {

        return productsModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        Button btn;
        TextView textView, textView1;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.air_conditioner);
           btn = itemView.findViewById(R.id.more_button);

            textView = itemView.findViewById(R.id.carrier_txt_one);
            textView1 = itemView.findViewById(R.id.carrier_txt_two);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                            Intent intent = new Intent(view.getContext(), Carrier_Activity.class);
                            view.getContext().startActivity(intent);


//                    if (mListener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            mListener.onItemClick(position);
//                        }
//                    }
                }
            });

//

        }



    }


}