package id.cuxxie.newslistapps.View.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
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
import id.cuxxie.newslistapps.Model.DataModel.Source;
import id.cuxxie.newslistapps.R;

/**
 * Created by hendr on 9/6/2017.
 */

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.ViewHolder> {
    ArrayList<Source> sources;
    SourcesAdapterOnItemClickListener listener;
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.list_item_source_name) TextView name;
        @BindView(R.id.list_item_source_desc) TextView desc;
        @BindView(R.id.list_item_source_url) TextView url;
        Button button;
        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);
        }

        public void bind(Source source)
        {
            name.setText(source.getName());
            desc.setText(source.getDescription());
            url.setText(source.getUrl());
        }
    }
    public SourcesAdapter(ArrayList<Source> sources, SourcesAdapterOnItemClickListener listener) {
        this.sources = sources;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.source_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(sources.get(position));
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
        return sources.size();
    }
    public void sourceClickedAt(int index){
        listener.onItemClicked(sources.get(index));
    }

    public void setSourcesWithNewData(ArrayList<Source> sources)
    {
        this.sources = sources;
    }

    public ArrayList<Source> getSources() {
        return sources;
    }

    public interface SourcesAdapterOnItemClickListener{
        public void onItemClicked(Source source);
    }
}
