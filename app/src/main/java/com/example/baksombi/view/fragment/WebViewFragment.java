package com.example.baksombi.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.baksombi.R;


public class WebViewFragment extends Fragment {

    View view;
    WebView wikipedia;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_web_view, container, false);
        Bundle bundle = getArguments();
        wikipedia = view.findViewById(R.id.wb_wikipedia);
        wikipedia.getSettings().setJavaScriptEnabled(true);
        wikipedia.getSettings().setLoadWithOverviewMode(true);
        wikipedia.getSettings().setUseWideViewPort(true);
        wikipedia.getSettings().setBuiltInZoomControls(true);
        wikipedia.getSettings().setPluginState(WebSettings.PluginState.ON);
        wikipedia.setWebViewClient(new WebViewClient());
        wikipedia.loadUrl(bundle.getString("url"));
        return view;
    }


}