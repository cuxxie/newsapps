package id.cuxxie.newslistapps.View.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import id.cuxxie.newslistapps.Presenter.Contract.NewsActivityPresenterContract;
import id.cuxxie.newslistapps.R;

public class NewsActivity extends AppCompatActivity implements NewsActivityPresenterContract {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
    }
}
