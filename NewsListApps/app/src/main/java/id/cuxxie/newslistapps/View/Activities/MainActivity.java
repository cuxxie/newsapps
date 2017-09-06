package id.cuxxie.newslistapps.View.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import id.cuxxie.newslistapps.Presenter.Contract.MainActivityPresenterContract;
import id.cuxxie.newslistapps.R;

public class MainActivity extends AppCompatActivity implements MainActivityPresenterContract {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
