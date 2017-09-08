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
        if (getIntent().hasExtra("article"))
            article = getIntent().getParcelableExtra("article");

        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.read_news_title);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        activity = this;
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
               // super.onReceivedError(view, request, error);
                //Toast.makeText(activity, error.getDescription(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
                //super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });

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
