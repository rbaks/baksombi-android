package com.example.baksombi.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.baksombi.R;
import com.example.baksombi.view.fragment.HomeFragment;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baksombi.view.fragment.ProfileFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    private ChipNavigationBar navbarBottom;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new HomeFragment()).commit();
        /*title = findViewById(R.id.lbl_title);
        ChipNavigationBar navbar = findViewById(R.id.navbar_bottom);
        navbar.setItemSelected(R.id.navigation_home, true);
        title.setText(getString(R.string.title_home));
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new AnimalDetailFragment()).commit();*/
    }

}