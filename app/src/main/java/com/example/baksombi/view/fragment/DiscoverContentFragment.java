package com.example.baksombi.view.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baksombi.R;
import com.example.baksombi.helper.HttpHelper;
import com.example.baksombi.model.Category;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiscoverContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscoverContentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    public DiscoverContentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiscoverContentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiscoverContentFragment newInstance(String param1, String param2) {
        DiscoverContentFragment fragment = new DiscoverContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_discover_content, container, false);
        DiscoverHandler  handler = new DiscoverHandler();
        handler.execute();
        return this.view;
    }

    public class DiscoverHandler extends AsyncTask<String, String, Category> {

        @Override
        protected Category doInBackground(String... strings) {
            try{
                return new Category().getCategory();
            }
            catch(Exception e){
                e.printStackTrace();
                //Toast.makeText(DiscoverContentFragment.this.getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Category category) {
            if(category!=null){
                TextView welcome = DiscoverContentFragment.this.view.findViewById(R.id.lbl_welcome);
                welcome.setText(category.getStatus().toString());
            }
        }
    }
}