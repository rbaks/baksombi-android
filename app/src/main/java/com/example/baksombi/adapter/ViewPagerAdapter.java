package com.example.baksombi.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.baksombi.R;
import com.example.baksombi.view.fragment.DiscoverFragment;
import com.example.baksombi.view.fragment.MyCourseFragment;
import com.example.baksombi.view.fragment.ProfileFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    public ViewPagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch(position){
            case 0:
                fragment = new DiscoverFragment();
                break;
            case 1:
                fragment = new MyCourseFragment();
                break;
            default:
                break;
        }
        return fragment;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return this.context.getString(R.string.discover);
            case 1:
                return this.context.getString(R.string.my_courses);
            case 2:
                return "Download";
        }
        return null;
    }
    @Override
    public int getCount() {
        return 2;
    }
}
