package com.e.login.HomeClass;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.R;
import com.e.login.ShopClass.ShopClassAdapter;
import com.e.login.ShopClass.ShopModel;
import com.e.login.ShoppostClass.ShopPostModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {



    List<CategoryModel> cat;
    private Context context;


    public static CategoryAdapter.OnItemClickListener mListener;



    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(CategoryAdapter.OnItemClickListener listener){

        mListener = listener;

    }


    public CategoryAdapter(Context context, List<CategoryModel> cat) {
        this.context = context;
        this.cat= cat;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category,parent,false);


        return new CategoryAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(cat.get(position).getName());


        Glide.with(context)
                .load(cat.get(position).getImg())
                .into(holder.img);


    }



    // total number of rows
    @Override
    public int getItemCount() {

        return cat.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.shopsLogo);
            textView = itemView.findViewById(R.id.cat_name);




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


}