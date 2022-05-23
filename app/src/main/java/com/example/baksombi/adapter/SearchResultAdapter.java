package com.example.baksombi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.baksombi.R;
import com.example.baksombi.model.Animal;
import com.example.baksombi.view.holder.SearchViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    String language;

    List<Animal> animals;


    public SearchResultAdapter(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType)
    {
        this.language = parent.getContext().getResources().getConfiguration().locale.getLanguage();
        View itemView
                = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_animal_list_item,
                        parent,
                        false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SearchViewHolder holder,
                                 final int position)
    {
        holder.setSubject(animals.get(position));
        holder.getLabelSearch().setText(animals.get(position).getName(this.language));
        Picasso.get().load(animals.get(position).getImgURL()).into(holder.getImgSearch());
    }
    @Override
    public int getItemCount()
    {
        return animals.size();
    }
}
