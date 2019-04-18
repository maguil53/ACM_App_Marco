package com.bignerdranch.android.demoapp;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




/**
 *  Delete This Fragment later.
 *  Use AboutUsFragment under fragment directory instead.
 */
public class AboutFragment extends Fragment {


    public AboutFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("About Us");
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

}
