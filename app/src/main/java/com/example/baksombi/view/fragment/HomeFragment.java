package com.example.baksombi.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.baksombi.R;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getChildFragmentManager().beginTransaction().replace(R.id.home_content, new DiscoverFragment()).commit();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}