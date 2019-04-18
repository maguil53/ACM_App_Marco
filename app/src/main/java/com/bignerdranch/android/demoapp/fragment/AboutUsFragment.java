package com.bignerdranch.android.demoapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.demoapp.AboutRecylerAdapter;
import com.bignerdranch.android.demoapp.R;
import com.bignerdranch.android.demoapp.RecyclerAnnouncements;
import com.bignerdranch.android.demoapp.model.ACMLeader;
import com.bignerdranch.android.demoapp.model.announcements.Announcement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AboutUsFragment extends Fragment {

    private final String TAG = "AboutUsFragment";

    //Instance of Firebasedatabase
    final private FirebaseDatabase database = FirebaseDatabase.getInstance();
    //Instance of our actual database
    private DatabaseReference mDatabaseReference;

    private RecyclerView recyclerView;
    private AboutRecylerAdapter mAdapter;

    // Need to create Model "ACMLeaders"
     private List<ACMLeader> mACMLeaders;

    public AboutUsFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mACMLeaders = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_about, container, false);
        recyclerView = view.findViewById(R.id.leaders_recycler);



        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        // Activity extends from "context". Thus, Activity IS a Context.
        mAdapter = new AboutRecylerAdapter(mACMLeaders, getActivity());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        // This is used to swipe one item at a time for your RecyclerView
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);




        getActivity().setTitle("About Us");

        retrieveData();


        return view;
    }

    public void retrieveData() {
        //Gets the reference from the firebase database.
        mDatabaseReference = database.getReference();

        //queries specific location on database, in this case the child of the root (project) node
        //is Announcements, so we want that information.
        Query query = mDatabaseReference.child("Leaders");

        //In order to listen for changes, we will add a listener.
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            //Returns a new "dataSnapshot" of how the data in the database looks.
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //For every single snapshot in our data snapshot, we will get their children - these will
                //be the "announcement1" and "announcement2".

                /**
                 * Remember that your Model's attribute need to be spelled the same as they are in
                 * Firebase, if not then you'll run into some errors (I think this is still the case).
                 */
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    //Here, we use our model to create an object of the singleSnapshot.
                    ACMLeader leader = singleSnapshot.getValue(ACMLeader.class);
                    //Adds this onto the list we have in this class.
                    mACMLeaders.add(leader);

                }


                mAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Do Nothing
            }
        });
    }

    // Gets current item of ReyclerView using it's LayoutManager
    private int getCurrentItem() {
        return ((LinearLayoutManager) recyclerView.getLayoutManager())
                .findFirstVisibleItemPosition();
    }

}
