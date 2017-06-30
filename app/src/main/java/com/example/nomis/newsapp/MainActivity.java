package com.example.nomis.newsapp;

import android.os.AsyncTask;
//import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import com.example.nomis.newsapp.utilities.NetworkUtils;
import com.example.nomis.newsapp.model.NewsItem;
import com.example.nomis.newsapp.utilities.NewsAdapter;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.nomis.newsapp.utilities.NetworkUtils.makeUrl;
//import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "mainactivity";
    private TextView search;
    private RecyclerView rv;

    private void makeNewSearch() {
        //String githubQuery = mSearchBoxEditText.getText().toString();
        URL newUrl = NetworkUtils.makeUrl("the-next-web");
        //search.setText(newUrl.toString());
        // COMPLETED (4) Create a new GithubQueryTask and call its execute method, passing in the url to query
        new NewsAppTask().execute(newUrl);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = (TextView) findViewById(R.id.searchResults);
        rv = (RecyclerView)findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        new NewsAppTask().execute();
    }


    class NewsAppTask extends AsyncTask<URL, Void, ArrayList<NewsItem>>{

        @Override
        protected ArrayList<NewsItem> doInBackground(URL... params){
            ArrayList<NewsItem> results = null;
            URL url = NetworkUtils.makeUrl("the-next-web");
            Log.d(TAG, "url: " + url.toString());
            try{
                String json = NetworkUtils.getResponseFromHttpUrl(url);
                results = NetworkUtils.parseJson(json);
            }catch (IOException e){
                e.printStackTrace();
            }catch (JSONException e){
                e.printStackTrace();
            }
            return results;
        }

        @Override
        protected void onPostExecute(final ArrayList<NewsItem> data){
            super.onPostExecute(data);
            if(data != null){
                NewsAdapter adapter = new NewsAdapter(data);
                rv.setAdapter(adapter);
            }
        }
/*    class NewsAppTask extends AsyncTask<URL, Void, String>{

            @Override
            protected String doInBackground(URL... params){
                String results = null;
                URL url = NetworkUtils.makeUrl("the-next-web");
                Log.d(TAG, "url: " + url.toString());
                try{
                    results = NetworkUtils.getResponseFromHttpUrl(url);
                }catch (IOException e){
                    e.printStackTrace();
                }
                return results;
            }
    @Override
      protected void onPostExecute(String results){
            //super.onPostExecute(results);
            if(results != null && !results.equals("")){
                search.setText(results);
                //
            }
        }*/

    }
}
