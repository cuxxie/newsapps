package id.cuxxie.newslistapps.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.cuxxie.newslistapps.Model.DataModel.Article;
import id.cuxxie.newslistapps.Model.DataModel.Source;
import id.cuxxie.newslistapps.R;

/**
 * Created by hendr on 9/6/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>  {
    ArrayList<Article> articles;
    Context mContext;
    NewsAdapterOnItemClickListener listener;
    static class ViewHolder extends RecyclerView.ViewHolder {
        Context mContext;
        @BindView(R.id.list_item_article_image) ImageView imageView;
        @BindView(R.id.list_item_article_title) TextView title;
        @BindView(R.id.list_item_article_desc) TextView desc;
        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);
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
        return new NewsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(articles.get(position));
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
        return articles.size();
    }

    public NewsAdapter(ArrayList<Article> articles, Context mContext, NewsAdapterOnItemClickListener listener) {
        this.articles = articles;
        this.mContext = mContext;
        this.listener = listener;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public void sourceClickedAt(int index){
        listener.onItemClicked(articles.get(index));
    }

    public interface NewsAdapterOnItemClickListener{
        public void onItemClicked(Article article);
    }
}
