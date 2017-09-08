package id.cuxxie.newslistapps;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import id.cuxxie.newslistapps.Model.DataModel.Article;
import id.cuxxie.newslistapps.Model.DataModel.ModelWrapper;
import id.cuxxie.newslistapps.Model.DataModel.Source;
import id.cuxxie.newslistapps.Model.Utility.JSONConverterUtility;
import id.cuxxie.newslistapps.Model.Utility.ModelConverterUtility;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ConverterUnitTest {
    @Test
    public void testJsonToModelWrapperToArticleConverter() throws Exception {
        String stringJson = loadJsonFileAsString("ArticleJson.json");
        JSONObject jsonObject = JSONConverterUtility.convertStringToJsonObject(stringJson);
        JSONArray jsonArray = JSONConverterUtility.getJsonArrayWithObject(jsonObject,"articles");
        Assert.assertNotNull(jsonArray);
        Assert.assertTrue(jsonArray.length() > 0);

        ArrayList<ModelWrapper> models = JSONConverterUtility.convertJsonArrayToModelWrapper(jsonArray, ModelWrapper.ModelType.ARTICLE);
        Assert.assertTrue(models.size() > 0);

        ArrayList<Article> sources = ModelConverterUtility.convertModelWrapperListToArticle(models);
        Assert.assertTrue(sources.size() > 0);
        Assert.assertEquals("Stephen King's IT Scores Huge Opening Night - IGN",sources.get(0).getTitle());
    }

    @Test
    public void testJsonToModelWrapperToSourcesConverter() throws Exception {
        String stringJson = loadJsonFileAsString("SourcesJson.json");
        JSONObject jsonObject = JSONConverterUtility.convertStringToJsonObject(stringJson);
        JSONArray jsonArray = JSONConverterUtility.getJsonArrayWithObject(jsonObject,"sources");
        Assert.assertNotNull(jsonArray);
        Assert.assertTrue(jsonArray.length() > 0);

        ArrayList<ModelWrapper> models = JSONConverterUtility.convertJsonArrayToModelWrapper(jsonArray, ModelWrapper.ModelType.SOURCE);
        Assert.assertTrue(models.size() > 0);

        ArrayList<Source> sources = ModelConverterUtility.convertModelWrapperListToSources(models);
        Assert.assertTrue(sources.size() > 0);
        Assert.assertEquals("ign",sources.get(0).getId());
    }

    @Test
    public void testStringToJsonConverter() throws Exception {
        String stringJson = loadJsonFileAsString("SourcesJson.json");
        JSONObject jsonObject = JSONConverterUtility.convertStringToJsonObject(stringJson);
        Assert.assertNotNull(jsonObject);
        Assert.assertTrue(jsonObject.length() > 0);
    }


    public String loadJsonFileAsString(String filename)
    {
        InputStream inputStream = getClass().getResourceAsStream(filename);

        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        try {
            Reader in = new InputStreamReader(inputStream, "UTF-8");
            for (; ; ) {
                int rsz = in.read(buffer, 0, buffer.length);
                if (rsz < 0)
                    break;
                out.append(buffer, 0, rsz);
            }
            return out.toString();
        }
        catch (UnsupportedEncodingException ex)
        {
            ex.printStackTrace();
            return "";
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            return "";
        }
    }
}