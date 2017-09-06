package id.cuxxie.newslistapps.Presenter;
import java.util.ArrayList;

import id.cuxxie.newslistapps.Model.DataModel.ModelWrapper;
import id.cuxxie.newslistapps.Presenter.ClientListener.DataRetrieverClientListener;
import id.cuxxie.newslistapps.Presenter.Contract.NewsActivityPresenterContract;

/**
 * Created by hendr on 9/6/2017.
 */

public class NewsActivityPresenter implements DataRetrieverClientListener {
    NewsActivityPresenterContract activity;

    public NewsActivityPresenter(NewsActivityPresenterContract activity) {
        this.activity = activity;
    }

    public void loadArticleData(String source, String sortBy)
    {

    }

    @Override
    public void onSuccess(ArrayList<ModelWrapper> model) {

    }

    @Override
    public void onFailed() {

    }
}
