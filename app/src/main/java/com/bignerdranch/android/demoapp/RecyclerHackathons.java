package com.bignerdranch.android.demoapp;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.demoapp.model.mlh.Event;
import com.squareup.picasso.Picasso;


import java.util.List;



public class RecyclerHackathons extends RecyclerView.Adapter<RecyclerHackathons.HackathonsViewHolder> {


    private List<Event> mHackathons;
    private Context mContext;


    public RecyclerHackathons(List<Event> hackathons, Context context) {
        this.mHackathons = hackathons;
        this.mContext = context;
    }


    public class HackathonsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView mEventImage;
        TextView mEventTitle;
        TextView mEventStart;
        TextView mEventLocation;


        public HackathonsViewHolder(View itemView){
            super(itemView);
            mEventImage = (ImageView) itemView.findViewById(R.id.image_url);
            mEventTitle = (TextView) itemView.findViewById(R.id.title);
            mEventStart = (TextView) itemView.findViewById(R.id.start_date);
            mEventLocation = (TextView) itemView.findViewById(R.id.location);


        }

        //Here, we bind the information with the view itself.
        void bind(final int position){


            Picasso.with(mContext).load(mHackathons.get(position)
            .getImageUrl()).into(mEventImage);

            mEventTitle.setText(mHackathons.get(position).getName());
            mEventStart.setText(mHackathons.get(position).getStartDate());
            mEventLocation.setText(mHackathons.get(position).getLocation());
            //We also set each item view to have a onClickListener.
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            String urlString = mHackathons.get(getAdapterPosition()).getUrl();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
            mContext.startActivity(browserIntent);
        }
    }


    @NonNull
    @Override
    public HackathonsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hackathon_item,viewGroup,false);
        return new HackathonsViewHolder(view);
    }
    //Whenever you scroll down, the HackathonsView recycles an old view to be replace
    //with new information that is supplied. This is where that happens.
    @Override
    public void onBindViewHolder(@NonNull HackathonsViewHolder recyclerViewHolder, int i) {
        recyclerViewHolder.bind(i);
    }

    //Returns the size of the array.
    @Override
    public int getItemCount() {
        return mHackathons.size();
    }


}
