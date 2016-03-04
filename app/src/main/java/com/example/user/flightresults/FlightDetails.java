package com.example.user.flightresults;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FlightDetails extends AppCompatActivity {
    String url = "http://192.168.56.1/fmf/ibe"; //get the json from the url
    JSONArray jsonarray;
    Integer index;
    JSONObject flightdetails;
    JSONArray flt_grp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_detail);
        index=getIntent().getIntExtra("index",0);
        Log.v("index", index.toString());
        new DownloadJSON().execute();
    }

    class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            jsonarray = JSONFunctions
                    .getJSONfromURL(url, 0);
            try {
                // Locate the NodeList name
                JSONObject jsonobject = jsonarray.getJSONObject(0);


                JSONArray value = jsonobject.getJSONArray("FlightResults");


                    JSONObject eachobject = value.getJSONObject(index);
                    flightdetails=eachobject.getJSONObject("FlightDetails");
                    JSONArray segs=flightdetails.getJSONArray("segs");
                    JSONObject zero_seg=segs.getJSONObject(0);
                    flt_grp=zero_seg.getJSONArray("flt_grp");





            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            Log.v("msg",flt_grp.toString());

        }
    }
}
