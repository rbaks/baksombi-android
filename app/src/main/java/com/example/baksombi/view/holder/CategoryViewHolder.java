package com.example.baksombi.view.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.baksombi.R;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baksombi.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    private TextView lblCategoryName;
    private ImageView imgCategory;

    public TextView getLblCategoryName() {
        return lblCategoryName;
    }

    public ImageView getImgCategory() {
        return imgCategory;
    }

    // parameterised constructor for View Holder class
    // which takes the view as a parameter
    public CategoryViewHolder(View view)
    {
        super(view);
        // initialise TextView with id
        lblCategoryName = (TextView)view
                .findViewById(R.id.lbl_category_discover_name);
        imgCategory = (ImageView) view.findViewById(R.id.img_category_discover);
    }
}
