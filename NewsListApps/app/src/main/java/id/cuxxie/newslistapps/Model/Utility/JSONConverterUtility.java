package id.cuxxie.newslistapps.Model.Utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import id.cuxxie.newslistapps.Model.DataModel.Article;
import id.cuxxie.newslistapps.Model.DataModel.ModelWrapper;
import id.cuxxie.newslistapps.Model.DataModel.Source;

/**
 * Created by hendr on 9/6/2017.
 */

public class JSONConverterUtility {
    public static JSONObject convertStringToJsonObject(String jsonStr){
        try {
            return new JSONObject(jsonStr);
        }
        catch (JSONException ex)
        {
            return new JSONObject();
        }
    }

    public static JSONArray getJsonArrayWithObject(JSONObject json, String key){
        try {
            return json.getJSONArray(key);
        }
        catch (JSONException ex)
        {
            return new JSONArray();
        }
    }

    public static ArrayList<ModelWrapper> convertJsonArrayToModelWrapper(JSONArray jsonArray, ModelWrapper.ModelType modelType)
    {
        ArrayList<ModelWrapper> models = new ArrayList<>();
        if(modelType == ModelWrapper.ModelType.ARTICLE)
        {
            ArrayList<Article> articles = convertJsonArrayToArticles(jsonArray);
            for(Article article: articles)
                models.add(new ModelWrapper(article));
        }
        else
        {
            ArrayList<Source> sources = convertJsonArrayToSources(jsonArray);
            for(Source source: sources)
                models.add(new ModelWrapper(source));
        }
        return models;
    }

    private static ArrayList<Article> convertJsonArrayToArticles(JSONArray jsonArray)
    {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Article> articleArrayList = new ArrayList<>();
        try {
            articleArrayList = mapper.readValue(jsonArray.toString(),new TypeReference<ArrayList<Article>>(){});
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return articleArrayList;
    }

    private static ArrayList<Source> convertJsonArrayToSources(JSONArray jsonArray)
    {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Source> sourceArrayList = new ArrayList<>();
        try {
            sourceArrayList = mapper.readValue(jsonArray.toString(),new TypeReference<ArrayList<Source>>(){});
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return sourceArrayList;
    }
}
