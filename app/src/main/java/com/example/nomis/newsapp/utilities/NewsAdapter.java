package com.example.nomis.newsapp.utilities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nomis.newsapp.R;
import com.example.nomis.newsapp.model.NewsItem;
import java.util.ArrayList;
/**
 * Created by Nomis on 6/29/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ArticleHolder>{

    private ArrayList<NewsItem> data;
    //ItemClickListener listener;

    public NewsAdapter(ArrayList<NewsItem> data){
        this.data = data;
    }
    /*    public interface ItemClickListener{
            void onItemClick(int clickedItemIndex);
        }*/
    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.article, viewGroup, shouldAttachToParentImmediately);
        ArticleHolder holder = new ArticleHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ArticleHolder holder, int position){
        holder.bind(position);
    }

    @Override
    public int getItemCount(){
        return data.size();
    }

    class ArticleHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView description;
        TextView publishedAt;

        ArticleHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.description);
            publishedAt = (TextView) view.findViewById(R.id.publishedAt);
            //view.setOnClickListener(this);
        }

        public void bind(int pos){
            NewsItem news = data.get(pos);
            title.setText(news.getTitle());
            description.setText(news.getDescription());
            publishedAt.setText(news.getPublishedAt());
        }
    }

}
