package com.bignerdranch.android.demoapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.demoapp.ArticleAdapter;
import com.bignerdranch.android.demoapp.R;
import com.bignerdranch.android.demoapp.RecyclerAnnouncements;
import com.bignerdranch.android.demoapp.model.Article;
import com.bignerdranch.android.demoapp.model.announcements.Announcement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    final private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference;

    private ArticleAdapter mArticleAdapter;
    private RecyclerView mRecyclerView;

    private List<Article> mArticleList;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = rootView.findViewById(R.id.home_recycler);
        getActivity().setTitle("Home");

        mArticleList = new ArrayList<>();

        mRecyclerView= rootView.findViewById(R.id.home_recycler);

        mArticleAdapter = new ArticleAdapter(mArticleList, container.getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        mRecyclerView.setAdapter(mArticleAdapter);

        retrieveData();


        return rootView;
    }

    public void retrieveData() {
        //Gets the reference from the firebase database.
        mDatabaseReference = database.getReference();

        //queries specific location on database, in this case the child of the root (project) node
        //is Announcements, so we want that information.
        Query query = mDatabaseReference.child("Articles");

        //In order to listen for changes, we will add a listener.
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            //Returns a new "dataSnapshot" of how the data in the database looks.
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //For every single snapshot in our data snapshot, we will get their children - these will
                //be the "announcement1" and "announcement2".
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    //Here, we use our model to create an object of the singleSnapshot.
                    Article article = singleSnapshot.getValue(Article.class);
                    //Adds this onto the list we have in this class.
                    mArticleList.add(article);
                }
                mArticleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Do Nothing
            }
        });
    }
}
