package com.example.baksombi.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.baksombi.R;
import com.example.baksombi.ui.home.HomeFragment;
import com.example.baksombi.view.fragment.AnimalDetailFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.baksombi.databinding.ActivityMainBinding;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    private ChipNavigationBar navbarBottom;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_animal_detail, new AnimalDetailFragment()).commit();
        /*title = findViewById(R.id.lbl_title);
        ChipNavigationBar navbar = findViewById(R.id.navbar_bottom);
        navbar.setItemSelected(R.id.navigation_home, true);
        title.setText(getString(R.string.title_home));
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new AnimalDetailFragment()).commit();*/
    }

}