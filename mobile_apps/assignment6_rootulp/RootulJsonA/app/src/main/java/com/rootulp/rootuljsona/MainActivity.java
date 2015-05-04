package com.rootulp.rootuljsona;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import java.io.Serializable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.jar.Attributes;


public class MainActivity extends ActionBarActivity {



    private String url = "http://www.cs.bc.edu/~signoril/people.json";
    TextView tv;
    ArrayList<Integer> selectedRows = new ArrayList<Integer>();
    ListView listview;
    ProgressBar progressBar;
    ArrayList<PersonInfo> personInfoList;
    ArrayList<String> values;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listview = (ListView) findViewById(R.id.listView);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> l, View v, int position, long id) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                PersonInfo selectedperson = personInfoList.get(position);
                intent.putExtra("person", selectedperson);
                startActivity(intent);

            }
        });
       new ReadJsonTask().execute(url);

    }

    class ReadJsonTask extends AsyncTask<String, Integer, String> {


        protected void onPreExecute() {
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... arg) {
            String stringValue = retrieveSiteContent(arg[0]);
            return stringValue;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                values = new ArrayList<String>();
                personInfoList = new ArrayList<PersonInfo>();
                JSONObject jsonObj = new JSONObject(result);
                JSONArray jsonA = new JSONArray();

                jsonA = jsonObj.getJSONArray("caricatures");

                for (int i = 0; i < jsonA.length(); i++) {
                    JSONObject jsonobject = jsonA.getJSONObject(i);
                    String name = jsonobject.getString("person");
                    String link = jsonobject.getString("img_link");
                    String occupation = jsonobject.getString("occupation");
                    Boolean female = jsonobject.getBoolean("isGenderFemale");
                    Integer age = jsonobject.getInt("age");
                    PersonInfo tempPerson = new PersonInfo();
                    tempPerson.setPerson(name);
                    tempPerson.setOccupation(occupation);
                    tempPerson.setlink(link);
                    tempPerson.setIsGenderFemale(female);
                    tempPerson.setAge(age);
                    personInfoList.add(tempPerson);
                    values.add(name + " - " + occupation);

                }

                progressBar.setVisibility(View.GONE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (adapter == null) {
                adapter = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1, values);
                listview.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
        }

    }

    public String retrieveSiteContent(String siteUrl){
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
                Log.e("SIG", "Status code: " + statusCode);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}