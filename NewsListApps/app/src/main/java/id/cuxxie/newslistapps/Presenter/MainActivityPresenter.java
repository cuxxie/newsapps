package id.cuxxie.newslistapps.Presenter;

import java.util.ArrayList;

import id.cuxxie.newslistapps.Model.DataModel.ModelWrapper;
import id.cuxxie.newslistapps.Model.DataModel.Source;
import id.cuxxie.newslistapps.Model.DataRetriever.DataRetriever;
import id.cuxxie.newslistapps.Model.DataRetriever.DataRetrieverListener;
import id.cuxxie.newslistapps.Model.Utility.ModelConverterUtility;
import id.cuxxie.newslistapps.Presenter.ClientListener.DataRetrieverClientListener;
import id.cuxxie.newslistapps.Presenter.Contract.MainActivityPresenterClientContract;

/**
 * Created by hendr on 9/6/2017.
 */

public class MainActivityPresenter implements DataRetrieverClientListener {
    MainActivityPresenterClientContract activity;
    String selectedCategory;
    DataRetrieverListener drl;
    public MainActivityPresenter(MainActivityPresenterClientContract activity) {
        this.activity = activity;
    }

    public void loadSourcesData(String category)
    {
        if(selectedCategory == null || !selectedCategory.equals(category)) {
            activity.showLoading();
            selectedCategory = category;
            cancelAllCall();
            drl = new DataRetrieverListener(this.hashCode(), this);
            DataRetriever.getInstance().getAllSources(selectedCategory, drl);
        }
    }

    public void setSelectedCategory(String category) {
        this.selectedCategory = category;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

    @Override
    public void onSuccess(ArrayList<ModelWrapper> model) {
        ArrayList<Source> sources = ModelConverterUtility.convertModelWrapperListToSources(model);
        activity.hideLoading();
        activity.updateAdapterWithNewData(sources);
    }

    @Override
    public void onFailed() {
        activity.hideLoading();
    }

    public void cancelAllCall()
    {
        if(drl == null)
            return;
        drl.cancelAllCall();
        drl = null;
    }
}
