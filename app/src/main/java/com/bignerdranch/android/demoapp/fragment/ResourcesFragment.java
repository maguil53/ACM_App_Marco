package com.bignerdranch.android.demoapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.android.demoapp.R;
import com.bignerdranch.android.demoapp.RecyclerHackathons;
import com.bignerdranch.android.demoapp.model.mlh.Event;
import com.bignerdranch.android.demoapp.retrofit.APIClient;
import com.bignerdranch.android.demoapp.retrofit.MLHInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResourcesFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerHackathons recyclerAdapter;
    List<Event> hackathons;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Understanding this completely is out of the scope of the workshop
        //Just know that we are getting our root view since we are using a fragment instead of an activity.
        //We must know what activity we are on.
        View rootView = inflater.inflate(R.layout.fragment_resources, container, false);

        recyclerView = rootView.findViewById(R.id.hackathons_recycler);


        //LoadJSON is a custom method, we take in the parameter container since this is a fragment.
        LoadJson(container);

        return rootView;
    }

    public void LoadJson(final ViewGroup container){
        MLHInterface apiInterface = APIClient.getApiClient().create(MLHInterface.class);

        Call<List<Event>> call;
        call = apiInterface.getEvents();

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                hackathons = filterEvents(response.body());

                recyclerAdapter = new RecyclerHackathons(hackathons, container.getContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });
    }

    public List<Event> filterEvents(final List<Event> allEvents){
        List<Event> caEvents = new ArrayList<>();
        for(int i = allEvents.size() - 1; i > 0; i--){
            //Filter California Events
            if(allEvents.get(i).getLocation().contains("CA")){
                caEvents.add(allEvents.get(i));
            }
        }
        return caEvents;
    }


}
