package com.e.login.Blog_Class;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Quotes_Catalog_Adapter extends RecyclerView.Adapter<Quotes_Catalog_Adapter.ViewHolder> {



    List<Quotes_Catalog_Model> quotesCatalogModelList;
    private Context context;

    Drawable drawable;
    Bitmap bitmap;
    String ImagePath;
    Uri URI;
    String glide;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    public Quotes_Catalog_Adapter(Context context, List<Quotes_Catalog_Model> quotesCatalogModelList) {
        this.context = context;
        this.quotesCatalogModelList = quotesCatalogModelList;
    }

    @NonNull
    @Override
    public Quotes_Catalog_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quotes_catalog_recycle,parent,false);


        return new Quotes_Catalog_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).asBitmap()
                .load(quotesCatalogModelList.get(position).getImg())
                .into(holder.img);


    }





    // total number of rows
    @Override
    public int getItemCount() {

        return quotesCatalogModelList.size();

    }




    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.quotes_catalog_img);
            img1 = itemView.findViewById(R.id.download_img);

            img1.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("UseCompatLoadingForDrawables")
                @Override
                public void onClick(View view) {



//                    BitmapDrawable draw = (BitmapDrawable) img.getDrawable();
//                    Bitmap bitmap = draw.getBitmap();
//
//                    FileOutputStream outStream = null;
//                    File sdCard = Environment.getExternalStorageDirectory();
//                    File dir = new File(sdCard.getAbsolutePath() + "/SaveImages");
//                    dir.mkdirs();
//                    String fileName = String.format("%d.jpg", System.currentTimeMillis());
//                    File outFile = new File(dir, fileName);
//                    try {
//                        outStream = new FileOutputStream(outFile);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
//                    try {
//                        outStream.flush();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        outStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//
              }

            });



}


    }


}
