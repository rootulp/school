package com.rootulp.currencyconverter;

        import android.content.Context;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.os.AsyncTask;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Spinner;
        import android.widget.ArrayAdapter;
        import android.widget.Toast;
        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.URL;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;




public class MainActivity extends ActionBarActivity {
    String url;
    private Pattern tPattern = null;
    private String tRE = "\\d+\\.\\d+";
    private String [] SpinnerTo;
    private String [] SpinnerFrom;
    String CurrencyFrom="", CurrencyTo="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.SpinnerTo = new String[] {
                "EUR", "USD", "JPY", "GBP", "CAD", "AUD"};
        
        this.SpinnerFrom = new String[] {
                "USD", "EUR", "JPY", "GBP", "CAD", "AUD"};

        Spinner s = (Spinner) findViewById(R.id.spinnerto);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, SpinnerTo);
        s.setAdapter(adapter);
        Spinner t = (Spinner) findViewById(R.id.spinnerfrom);
        ArrayAdapter<String> second_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, SpinnerFrom);
        t.setAdapter(second_adapter);

        url = "http://www.gocurrency.com/v2/dorate.php?inV=1&from=USD&to=EUR&Calculate=Convert";
    }


    public void onButtonClick(View v){
        CurrencyFrom = ((Spinner)findViewById(R.id.spinnerfrom)).getSelectedItem().toString();
        CurrencyTo = ((Spinner)findViewById(R.id.spinnerto)).getSelectedItem().toString();
        if (CurrencyTo==CurrencyFrom){
            Toast.makeText(getApplicationContext(), "You picked the same two currencies! Try again!" , Toast.LENGTH_LONG).show();
        }
        url = "http://www.gocurrency.com/v2/dorate.php?inV=1&from="+CurrencyFrom+"&to="+CurrencyTo+"&Calculate=Convert";
        doScrape scrapeTask = new doScrape();
        scrapeTask.execute(url, CurrencyFrom, CurrencyTo);  //this kicks off background task
    }


    public void showAnswer(String newP) {
        TextView result = (TextView) findViewById(R.id.result);
        result.setText("1 "+CurrencyTo+" = " +newP+" "+ CurrencyFrom);
    }

    private class doScrape extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                url = "http://www.gocurrency.com/v2/dorate.php?inV=1&from=USD&to=EUR&Calculate=Convert";
            } else {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println(e);
                }
                onStop();
            }

        }


        @Override
        protected String doInBackground(String... arguments) {
            String urlIn = arguments[0];
            //String s = arguments[1];
            String s="";
            BufferedReader in = null;
            if (tPattern == null)
                tPattern = Pattern.compile(tRE); 
            try {
                URL url = new URL(urlIn);
                in = new BufferedReader(
                        new InputStreamReader(
                                url.openStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.contains( CurrencyFrom + " to " + CurrencyTo + " Exchange Rate") ) {//find city
//                        inputLine = in.readLine(); //originally had this but
//                                                      realized it actually skipped a line in the debugger
                        Matcher m = tPattern.matcher(inputLine);
                        if (m.find()) {
                            s = m.group(0);
                        }
                        return s;
                    }
                }
                return getString(R.string.never_found);  // never found the pattern
            } catch (IOException e) {
                Log.e("ScrapeTemperatures", "Unable to open url: " + url);
                return getString(R.string.never_found);
            } catch (Exception e) {
                Log.e("ScrapeTemperatures", e.toString());
                return getString(R.string.never_found);
            } finally {
                if (in != null)
                    try {
                        in.close();
                    } catch (IOException e) {
                    }
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            showAnswer(result);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
//try {
// thread to sleep for 1000 milliseconds
//    Thread.sleep(100);
//} catch (Exception e) {
//   System.out.println(e);
// }




