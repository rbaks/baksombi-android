package com.example.baksombi.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.baksombi.R;
import com.example.baksombi.adapter.SearchResultAdapter;
import com.example.baksombi.model.Animal;
import com.example.baksombi.view.activity.MainActivity;

import java.util.List;


public class SearchFragment extends Fragment {

    View view;
    String language;
    SearchView search;
    RecyclerView result;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        language = view.getContext().getResources().getConfiguration().locale.getDisplayLanguage();
        search = view.findViewById(R.id.search_animal);
        result = view.findViewById(R.id.rv_search);
        initList();
        initSearchListener();
        return view;
    }
    public void initList(){
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        result.setLayoutManager(layoutManager);
    }
    public void initSearchListener(){
        System.out.println("****************search listener setting entered***********");
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                new SearchTask().execute(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

    public class SearchTask extends AsyncTask<String, String, List<Animal>>{

        @Override
        protected List<Animal> doInBackground(String... strings) {
            try{
                SharedPreferences preferences = getContext().getSharedPreferences(MainActivity.PREFERENCE, Context.MODE_PRIVATE);
                language = preferences.getString(MainActivity.LANGUAGE,getContext().getResources().getConfiguration().locale.getDisplayLanguage());
                return new Animal().getAnimals(strings[0], language);
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Animal> animals) {
            if(animals != null){
                System.out.println(animals.size());
                SearchResultAdapter list = new SearchResultAdapter(animals);
                result.setAdapter(list);
            }
        }
    }

}