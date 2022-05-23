package com.example.baksombi.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.baksombi.R;
import com.example.baksombi.adapter.ViewPagerAdapter;
import com.example.baksombi.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;

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