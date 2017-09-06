package id.cuxxie.newslistapps.Presenter;

import java.util.ArrayList;

import id.cuxxie.newslistapps.Model.DataModel.ModelWrapper;
import id.cuxxie.newslistapps.Presenter.ClientListener.DataRetrieverClientListener;
import id.cuxxie.newslistapps.Presenter.Contract.MainActivityPresenterContract;

/**
 * Created by hendr on 9/6/2017.
 */

public class MainActivityPresenter implements DataRetrieverClientListener {
    MainActivityPresenterContract activity;
    public MainActivityPresenter(MainActivityPresenterContract activity) {
        this.activity = activity;
    }

    public void loadSourcesData(String languages, String category)
    {

    }

    @Override
    public void onSuccess(ArrayList<ModelWrapper> model) {

    }

    @Override
    public void onFailed() {

    }
}
