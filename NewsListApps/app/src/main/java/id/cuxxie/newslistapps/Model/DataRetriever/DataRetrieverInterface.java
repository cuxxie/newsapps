package id.cuxxie.newslistapps.Model.DataRetriever;

import android.content.Context;

import id.cuxxie.newslistapps.Model.DataModel.ModelWrapper;

/**
 * Created by hendr on 9/6/2017.
 */

public interface DataRetrieverInterface {
    void onDataRetrieveFailed(Exception ex);
    void onDataRetrieveSucceed(String rawResult, ModelWrapper.ModelType modelType);
    int getCallerHash();
}
