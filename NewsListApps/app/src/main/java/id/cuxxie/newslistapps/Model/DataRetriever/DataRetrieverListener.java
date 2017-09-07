package id.cuxxie.newslistapps.Model.DataRetriever;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import id.cuxxie.newslistapps.Model.DataModel.ModelWrapper;
import id.cuxxie.newslistapps.Model.Utility.JSONConverterUtility;
import id.cuxxie.newslistapps.Presenter.ClientListener.DataRetrieverClientListener;

/**
 * Created by hendr on 9/6/2017.
 */

public class DataRetrieverListener implements DataRetrieverInterface {
    public int callerHashCode;
    private DataRetrieverClientListener clientListener;
    public DataRetrieverListener(int hashCode, DataRetrieverClientListener clientListener) {
        this.callerHashCode = hashCode;
        this.clientListener = clientListener;
    }

    @Override
    public void onDataRetrieveFailed(Exception ex) {
        clientListener.onFailed();
    }

    @Override
    public void onDataRetrieveSucceed(String rawResult, ModelWrapper.ModelType modelType) {
        JSONObject json = JSONConverterUtility.convertStringToJsonObject(rawResult);
        JSONArray jsonArray;
        if(modelType == ModelWrapper.ModelType.ARTICLE)
        {
            jsonArray = JSONConverterUtility.getJsonArrayWithObject(json,"articles");
        }
        else
        {
            jsonArray = JSONConverterUtility.getJsonArrayWithObject(json,"sources");
        }
        if(clientListener != null)
            clientListener.onSuccess(JSONConverterUtility.convertJsonArrayToModelWrapper(jsonArray,modelType));
    }

    @Override
    public int getCallerHash() {
        return callerHashCode;
    }

    public void cancelAllCall()
    {
        DataRetriever.getInstance().cancelAPICall(callerHashCode);
        clientListener = null;
    }
}
