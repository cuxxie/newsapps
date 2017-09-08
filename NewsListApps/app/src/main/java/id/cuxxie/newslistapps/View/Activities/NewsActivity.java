package id.cuxxie.newslistapps.View.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.cuxxie.newslistapps.Model.DataModel.Article;
import id.cuxxie.newslistapps.Model.DataModel.Source;
import id.cuxxie.newslistapps.Presenter.Contract.NewsActivityPresenterContract;
import id.cuxxie.newslistapps.Presenter.NewsActivityPresenter;
import id.cuxxie.newslistapps.R;
import id.cuxxie.newslistapps.Presenter.Adapter.NewsAdapter;
import id.cuxxie.newslistapps.Presenter.Adapter.SourcesAdapter;

public class NewsActivity extends AppCompatActivity implements NewsActivityPresenterContract, NewsAdapter.NewsAdapterOnItemClickListener {
    private static String articlesStateKey = "articles";
    @BindView(R.id.news_activity_recycle_view) RecyclerView recyclerView;
    @BindView(R.id.news_search) EditText search;
    NewsAdapter adapter;
    NewsActivityPresenter presenter;
    Source source;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        presenter = new NewsActivityPresenter(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getIntent().hasExtra("source"))
            source = getIntent().getParcelableExtra("source");

        ArrayList<Article> articles = new ArrayList<>();
        if(savedInstanceState!=null && savedInstanceState.containsKey(articlesStateKey))
            articles = savedInstanceState.getParcelableArrayList(articlesStateKey);
        else
            presenter.loadArticleData(source.getId());

        adapter = new NewsAdapter(articles,getBaseContext(),this,"");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchForText(s.toString());
            }
        });
    }

    @Override
    public void updateAdapterWithNewData(ArrayList<Article> articles) {
        adapter.setFilterText("");
        clearSearch();
        adapter.setArticles(articles);
        notifyUpdateData();
    }

    @Override
    public void onItemClicked(Article article) {
        Intent intent = new Intent(getBaseContext(),ReadNewsActivity.class);
        intent.putExtra("article",article);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(articlesStateKey,adapter.getArticles());
        super.onSaveInstanceState(outState);
    }

    public void searchForText(String searchVal)
    {
        adapter.searchText(searchVal);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFinishedSearch() {
        notifyUpdateData();
    }

    public void notifyUpdateData()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void clearSearch()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                search.setText("");
            }
        });
    }
}
