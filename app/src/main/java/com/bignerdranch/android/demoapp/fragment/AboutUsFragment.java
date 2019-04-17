package com.bignerdranch.android.demoapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.demoapp.R;
import com.bignerdranch.android.demoapp.RecyclerAnnouncements;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AboutUsFragment extends Fragment {

    //Instance of Firebasedatabase
    final private FirebaseDatabase database = FirebaseDatabase.getInstance();
    //Instance of our actual database
    private DatabaseReference mDatabaseReference;

    private RecyclerView recyclerView;

    // Need to create Model "ACMLeaders"
    // private List<ACMMember> mACMLeaders;

    public AboutUsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // mACMLeaders = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_about, container, false);

        getActivity().setTitle("About Us");

        return view;
    }


}
