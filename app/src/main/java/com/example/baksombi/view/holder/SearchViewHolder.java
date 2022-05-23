package com.example.baksombi.view.holder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baksombi.R;
import com.example.baksombi.model.Animal;
import com.example.baksombi.view.activity.SubmainActivity;
import com.example.baksombi.view.fragment.ProfileFragment;

public class SearchViewHolder extends RecyclerView.ViewHolder {
    private TextView labelSearch;
    private ImageView imgSearch;
    private CardView cardAnimal;
    private Animal subject;

    public Animal getSubject() {
        return subject;
    }

    public void setSubject(Animal subject) {
        this.subject = subject;
    }

    public TextView getLabelSearch() {
        return labelSearch;
    }

    public void setLabelSearch(TextView labelSearch) {
        this.labelSearch = labelSearch;
    }

    public ImageView getImgSearch() {
        return imgSearch;
    }

    public void setImgSearch(ImageView imgSearch) {
        this.imgSearch = imgSearch;
    }

    // which takes the view as a parameter
    public SearchViewHolder(View view) {
        super(view);
        labelSearch = view.findViewById(R.id.lbl_search);
        imgSearch = view.findViewById(R.id.img_search);
        cardAnimal = view.findViewById(R.id.cv_animal);
        initCard();
    }

    private void initCard(){
        this.cardAnimal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchViewHolder.this.itemView.getContext(), SubmainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("animal_id", subject.getId());
                bundle.putString(SubmainActivity.TITLE, "");
                bundle.putInt(SubmainActivity.FRAGMENT, R.layout.fragment_animal_detail);
                intent.putExtras(bundle);
                SearchViewHolder.this.itemView.getContext().startActivity(intent);
            }
        });
    }
}
