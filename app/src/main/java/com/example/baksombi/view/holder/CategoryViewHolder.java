package com.example.baksombi.view.holder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.baksombi.R;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baksombi.R;
import com.example.baksombi.view.activity.SubmainActivity;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    private TextView lblCategoryName;
    private ImageView imgCategory;
    private CardView card;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
        card = view.findViewById(R.id.cv_category);
        // initialise TextView with id
        lblCategoryName = (TextView)view
                .findViewById(R.id.lbl_category_discover_name);
        imgCategory = (ImageView) view.findViewById(R.id.img_category_discover);
        initCard();
    }

    private void initCard(){

        this.card.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryViewHolder.this.itemView.getContext(), SubmainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("category_id", id);
                bundle.putString(SubmainActivity.TITLE, lblCategoryName.getText().toString());
                bundle.putInt(SubmainActivity.FRAGMENT, R.layout.fragment_category_zoom);
                intent.putExtras(bundle);
                CategoryViewHolder.this.itemView.getContext().startActivity(intent);
            }
        });
    }
}
