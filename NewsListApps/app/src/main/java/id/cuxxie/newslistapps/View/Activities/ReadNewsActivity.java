package id.cuxxie.newslistapps.View.Activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.cuxxie.newslistapps.Model.DataModel.Article;
import id.cuxxie.newslistapps.Model.Utility.Key;
import id.cuxxie.newslistapps.Presenter.Contract.ReadNewsActivityPresenterContract;
import id.cuxxie.newslistapps.R;

public class ReadNewsActivity extends AppCompatActivity implements ReadNewsActivityPresenterContract {
    Activity activity;
    Article article;
    @BindView(R.id.webview) WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_news);
        ButterKnife.bind(this);
        if (getIntent().hasExtra(Key.ARTICLE.toString()))
            article = getIntent().getParcelableExtra(Key.ARTICLE.toString());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.read_news_title);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        activity = this;
        webView.loadUrl(article.getUrl());
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
}
