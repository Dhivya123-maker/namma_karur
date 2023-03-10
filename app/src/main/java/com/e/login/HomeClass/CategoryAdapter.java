package com.e.login.HomeClass;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.Blog_Class.BlogActivity;
import com.e.login.BloodClass.Blood_Fragment;
import com.e.login.JobsClass.Jobs;
import com.e.login.MoreClass.MoreActivity;
import com.e.login.NewsClass.NewsActivity;
import com.e.login.Offers.OfferActivity;
import com.e.login.R;
import com.e.login.ShopClass.ShopClassAdapter;
import com.e.login.ShopClass.ShopModel;
import com.e.login.ShopClass.ShopScreen_Class;
import com.e.login.Shopping_Activity;
import com.e.login.ShoppostClass.ShopPostModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {



    List<CategoryModel> cat;
    private Context context;

//
//    public static CategoryAdapter.OnItemClickListener mListener;
//
//
//
//    public interface OnItemClickListener{
//        void onItemClick(int position);
//    }
//
//    public void setOnItemClickListener(CategoryAdapter.OnItemClickListener listener){
//
//        mListener = listener;
//
//    }


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

                    int position = getAdapterPosition();
                    String category = cat.get(position).getCat_name();
                    String url = cat.get(position).getUrl();
                    if(category.equals("OfferCatalog")){

                        Intent intent = new Intent(view.getContext(), OfferActivity.class);
                        intent.putExtra("cat",category);
                        view.getContext().startActivity(intent);
                    }else if (category.equals("NewsCatalog")){
                        Intent intent = new Intent(view.getContext(), NewsActivity.class);
                        intent.putExtra("cat",category);

                        view.getContext().startActivity(intent);
                    }else if (category.equals("BloodCatalog")){
                        Intent intent = new Intent(view.getContext(), Blood_Fragment.class);
                        view.getContext().startActivity(intent);

                    }else if (category.equals("KarurBlogCatalog")){
                        Intent intent = new Intent(view.getContext(), BlogActivity.class);
                        intent.putExtra("cat",category);
                        view.getContext().startActivity(intent);
                    }
                    else if (category.equals("JobsCatalog")){
                        Intent intent = new Intent(view.getContext(), Jobs.class);
                        intent.putExtra("cat",category);
                        view.getContext().startActivity(intent);

                    }  else if(category.equals("ShoppingCatalog")){
                        Intent intent = new Intent(view.getContext(), Shopping_Activity.class);
                        intent.putExtra("url",url);
                        view.getContext().startActivity(intent);

                    }
                    else if(category.equals("ShopCatalog") ||category.equals("ServiceCatalog") ||category.equals("EducationCatalog")||category.equals("TransportCatalog")
                            ||category.equals("HotelCatalog")  ||category.equals("HospitalCatalog") ||category.equals("EventCatalog")
                            ||category.equals("MarketCatalog") ||category.equals("BankCatalog") || category.equals("ATMCatalog")
                            ||category.equals("BusTimeCatalog")  ||category.equals("GovtNgoCatalog") ||category.equals("AmbulanceCatalog") )    {

                        Intent intent = new Intent(view.getContext(), ShopScreen_Class.class);
                        intent.putExtra("cat", category);
                        view.getContext().startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(view.getContext(), MoreActivity.class);
                        intent.putExtra("cat", category);
                        view.getContext().startActivity(intent);
                    }


//                    if (mListener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            mListener.onItemClick(position);
//                        }
//                    }
                }
            });
        }



    }


}
