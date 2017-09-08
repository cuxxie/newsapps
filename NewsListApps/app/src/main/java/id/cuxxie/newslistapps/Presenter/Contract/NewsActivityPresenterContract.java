package id.cuxxie.newslistapps.Presenter.Contract;

import java.util.ArrayList;

import id.cuxxie.newslistapps.Model.DataModel.Article;

/**
 * Created by hendr on 9/6/2017.
 */

public interface NewsActivityPresenterContract {
    void updateAdapterWithNewData(ArrayList<Article> articles);
}
