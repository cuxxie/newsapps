package id.cuxxie.newslistapps;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import id.cuxxie.newslistapps.Model.DataModel.Article;
import id.cuxxie.newslistapps.Model.DataModel.ModelWrapper;
import id.cuxxie.newslistapps.Model.DataModel.Source;
import id.cuxxie.newslistapps.Model.DataRetriever.DataRetriever;
import id.cuxxie.newslistapps.Model.DataRetriever.DataRetrieverListener;
import id.cuxxie.newslistapps.Presenter.ClientListener.DataRetrieverClientListener;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class APICallTest implements DataRetrieverClientListener {
    private CountDownLatch signal = null;
    DataRetrieverListener drl;
    boolean testingSource = true;
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("id.cuxxie.newslistapps", appContext.getPackageName());
    }

    @Test
    public void getSourcesTest()
    {
        testingSource = true;
        signal = new CountDownLatch(1);
        Context appContext = InstrumentationRegistry.getTargetContext();
        drl = new DataRetrieverListener(this.hashCode(),this);
        DataRetriever.getInstance().getAllSources("en",drl);
        try {
            signal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getArticlesTest()
    {
        testingSource = false;
        signal = new CountDownLatch(1);
        Context appContext = InstrumentationRegistry.getTargetContext();
        drl = new DataRetrieverListener(this.hashCode(),this);
        DataRetriever.getInstance().getAllArticles("the-next-web",drl);
        try {
            signal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccess(ArrayList<ModelWrapper> model) {
        assertTrue(model.size() > 0);
        if(testingSource) {
            assertNotNull(model.get(0).getSource());
            assertTrue(model.get(0).getSource() instanceof Source);
        }
        else{
            assertNotNull(model.get(0).getArticle());
            assertTrue(model.get(0).getArticle() instanceof Article);
        }
        signal.countDown();
    }

    @Override
    public void onFailed() {
        signal.countDown();
    }
}
