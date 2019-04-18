package com.bignerdranch.android.demoapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.demoapp.model.ACMLeader;
import com.squareup.picasso.Picasso;


import java.util.List;

public class AboutRecylerAdapter extends RecyclerView.Adapter<AboutRecylerAdapter.AboutViewHolder> {

    private List<ACMLeader> leaders;
    private Context mContext;

    public AboutRecylerAdapter(List<ACMLeader> leaders, Context context) {
        this.leaders = leaders;
        this.mContext = context;
    }

    @NonNull
    @Override
    public AboutRecylerAdapter.AboutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.acm_leader_card,viewGroup,false);


        return new AboutRecylerAdapter.AboutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AboutRecylerAdapter.AboutViewHolder recyclerViewHolder, int i) {
        recyclerViewHolder.bind(i);
    }

    //Returns the size of the array.
    @Override
    public int getItemCount() {
        return leaders.size();
    }

    public class AboutViewHolder extends RecyclerView.ViewHolder{
        TextView leaderName;
        TextView leaderRole;
        ImageView leaderImage;

        public AboutViewHolder(View view) {
            super(view);

            leaderName = view.findViewById(R.id.about_name);
            leaderRole = view.findViewById(R.id.about_role);
            leaderImage = view.findViewById(R.id.leader_img);
        }

        void bind(final int position){
            leaderName.setText(leaders.get(position).getName());
            leaderRole.setText(leaders.get(position).getRole());
            // Need to set a placeholder

            // The URL works.
//            System.out.println(leaders.get(position).getImgUrl());

//            ***There's definitely an error with Picasso
            /**
             * For some reason, Picasso has trouble getting the image from ACM's website.
             * It has NO trouble getting the image from Firebase. So, we simply changed the URL's
             * on Firebase that send us to the image stored on Firebase, not ACM's server.
             */
            Picasso.with(mContext)
                    .load(leaders.get(position).getImgUrl())
                    .error(R.drawable.ic_home_black_24dp)
                    .placeholder(R.drawable.ic_portrait_black_24dp)
                    .into(leaderImage);


//            Picasso.get().load(leaders.get(position).getImgUrl()).into
//            mAnnouncementTitle.setText(announcements.get(position).getTitle());
//            mAnnouncementDate.setText(announcements.get(position).getDate());
//            mAnnouncementBody.setText(announcements.get(position).getBody());
//            mAnnouncementAuthor.setText(announcements.get(position).getAuthor());
        }

    }
}
