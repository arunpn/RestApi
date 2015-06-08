package com.example.a1nagar.flowercatalog;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1nagar.flowercatalog.model.Flower;
import com.example.a1nagar.flowercatalog.parsers.FlowerJsonParser;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    TextView output;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         output = (TextView) findViewById(R.id.textView);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);

         output.setMovementMethod( new ScrollingMovementMethod());


        for(int i=0; i < 3; i++){

            updateDisplay("Hi How Are You");

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

        boolean handled = true;
        int id = item.getItemId();

        switch(id) {
            case R.id.action_doTask:
                // updateDisplay("Task Done");
                if(isOnline()) {
                    requestData("http://services.hanselandpetal.com/feeds/flowers.json");
                }
                else {
                    Toast.makeText(this,"Device Not Connected to the Internet",Toast.LENGTH_LONG).show();

                }
                break;
            default:
                handled = super.onOptionsItemSelected(item);

        }


        //noinspection SimplifiableIfStatement

        return handled;
    }

    private void requestData(String uri) {
        myTask t = new myTask();
        t.execute(uri);
    }

    private void updateDisplay(String message) {
        output.append(message + "\n");

    }

    protected boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nm = cm.getActiveNetworkInfo();

        if (nm!=null && nm.isConnectedOrConnecting()) {
            return true;
        }else {
            return false;
        }


    }

    private class myTask extends AsyncTask<String ,String,String>
    {
        @Override
        protected void onPreExecute() {
            updateDisplay("Starting Task");
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {

            List<Flower> flowerList = FlowerJsonParser.parseFeed(s);

            for (Flower flower : flowerList) {
                updateDisplay(flower.getName());

            }



            pb.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            updateDisplay(values[0]);
        }



        @Override
        protected String doInBackground(String... params) {
            String str = HttpManager.getData(params[0]);
            return str;
        }


    }
}
