package com.example.gargc.avruttilabs.Fragments;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gargc.avruttilabs.Model.Offer;
import com.example.gargc.avruttilabs.Model.SubCategory;
import com.example.gargc.avruttilabs.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */


public class Offers extends Fragment {

    RecyclerView subcategoryRecyclerView;
    RecyclerView itemListRecyclerView;
    ArrayList<String> subCategoryName;
    DatabaseReference mDatabase;

    public Offers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragmentlayout, container, false);

        Log.i("offers","yu");

        subcategoryRecyclerView=(RecyclerView) view.findViewById(R.id.subcategory_content);
        itemListRecyclerView=(RecyclerView) view.findViewById(R.id.subcategory_item_layout);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Category").child("category1");

        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        subcategoryRecyclerView.setLayoutManager(layoutManager);
        subcategoryRecyclerView.hasFixedSize();

        return view;

    }

    @Override
    public void onStart()
    {
        super.onStart();

        FirebaseRecyclerAdapter<SubCategory,MyViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<SubCategory, MyViewHolder>
                (
                    SubCategory.class,
                    R.layout.subcategorybtn,
                    MyViewHolder.class,
                    mDatabase
                )
        {

            @Override
            protected void populateViewHolder(MyViewHolder viewHolder, SubCategory model, int position)
            {

                Log.i("data",model.getName());
                viewHolder.btn.setText(model.getName());
            }
        };

        firebaseRecyclerAdapter.notifyDataSetChanged();
        subcategoryRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        Button btn;

        public MyViewHolder(View itemView)
        {
            super(itemView);

            btn = (Button)itemView.findViewById(R.id.subcategorybutton);
        }

    }

}
