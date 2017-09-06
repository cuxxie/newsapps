package id.cuxxie.newslistapps.Presenter;

import id.cuxxie.newslistapps.Presenter.Contract.ReadNewsActivityPresenterContract;

/**
 * Created by hendr on 9/6/2017.
 */

public class ReadNewsActivityPresenter {
    ReadNewsActivityPresenterContract activity;

    public ReadNewsActivityPresenter(ReadNewsActivityPresenterContract activity) {
        this.activity = activity;
    }
}
