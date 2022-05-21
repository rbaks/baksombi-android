package com.example.baksombi.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baksombi.R;
import com.example.baksombi.view.activity.MainActivity;


public class LanguageFragment extends Fragment {

    CardView englishLanguage;
    CardView frenchLanguage;

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
        this.frenchLanguage = this.view.findViewById(R.id.cv_french_language);
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
        card.setBackground(null);
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
        }
    }
}