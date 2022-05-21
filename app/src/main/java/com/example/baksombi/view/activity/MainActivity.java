package com.example.baksombi.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.baksombi.R;
import com.example.baksombi.view.fragment.HomeFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.example.baksombi.view.fragment.ProfileFragment;
import com.example.baksombi.view.fragment.QuizFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    private ChipNavigationBar navbar;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navbar = findViewById(R.id.navbar_bottom);
        navbar.setItemSelected(R.id.navigation_home, true);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_content, new HomeFragment(), new Integer(R.id.navigation_home).toString())
                .commit();
        this.bottomMenuNavigation();
    }

    private void bottomMenuNavigation(){
        navbar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                String tag = new Integer(i).toString();
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if(fragment == null){
                    switch(i){
                        case R.id.navigation_home:
                            fragment = new HomeFragment();
                            break;
                        case R.id.navigation_quiz:
                            fragment = new QuizFragment();
                            break;
                        case R.id.navigation_profile:
                            fragment = new ProfileFragment();
                            break;
                        default:
                            break;
                    }
                }
                if(fragment!=null){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fl_content, fragment, tag)
                            .addToBackStack(tag)
                            .commit();
                }

            }
        });
    }


}