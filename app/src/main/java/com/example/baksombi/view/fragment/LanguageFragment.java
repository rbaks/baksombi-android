package com.example.baksombi.view.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baksombi.R;
import com.example.baksombi.helper.LocaleHelper;
import com.example.baksombi.view.activity.MainActivity;
import com.example.baksombi.view.activity.SubmainActivity;

import java.util.Locale;


public class LanguageFragment extends Fragment {

    CardView englishLanguage;
    TextView english;
    CardView frenchLanguage;
    TextView french;
    TextView title;
    Drawable defaultSelect;
    CardView selected;
    View view;
    public LanguageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_language, container, false);
        this.englishLanguage = this.view.findViewById(R.id.cv_english_language);
        this.english = this.view.findViewById(R.id.lbl_language_english);
        this.frenchLanguage = this.view.findViewById(R.id.cv_french_language);
        this.french = this.view.findViewById(R.id.lbl_language_french);
        this.defaultSelect = englishLanguage.getBackground();
        this.title = ((SubmainActivity)getActivity()).getTitleCustom();
        selectCurrentLanguage();
        initCardView();
        // Inflate the layout for this fragment
        return view;
    }

    private void selectCurrentLanguage(){
        selected = this.englishLanguage;

        selectUI(englishLanguage);
        String language = this.view.getContext().getSharedPreferences(MainActivity.PREFERENCE, Context.MODE_PRIVATE).getString(MainActivity.LANGUAGE, "en");
        if(language.equals("fr")){
            this.selected = frenchLanguage;
            selectUI(frenchLanguage);
            unselectUI(englishLanguage);
        }
    }

    private void unselectUI(CardView card){
        card.setBackground(defaultSelect);
    }

    private void selectUI(CardView card){
        card.setBackground(getResources().getDrawable(R.drawable.selected_rectangle));
    }

    private void initCardView(){
        this.frenchLanguage.setOnClickListener(new CardLanguageListener());
        this.englishLanguage.setOnClickListener(new CardLanguageListener());
    }
    public class CardLanguageListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            unselectUI(selected);
            selected = (CardView)view;
            selectUI(selected);
            updateLanguage(view);
        }

        public void updateLanguage(View view){
            if(view.equals(englishLanguage)){
                updateView("en");
            }
            if(view.equals(frenchLanguage)){
                updateView("fr");
            }
        }

        public void updateView(String language){
            LocaleHelper.updateLocaleConfig(getActivity().getBaseContext(),language);
            Resources res = getActivity().getResources();

            english.setText(res.getString(R.string.english));
            french.setText(res.getString(R.string.french));
            title.setText(res.getString(R.string.language));
        }


    }
}