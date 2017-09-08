package id.cuxxie.newslistapps.Presenter;
import java.util.ArrayList;

import id.cuxxie.newslistapps.Model.DataModel.Article;
import id.cuxxie.newslistapps.Model.DataModel.ModelWrapper;
import id.cuxxie.newslistapps.Model.DataRetriever.DataRetriever;
import id.cuxxie.newslistapps.Model.DataRetriever.DataRetrieverListener;
import id.cuxxie.newslistapps.Model.Utility.ModelConverterUtility;
import id.cuxxie.newslistapps.Presenter.ClientListener.DataRetrieverClientListener;
import id.cuxxie.newslistapps.Presenter.Contract.NewsActivityPresenterContract;

/**
 * Created by hendr on 9/6/2017.
 */

public class NewsActivityPresenter implements DataRetrieverClientListener {
    NewsActivityPresenterContract activity;
    DataRetrieverListener drl;
    public NewsActivityPresenter(NewsActivityPresenterContract activity) {
        this.activity = activity;
    }

    public void loadArticleData(String source)
    {
        drl = new DataRetrieverListener(this.hashCode(), this);
        DataRetriever.getInstance().getAllArticles(source, drl);
    }

    @Override
    public void onSuccess(ArrayList<ModelWrapper> model) {
        ArrayList<Article> articles = ModelConverterUtility.convertModelWrapperListToArticle(model);
        activity.updateAdapterWithNewData(articles);
    }

    @Override
    public void onFailed() {

    }
}
