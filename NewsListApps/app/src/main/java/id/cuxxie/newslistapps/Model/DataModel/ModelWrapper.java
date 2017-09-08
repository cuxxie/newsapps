package id.cuxxie.newslistapps.Model.DataModel;

import java.util.ArrayList;

/**
 * Created by hendr on 9/6/2017.
 */

public class ModelWrapper {
    public enum ModelType{
        ARTICLE,
        SOURCE
    }
    private Article article;
    private Source source;
    private ModelType type;

    public ModelWrapper(Article article) {
        this.article = article;
        this.type = ModelType.ARTICLE;
    }
    public ModelWrapper(Source source) {
        this.source = source;
        this.type = ModelType.SOURCE;
    }
    public Article getArticle()
    {
        if(this.type == ModelType.ARTICLE)
            return article;
        else
            return null;
    }

    public Source getSource()
    {
        if(this.type == ModelType.SOURCE)
            return source;
        else
            return null;
    }
}
