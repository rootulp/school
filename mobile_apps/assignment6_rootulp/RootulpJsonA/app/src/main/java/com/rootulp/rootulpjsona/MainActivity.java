package com.rootulp.rootulpjsona;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private String url = "http://www.cs.bc.edu/~signoril/american-art.json";
    ListView listview;
    ArrayList<String> vals;
    ArrayList<artist> artistList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listview);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> l, View v, int position, long id) {
                CheckedTextView item = (CheckedTextView) v;
            }
        });
        new ReadJsonTask().execute(url);

    }


    class ReadJsonTask extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String... arg) {
            String str = retrieveSiteContent(arg[0]);
            return str;
        }

        protected void onPostExecute(String result) {
            try {
                vals = new ArrayList<String>();
                artistList = new ArrayList<artist>();
                JSONObject jsonobj = new JSONObject(result);
                JSONArray jsonarray = new JSONArray();
                jsonarray = jsonobj.getJSONArray("paintings");

                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject tempObj = jsonarray.getJSONObject(i);

                    String imageURL = tempObj.getString("imageURL");
                    String title = tempObj.getString("title");
                    String name = tempObj.getString("name");
                    String nationality = tempObj.getString("nationality");
                    String paintingdate = tempObj.getString("date");
                    String paintingtype = tempObj.getString("type");
                    String medium = tempObj.getString("medium");
                    String dimensions = tempObj.getString("dimensions");
                    String classification = tempObj.getString("classification");
                    String provenance = tempObj.getString("provenance");
                    String description = tempObj.getString("description");
                    artist tempArtist = new artist();
                    tempArtist.setImageURL(imageURL);
                    tempArtist.setTitle(title);
                    tempArtist.setName(name);
                    tempArtist.setNationality(nationality);
                    tempArtist.setPaintingdate(paintingdate);
                    tempArtist.setPaintingtype(paintingtype);
                    tempArtist.setMedium(medium);
                    tempArtist.setDimensions(dimensions);
                    tempArtist.setClassification(classification);
                    tempArtist.setProvenance(provenance);
                    tempArtist.setDescription(description);


                    artistList.add(tempArtist);
                    vals.add(title + " by " + name);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (adapter == null) {
                adapter = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_multiple_choice, vals);
                listview.setAdapter(adapter);
                listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            } else {
                adapter.notifyDataSetChanged();
            }


        }

        public String retrieveSiteContent(String siteUrl) {
            StringBuilder builder = new StringBuilder();
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(siteUrl);
            try {
                HttpResponse response = client.execute(httpGet);
                StatusLine statusLine = response.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                } else {
                    Log.e("KB", "Status code: " + statusCode);
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return builder.toString();
        }
    }

    public void goToPic() {
        SparseBooleanArray checked = listview.getCheckedItemPositions();
        int size = checked.size();
        for (int i = 0; i < size; i++) {
            int key = checked.keyAt(i);
            boolean value = checked.get(key);
            if (value) {
                Intent intent = new Intent(MainActivity.this, picPage.class);
                artist selected = artistList.get(key);
                intent.putExtra("selected", selected);
                startActivity(intent);
                break;
            }
        }

    }





    public void goToMap(){
        ArrayList<Integer> checked1 = new ArrayList<Integer>();
        SparseBooleanArray checked = listview.getCheckedItemPositions();
        int size = checked.size(); // number of name-value pairs in the array
        for (int i = 0; i < size; i++) {
            int key = checked.keyAt(i);
            boolean value = checked.get(key);
            if (value) {
                checked1.add(key);
            }
        }
        // prepare a Bundle and add the data pieces to be sent
        Bundle myData = new Bundle();
        myData.putIntegerArrayList("myIntArray1", checked1);
        myData.putSerializable("artists", artistList);
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtras(myData);
        startActivity(intent);
    }

    public void goToInfo() {
        SparseBooleanArray checked = listview.getCheckedItemPositions();
        int size = checked.size();
        for (int i = 0; i < size; i++) {
            int key = checked.keyAt(i);
            boolean value = checked.get(key);
            if (value) {
                Intent intent = new Intent(MainActivity.this, infoPage.class);
                artist selected = artistList.get(key);
                intent.putExtra("selected", selected);
                startActivity(intent);
                break;
            }
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.menu_info:
                goToInfo();
                break;
            case R.id.menu_pic:
                goToPic();
                break;
            case R.id.menu_map:
                goToMap();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}