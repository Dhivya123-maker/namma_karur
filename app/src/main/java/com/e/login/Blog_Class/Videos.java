package com.e.login.Blog_Class;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.e.login.R;

import java.util.ArrayList;
import java.util.List;


public class Videos extends Fragment {

    WebView webView;
    String url;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_videos, container, false);


        webView  = root.findViewById(R.id.web_view);


        url = "https://www.youtube.com/channel/UC-ORvLZG6VliMaSeX41EIIA";

        webView.loadUrl(url);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());




        return  root;
    }


}