package com.e.login.Services_Class;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.ProductsFragmentClass.ProductsModel;
import com.e.login.R;

import java.util.List;

public class FragmentAdapter extends RecyclerView.Adapter<FragmentAdapter.ViewHolder> {



    List<Fragment_Model> fragmentModelList;
    private Context context;





    public static FragmentAdapter.OnItemClickListener mListener;




    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(FragmentAdapter.OnItemClickListener listener){

        mListener = listener;

    }

    public FragmentAdapter(Context context, List<Fragment_Model> fragmentModelList) {
        this.context = context;
        this.fragmentModelList = fragmentModelList;
    }

    @NonNull
    @Override
    public FragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_sixty_six,parent,false);


        return new FragmentAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(fragmentModelList.get(position).getText());
        holder.textView1.setText(fragmentModelList.get(position).getText_one());
        holder.txt2.setText(fragmentModelList.get(position).getText_two());
        holder.img1.setImageResource(R.drawable.five_starr);

        //holder.img.setImageResource(R.drawable.ac);

        Glide.with(context)
                .load(fragmentModelList.get(position).getImage())
                .into(holder.img);

    }



    // total number of rows
    @Override
    public int getItemCount() {

        return fragmentModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1;
        TextView textView, textView1,txt2;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.comments_img5);
            img1 = itemView.findViewById(R.id.star_star5);

            textView = itemView.findViewById(R.id.userr_txt5);
            textView1 = itemView.findViewById(R.id.goodd_txt5);
            txt2 = itemView.findViewById(R.id.reviewss_ratee5);



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
//                    Intent intent = new Intent(view.getContext(),Carrier_Activity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                    view.getContext().startActivity(intent);


        }



    }


}