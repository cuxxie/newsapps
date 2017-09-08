package id.cuxxie.newslistapps.Presenter.ClientListener;

import android.os.Parcelable;

import java.util.ArrayList;

import id.cuxxie.newslistapps.Model.DataModel.Article;
import id.cuxxie.newslistapps.Model.DataModel.ModelWrapper;
import id.cuxxie.newslistapps.Model.DataModel.Source;

/**
 * Created by hendr on 9/6/2017.
 */

public interface DataRetrieverClientListener {
    void onSuccess(ArrayList<ModelWrapper> model);
    void onFailed();
}
