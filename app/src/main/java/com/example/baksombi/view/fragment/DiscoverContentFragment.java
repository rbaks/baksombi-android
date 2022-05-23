package com.example.baksombi.view.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baksombi.R;
import com.example.baksombi.adapter.DiscoverCategoryViewAdapter;
import com.example.baksombi.model.Category;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;


public class DiscoverContentFragment extends Fragment {

    private RecyclerView recycler;
    private View view;
    public DiscoverContentFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_discover_content, container, false);
        recycler = this.view.findViewById(R.id.rv_category_list);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler.setLayoutManager(layoutManager);
        CategoryTask task = new CategoryTask();
        task.execute();
        initializeYoutubeVideo();
        return this.view;
    }

    public void initializeYoutubeVideo(){
        YouTubePlayerView youtubePlayerView = this.view.findViewById(R.id.vd_youtube);
        getLifecycle().addObserver(youtubePlayerView);
        youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "fM4m0oMA_Mw";
                youTubePlayer.cueVideo(videoId, 0);
            }
        });
    }

    public class CategoryTask extends AsyncTask<String, String, List<Category>>{

        @Override
        protected List<Category> doInBackground(String... strings) {
            try{
                return new Category().getCategories();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Category> categories){
            if(categories != null){
                DiscoverCategoryViewAdapter adapter = new DiscoverCategoryViewAdapter(categories);
                recycler.setAdapter(adapter);
            }
        }
    }

}