package id.cuxxie.newslistapps.Presenter.Contract;

import java.util.ArrayList;

import id.cuxxie.newslistapps.Model.DataModel.Source;

/**
 * Created by hendr on 9/6/2017.
 */

public interface MainActivityPresenterClientContract {
    public void updateAdapterWithNewData(ArrayList<Source> sources);
}
