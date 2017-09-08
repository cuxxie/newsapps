package id.cuxxie.newslistapps.Presenter.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.cuxxie.newslistapps.Model.DataModel.Article;
import id.cuxxie.newslistapps.R;

/**
 * Created by hendr on 9/6/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>  {
    ArrayList<Article> displayedArticles;
    ArrayList<Article> articles;
    Context mContext;
    NewsAdapterOnItemClickListener listener;
    String filterText = "";
    static class ViewHolder extends RecyclerView.ViewHolder {
        Context mContext;
        @BindView(R.id.list_item_article_image) ImageView imageView;
        @BindView(R.id.list_item_article_title) TextView title;
        @BindView(R.id.list_item_article_desc) TextView desc;
        public ViewHolder(View v,Context context) {
            super(v);
            ButterKnife.bind(this,v);
            this.mContext = context;
        }

        public void bind(Article article)
        {
            title.setText(article.getTitle());
            desc.setText(article.getDescription());
            Glide.with(mContext).load(article.getUrlToImage()).into(imageView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_list_item, parent, false);
        return new NewsAdapter.ViewHolder(v,mContext);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(displayedArticles.get(position));
        final int index = holder.getAdapterPosition();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sourceClickedAt(index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return displayedArticles.size();
    }

    public NewsAdapter(ArrayList<Article> articles, Context mContext, NewsAdapterOnItemClickListener listener, String filterText) {
        this.articles = articles;
        this.displayedArticles = new ArrayList<>(articles);
        this.mContext = mContext;
        this.listener = listener;
        this.filterText = filterText;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
        this.displayedArticles = new ArrayList<>(articles);
        searchText(filterText);
    }

    public void sourceClickedAt(int index){
        listener.onItemClicked(articles.get(index));
    }


    public String getFilterText() {
        return filterText;
    }

    public void setFilterText(String filterText) {
        this.filterText = filterText;
    }

    public void searchText(String search)
    {
        filterText = search;
        if(filterText.length() > 0)
        {
            displayedArticles = new ArrayList<>(articles);
            ArrayList<Article> removeList = new ArrayList<>();
            for(Article item: displayedArticles){
                if(item.getTitle().toLowerCase().contains(filterText.toLowerCase()))
                {}
                else
                    removeList.add(item);
            }
            for(Article item: removeList)
                displayedArticles.remove(item);

            listener.onFinishedSearch();
        }
    }


    public interface NewsAdapterOnItemClickListener{
        void onItemClicked(Article article);
        void onFinishedSearch();
    }
}
