package com.example.baksombi.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.baksombi.R;
import com.example.baksombi.view.fragment.LanguageFragment;
import com.example.baksombi.view.fragment.ThemeFragment;

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
            case R.layout.fragment_language:
                return new LanguageFragment();
            case R.layout.fragment_theme:
                return new ThemeFragment();
            default:
                break;
        }
        return fragment;
    }
}