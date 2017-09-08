package id.cuxxie.newslistapps.Model.DataRetriever;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import id.cuxxie.newslistapps.Model.DataModel.ModelWrapper;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hendr on 9/6/2017.
 */

public class DataRetriever implements Callback  {
    private final OkHttpClient okHttpClient;
    private static DataRetriever self;
    private static final String BASE_URL = "newsapi.org";
    private static final String ARTICLE_PATH = "articles";
    private static final String SOURCE_PATH = "sources";
    private static final String API_KEY = "7c0501dff5ff4ac4955540c4e9271fb2";
    private DataRetrieverInterface callerHandler;
    private DataRetriever() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        System.out.println("url: " + chain.request().url());
                        return chain.proceed(chain.request());
                    }
                })
                .build();
    }

    public static DataRetriever getInstance()
    {
        if(self == null){
            self = new DataRetriever();
        }
        return self;
    }

    private void callURLGet(HttpUrl url,DataRetrieverInterface dataRetrieverHandler )
    {
        this.callerHandler = dataRetrieverHandler;
        Log.v("URL Call",url.toString());
        Request request = new Request.Builder().cacheControl(new CacheControl.Builder()
                .maxStale(2, TimeUnit.HOURS)
                .build())
                .url(url).tag(dataRetrieverHandler.getCallerHash())
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(this);
    }

    public String cleanCategoryToMatchServerRequirement(String category)
    {
        if(category != null){
            if(!category.equals("All"))
                return category.replace(' ','-');
        }
        return null;
    }

    public void getAllSources(String category, DataRetrieverInterface dataRetrieverHandler)
    {
        category = cleanCategoryToMatchServerRequirement(category);
        HttpUrl.Builder builder = new HttpUrl.Builder().scheme("https").host(BASE_URL)
                .addEncodedPathSegment("v1").addEncodedPathSegment(SOURCE_PATH);
        if(category != null)
            builder.addQueryParameter("category",category);
        HttpUrl url = builder.build();
        callURLGet(url,dataRetrieverHandler);
    }

    public void getAllArticles(String sourceId, DataRetrieverInterface dataRetrieverHandler)
    {
        HttpUrl url = new HttpUrl.Builder().scheme("https").host(BASE_URL).addEncodedPathSegment("v1")
                .addEncodedPathSegment(ARTICLE_PATH)
                .addQueryParameter("source",sourceId)
                .addQueryParameter("apiKey",API_KEY)
                .build();
        callURLGet(url,dataRetrieverHandler);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        if(!e.getMessage().toLowerCase().contains("canceled") && !e.getMessage().toLowerCase().contains("socket closed")) {
            callerHandler.onDataRetrieveFailed(e);
        }
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
       if(call.request().url().encodedPathSegments().contains(ARTICLE_PATH))
           callerHandler.onDataRetrieveSucceed(response.body().string(), ModelWrapper.ModelType.ARTICLE);
       else
           callerHandler.onDataRetrieveSucceed(response.body().string(), ModelWrapper.ModelType.SOURCE);
    }

    public void cancelAPICall(int tag)
    {
        for(Call call : okHttpClient.dispatcher().queuedCalls()) {
            if(call.request().tag().equals(tag))
                call.cancel();
        }
        for(Call call : okHttpClient.dispatcher().runningCalls()) {
            if(call.request().tag().equals(tag))
                call.cancel();
        }
    }
}
