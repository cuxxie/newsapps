package id.cuxxie.newslistapps.View.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.cuxxie.newslistapps.Model.DataModel.Source;
import id.cuxxie.newslistapps.Presenter.Contract.MainActivityPresenterClientContract;
import id.cuxxie.newslistapps.Presenter.MainActivityPresenter;
import id.cuxxie.newslistapps.R;
import id.cuxxie.newslistapps.Presenter.Adapter.SourcesAdapter;

public class MainActivity extends AppCompatActivity implements MainActivityPresenterClientContract, SourcesAdapter.SourcesAdapterOnItemClickListener{

    private static String categoryStateKey = "category";
    private static String sourcesStateKey = "sources";
    @BindView(R.id.main_activity_category) Spinner category;
    @BindView(R.id.main_activity_recycle_view) RecyclerView recyclerView;
    @BindView(R.id.progress_container) LinearLayout progressContainer;
    SourcesAdapter adapter;
    MainActivityPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainActivityPresenter(this);
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = getResources().getStringArray(R.array.category_arrays)[position];
                spinnerChangeValue(selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayList<Source> sources = new ArrayList<>();

        if(savedInstanceState!= null && savedInstanceState.containsKey(categoryStateKey)) {
            presenter.setSelectedCategory(savedInstanceState.getString(categoryStateKey));
        }

        if(savedInstanceState != null && savedInstanceState.containsKey(sourcesStateKey))
            sources = savedInstanceState.getParcelableArrayList(sourcesStateKey);
        else {
            presenter.loadSourcesData(null);
            showLoading();
        }
        adapter = new SourcesAdapter(sources,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(Source source) {
        Intent intent = new Intent(getBaseContext(),NewsActivity.class);
        intent.putExtra("source",source);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(sourcesStateKey,adapter.getSources());
        outState.putString(categoryStateKey,presenter.getSelectedCategory());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void updateAdapterWithNewData(ArrayList<Source> sources) {
        adapter.setSourcesWithNewData(sources);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void spinnerChangeValue(String value)
    {
        presenter.loadSourcesData(value);
    }



    @Override
    public void showLoading()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressContainer.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void hideLoading()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressContainer.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onStop() {
        presenter.cancelAllCall();
        super.onStop();
    }
}
