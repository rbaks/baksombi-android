package com.example.baksombi.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import androidx.recyclerview.widget.RecyclerView;

import com.example.baksombi.R;
import com.example.baksombi.model.Category;
import com.example.baksombi.view.holder.CategoryViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

// The adapter class which
// extends RecyclerView Adapter
public class DiscoverCategoryViewAdapter
        extends RecyclerView.Adapter<CategoryViewHolder> {

    String language;
    // List with String type
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    // View Holder class which
    // extends RecyclerView.ViewHolder

    // Constructor for adapter class
    // which takes a list of String type
    public DiscoverCategoryViewAdapter(List<Category> horizontalList)
    {
        this.categories = horizontalList;
    }

    // Override onCreateViewHolder which deals
    // with the inflation of the card layout
    // as an item for the RecyclerView.
    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent,
                                     int viewType)
    {

        this.language = parent.getContext().getResources().getConfiguration().locale.getLanguage();
        // Inflate item.xml using LayoutInflator
        View itemView
                = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_category_item,
                        parent,
                        false);

        // return itemView
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder,
                                 final int position)
    {
        holder.setId(categories.get(position).getId());
        holder.getLblCategoryName().setText(categories.get(position).getName(this.language));
        Picasso.get().load(categories.get(position).getImgURL()).into(holder.getImgCategory());
    }

    // Override getItemCount which Returns
    // the length of the RecyclerView.
    @Override
    public int getItemCount()
    {
        return categories.size();
    }
}