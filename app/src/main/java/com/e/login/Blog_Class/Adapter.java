package com.e.login.Blog_Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.R;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<Youtube_Model> youtubeVideoList;
     Context context;



    public Adapter(Context context, List<Youtube_Model> youtubeVideoList) {
        this.youtubeVideoList = youtubeVideoList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext()).inflate(R.layout.videos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.videoWeb.loadUrl(youtubeVideoList.get(position).getVideoUrl());
        holder.videoWeb.getSettings().setJavaScriptEnabled(true);

        holder.videoWeb.setWebChromeClient(new WebChromeClient() {
        } );


    }



    @Override
    public int getItemCount() {

        return youtubeVideoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        WebView videoWeb;


       ViewHolder(View itemView) {
            super(itemView);


            videoWeb = itemView.findViewById(R.id.web_view);


        }
    }
}