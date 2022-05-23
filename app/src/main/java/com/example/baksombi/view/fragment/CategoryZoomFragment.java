package com.example.baksombi.view.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.baksombi.R;
import com.example.baksombi.model.Category;


public class CategoryZoomFragment extends Fragment {

    private View view;
    private Button nextButton;
    private Button previousButton;
    private Category category;
    private int currentIndex = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_zoom, container, false);
        Bundle bundle = getArguments();
        category = new Category();
        category.setId(bundle.getString("category_id"));
        nextButton = view.findViewById(R.id.btn_next);
        nextButton.setClickable(false);
        previousButton = view.findViewById(R.id.btn_previous);
        new CategoryZoomTask().execute();
        initButtonListener();
        return view;
    }
    private void  next(){
        if(category.getAnimals()!=null){
            if(category.getAnimals().length-1 > currentIndex){
                currentIndex = currentIndex +1;
                changeAnimalDetailFragment(category.getAnimals()[currentIndex]);
            }
        }
    }

    private void previous(){
        if(category.getAnimals()!=null){
            if(currentIndex > 0){
                currentIndex = currentIndex - 1;
                changeAnimalDetailFragment(category.getAnimals()[currentIndex]);
            }
        }
    }
    private void initButtonListener(){
        this.nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                next();
            }
        });

        this.previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previous();
            }
        });
    }
    private void changeAnimalDetailFragment(String id){
        Fragment fragment = new AnimalDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("animal_id", id);
        fragment.setArguments(bundle);
        CategoryZoomFragment.this
                .getChildFragmentManager()
                .beginTransaction().replace(R.id.fl_animal_detail, fragment)
                .commit();
    }
    private class CategoryZoomTask extends AsyncTask<String, String, Category> {
        @Override
        protected void onPostExecute(Category category) {
            if(category != null){
                CategoryZoomFragment.this.category = category;
                changeAnimalDetailFragment(category.getAnimals()[0]);
            }
        }

        @Override
        protected Category doInBackground(String... strings) {
            try{
                return category.getCategory();
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }

}