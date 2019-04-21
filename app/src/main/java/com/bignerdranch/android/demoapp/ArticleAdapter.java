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


import com.bignerdranch.android.demoapp.model.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private List<Article> mArticles;
    private Context mContext;

    public ArticleAdapter(List<Article> articles, Context context) {
        this.mArticles = articles;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ArticleAdapter.ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.article_item,viewGroup,false);

        return new ArticleAdapter.ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ArticleViewHolder recyclerViewHolder, int i) {
        recyclerViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        ImageView articleImage;
        TextView authorText;
        TextView titleText;
        TextView descriptionText;
        TextView dateText;
        CircleImageView profileImage;
        ImageView github;
        ImageView linkedin;




        public ArticleViewHolder(View view) {
            super(view);

            articleImage = view.findViewById(R.id.article_image_url);
            authorText = view.findViewById(R.id.article_author);
            titleText = view.findViewById(R.id.article_title);
            descriptionText = view.findViewById(R.id.article_description);
            dateText = view.findViewById(R.id.article_date);

            profileImage = view.findViewById(R.id.profile_image);
            github = view.findViewById(R.id.github);
            linkedin = view.findViewById(R.id.linkedin);
        }

        void bind(final int position){
            authorText.setText(mArticles.get(position).getAuthor());
            titleText.setText(mArticles.get(position).getTitle());
            descriptionText.setText(mArticles.get(position).getDescription());
            dateText.setText(mArticles.get(position).getDate());

            github.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String urlString = mArticles.get(getAdapterPosition()).getGithub();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                    mContext.startActivity(browserIntent);
                }
            });

            linkedin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String urlString = mArticles.get(getAdapterPosition()).getLinkedin();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                    mContext.startActivity(browserIntent);
                }
            });


            Picasso.with(mContext)
                    .load(mArticles.get(position).getAuthorImg())
                    .error(R.drawable.ic_home_black_24dp)
                    .placeholder(R.drawable.ic_android_green_24dp)
                    .into(profileImage);

            Picasso.with(mContext)
                    .load(mArticles.get(position).getImgUrl())
                    .error(R.drawable.ic_home_black_24dp)
                    .placeholder(R.drawable.ic_android_green_24dp)
                    .into(articleImage);


        }
    }


}

