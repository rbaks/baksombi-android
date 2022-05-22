package com.example.baksombi.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baksombi.R;
import com.example.baksombi.view.activity.SubmainActivity;


public class ProfileFragment extends Fragment {

    View view;
    CardView theme;
    CardView language;
    CardView logout;

    public ProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.fragment_profile, container, false);
        this.theme = this.view.findViewById(R.id.cv_theme);
        this.language = this.view.findViewById(R.id.cv_language);
        this.logout = this.view.findViewById(R.id.cv_logout);
        this.initEvents();
        // Inflate the layout for this fragment
        return this.view;
    }

    private void initEvents(){
        this.language.setOnClickListener(new ProfileMenuListener());
        this.theme.setOnClickListener(new ProfileMenuListener());
        this.logout.setOnClickListener(new ProfileMenuListener());
    }

    public class ProfileMenuListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if(view.equals(theme)){
                themeEvent();
            }
            if(view.equals(language)){
                languageEvent();
            }
            if(view.equals(logout)){
                logoutEvent();
            }
        }

        private void logoutEvent(){

        }

        private void languageEvent(){
            Intent intent = new Intent(ProfileFragment.this.getContext(), SubmainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(SubmainActivity.TITLE, getResources().getString(R.string.language));
            bundle.putInt(SubmainActivity.FRAGMENT, R.layout.fragment_language);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        private void themeEvent(){
            Intent intent = new Intent(ProfileFragment.this.getContext(), SubmainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(SubmainActivity.TITLE, getResources().getString(R.string.theme));
            bundle.putInt(SubmainActivity.FRAGMENT, R.layout.fragment_theme);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}