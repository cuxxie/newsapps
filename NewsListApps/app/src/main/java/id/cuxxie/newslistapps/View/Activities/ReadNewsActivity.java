package id.cuxxie.newslistapps.View.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import id.cuxxie.newslistapps.Presenter.Contract.ReadNewsActivityPresenterContract;
import id.cuxxie.newslistapps.R;

public class ReadNewsActivity extends AppCompatActivity implements ReadNewsActivityPresenterContract {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_news);
    }
}
