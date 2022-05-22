package com.example.baksombi.view.fragment;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.baksombi.R;
import com.example.baksombi.helper.ThemeHelper;
import com.example.baksombi.view.activity.MainActivity;

import java.util.HashMap;
import java.util.Map;


public class ThemeFragment extends Fragment {

    private View view;
    private ImageButton pinkTheme;
    private ImageButton orangeTheme;
    private ImageButton blueTheme;

    public ThemeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_theme, container, false);
        pinkTheme = view.findViewById(R.id.img_btn_pink_theme);
        blueTheme = view.findViewById(R.id.img_btn_blue_theme);
        orangeTheme = view.findViewById(R.id.img_btn_orange_theme);
        initializeUI();
        initListener();
        return view;
    }
    private void initListener(){
        pinkTheme.setOnClickListener(new ThemeChangeButtonListener());
        blueTheme.setOnClickListener(new ThemeChangeButtonListener());
        orangeTheme.setOnClickListener(new ThemeChangeButtonListener());
    }
    private void initializeUI(){
        int themeSelected = getContext()
                .getSharedPreferences(MainActivity.PREFERENCE, Context.MODE_PRIVATE)
                .getInt(MainActivity.PREFERENCE, R.id.img_btn_blue_theme);
        changeUISelected(themeSelected);
    }
    private void changeUISelected(int id){
        switch(id){
            case R.id.img_btn_blue_theme:
                blueTheme.setImageResource(R.drawable.cirlce_blue_selected);
                pinkTheme.setImageResource(R.drawable.circle_pink);
                orangeTheme.setImageResource(R.drawable.circle_orange);
                break;
            case R.id.img_btn_orange_theme:
                blueTheme.setImageResource(R.drawable.circle_blue);
                pinkTheme.setImageResource(R.drawable.circle_pink);
                orangeTheme.setImageResource(R.drawable.circle_orange_selected);
                break;
            case R.id.img_btn_pink_theme:
                blueTheme.setImageResource(R.drawable.circle_blue);
                pinkTheme.setImageResource(R.drawable.circle_pink_selected);
                orangeTheme.setImageResource(R.drawable.circle_orange);
                break;
            default:
                break;
        }
    }
    public class ThemeChangeButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            ThemeHelper.updateLocalThemeConfig( ThemeFragment.this.getContext(),view.getId());
            changeUISelected(view.getId());
        }
    }
}