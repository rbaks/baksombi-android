package com.example.baksombi.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baksombi.R;
import com.example.baksombi.dto.LogoutDto;
import com.example.baksombi.helper.HttpHelper;
import com.example.baksombi.model.Token;
import com.example.baksombi.view.activity.AuntheticationActivity;
import com.example.baksombi.view.activity.MainActivity;
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
    public class LogoutTask extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... strings) {
            try{
                LogoutDto dto = new LogoutDto();
                dto.setRefreshToken(strings[0]);
                HttpHelper.getInstance().post("/auth/logout",null, dto);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            return null;
        }
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
            SharedPreferences preferences = getActivity().getSharedPreferences(MainActivity.PREFERENCE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            new LogoutTask().execute(preferences.getString(Token.PREF_REFRESH, ""));
            editor.clear();
            editor.commit();
            HttpHelper.getInstance().setToken(null);
            Intent intent = new Intent(getContext(), AuntheticationActivity.class);
            getActivity().startActivity(intent);
            getActivity().finish();
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