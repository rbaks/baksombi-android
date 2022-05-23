package com.example.baksombi.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.baksombi.R;
import com.example.baksombi.view.fragment.AnimalDetailFragment;
import com.example.baksombi.view.fragment.CategoryZoomFragment;
import com.example.baksombi.view.fragment.LanguageFragment;
import com.example.baksombi.view.fragment.ThemeFragment;
import com.example.baksombi.view.fragment.WebViewFragment;

import org.w3c.dom.Text;

public class SubmainActivity extends BaseActivity {
    //On va utiliser cela pour faire des pages statiques

    public static final String TITLE = "title";
    public static final String FRAGMENT = "fragment";

    //vues statiques
    private TextView titleCustom;
    private Button button;

    public TextView getTitleCustom() {
        return titleCustom;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        setContentView(R.layout.activity_submain);
        titleCustom = findViewById(R.id.lbl_page_title);
        this.titleCustom.setText(bundle.getString(TITLE));
        button = findViewById(R.id.btn_back_activity);
        initButtonBack();
        Fragment fragment = getFragment(bundle.getInt(FRAGMENT));
        fragment.setArguments(bundle);
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_submain_content, fragment).commit();
        }
    }

    private void initButtonBack(){
        this.button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                exitActivity();
            }
        });
    }

    private void exitActivity(){
        finish();
    }

    private Fragment getFragment(int id){
        Fragment fragment = null;
        switch(id){
            case R.layout.fragment_category_zoom:
                return new CategoryZoomFragment();
            case R.layout.fragment_language:
                return new LanguageFragment();
            case R.layout.fragment_theme:
                return new ThemeFragment();
            case R.layout.fragment_animal_detail:
                return new AnimalDetailFragment();
            case R.layout.fragment_web_view:
                return new WebViewFragment();
            default:
                break;
        }
        return fragment;
    }
}