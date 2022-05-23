package com.example.baksombi.view.fragment;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.baksombi.R;
import com.example.baksombi.model.Animal;
import com.example.baksombi.view.activity.SubmainActivity;
import com.example.baksombi.view.holder.SearchViewHolder;
import com.squareup.picasso.Picasso;

public class AnimalDetailFragment extends Fragment {

    String language;
    private View view;
    private TextView name;
    private TextView reproduction;
    private TextView home;
    private TextView food;
    private ImageView img;
    private Button readWikipedia;
    private ProgressBar progressBar;
    LinearLayout container;
    String url;
    public AnimalDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_animal_detail, container, false);
        Bundle bundle = getArguments();
        this.container = view.findViewById(R.id.ll_detail);
        language = getContext().getResources().getConfiguration().locale.getLanguage();
        this.name = view.findViewById(R.id.lbl_animal_title);
        this.reproduction = view.findViewById(R.id.lbl_ovipare_value);
        this.home = view.findViewById(R.id.lbl_home_value);
        this.food = view.findViewById(R.id.lbl_food_value);
        this.img = view.findViewById(R.id.img_animal_detail);
        this.readWikipedia = view.findViewById(R.id.btn_read_article);
        this.progressBar = view.findViewById(R.id.progressBar);
        this.container.setVisibility(View.GONE);
        new AnimalDetailTask().execute(bundle.getString("animal_id"));
        return view;
    }

    public class AnimalDetailTask extends AsyncTask<String, String, Animal> {
        @Override
        protected Animal doInBackground(String... strings) {
            try{
                Animal animal = new Animal();
                animal.setId(strings[0]);
                return animal.getAnimal();
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Animal animal) {
            if(animal != null){
                name.setText(animal.getName(language));
                home.setText(animal.getHome(language));
                food.setText(animal.getFood(language));
                reproduction.setText(animal.getReproduction(language));
                Picasso.get().load(animal.getImgURL()).into(img);
                url = animal.getWikipediaURL(language);
                readWikipedia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AnimalDetailFragment.this.getContext(), SubmainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("url", url);
                        bundle.putString(SubmainActivity.TITLE, animal.getName(language));
                        bundle.putInt(SubmainActivity.FRAGMENT, R.layout.fragment_web_view);
                        intent.putExtras(bundle);
                        AnimalDetailFragment.this.view.getContext().startActivity(intent);
                    }
                });
                container.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        }
    }
}