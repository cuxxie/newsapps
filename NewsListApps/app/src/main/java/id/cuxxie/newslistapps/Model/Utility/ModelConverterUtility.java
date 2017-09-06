package id.cuxxie.newslistapps.Model.Utility;

import java.util.ArrayList;

import id.cuxxie.newslistapps.Model.DataModel.Article;
import id.cuxxie.newslistapps.Model.DataModel.ModelWrapper;
import id.cuxxie.newslistapps.Model.DataModel.Source;

import static java.lang.System.in;

/**
 * Created by hendr on 9/6/2017.
 */

public class ModelConverterUtility {
    public static ArrayList<Article> convertModelWrapperListToArticle(ArrayList<ModelWrapper> modelWrappers)
    {
        ArrayList<Article> articles = new ArrayList<>();
        for(ModelWrapper item: modelWrappers)
        {
            Article article = item.getArticle();
            if(article != null)
                articles.add(article);
        }
        return articles;
    }

    public static ArrayList<Source> convertModelWrapperListToSources(ArrayList<ModelWrapper> modelWrappers)
    {
        ArrayList<Source> sources = new ArrayList<>();
        for(ModelWrapper item: modelWrappers)
        {
            Source source = item.getSource();
            if(source != null)
                sources.add(source);
        }
        return sources;
    }
}
