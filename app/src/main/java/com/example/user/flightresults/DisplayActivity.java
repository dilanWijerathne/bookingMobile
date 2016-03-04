package com.example.user.flightresults;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {
    ArrayList<FlightResults> flight_results = new ArrayList<>();
    ArrayList<Departure> departure = new ArrayList<>();
    ArrayList<Arrival> arrival = new ArrayList<>();
    ArrayList<MoreDep> moreDeps; //contains all options for departure
    ArrayList<MoreArr> moreArrs = new ArrayList<>(); //contains all options for arrival
    String url = "http://192.168.56.1/fmf/ibe"; //get the json from the url
    JSONArray jsonarray;
    TextView tv;
    String arl_cod;
    String arl_name;
    String fare_before;
    String tot_fare;
    String dep_code;
    String air_dep;
    String arr_code;
    String air_arr;
    String dep_date;
    String flt_no;
    String arr_date;
    String time;
    Boolean zone;
    String nonstop;
    JSONArray options1;
    JSONArray options2;
    Integer more;
    JSONObject flightdetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display);
        tv = (TextView) findViewById(R.id.textView2);
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

                for (int i = 0; i < value.length(); i++) {
                    moreDeps = new ArrayList<>();
                    moreArrs = new ArrayList<>();

                    JSONObject eachobject = value.getJSONObject(i);
                    JSONObject main = eachobject.getJSONObject("main");
                    flightdetails=eachobject.getJSONObject("FlightDetails");
                    JSONObject boxdata = main.getJSONObject("boxData");
                    JSONObject flightdata = main.getJSONObject("flightData");
                    //Get details from box data
                    arl_cod = boxdata.getString("ARL_COD");
                    arl_name = boxdata.getString("airline");
                    fare_before = boxdata.getString("disTotfareBefore");
                    tot_fare = boxdata.getString("disTotprice");

                    JSONArray rows = flightdata.getJSONArray("rows");
                    JSONObject rowsObjectOne = rows.getJSONObject(0);
                    dep_code = rowsObjectOne.getString("DEPAirCode");
                    air_dep = rowsObjectOne.getString("airDEP");
                    arr_code = rowsObjectOne.getString("ARRAirCode");
                    air_arr = rowsObjectOne.getString("airARR");
                    options1 = rowsObjectOne.getJSONArray("rows"); //to get the json array from "row" from the 0th json object

                    for (int k = 0; k < options1.length(); k++) {
                        JSONObject det = options1.getJSONObject(k);

                        flt_no = det.getString("FlightNumber");
                        dep_date = det.getString("depDate");
                        arr_date = det.getString("arrDate");
                        time = det.getString("TimeTakes");
                        zone = det.getBoolean("isTimeZone");
                        nonstop = det.getString("nonstop");
                        moreDeps.add(new MoreDep(arr_date, dep_date, flt_no, nonstop, time, zone));

                    }
                    departure.add(new Departure(air_arr, air_dep, arr_code, dep_code));
                    JSONObject rowsObjectTwo = rows.getJSONObject(1);
                    dep_code = rowsObjectTwo.getString("DEPAirCode");
                    air_dep = rowsObjectTwo.getString("airDEP");
                    arr_code = rowsObjectTwo.getString("ARRAirCode");
                    air_arr = rowsObjectTwo.getString("airARR");
                    options2 = rowsObjectTwo.getJSONArray("rows"); //to get the json array from "row" from the 1st json object
                    for (int l = 0; l < options2.length(); l++) {
                        JSONObject det = options2.getJSONObject(l);

                        flt_no = det.getString("FlightNumber");
                        dep_date = det.getString("depDate");
                        arr_date = det.getString("arrDate");
                        time = det.getString("TimeTakes");
                        zone = det.getBoolean("isTimeZone");
                        nonstop = det.getString("nonstop");
                        moreArrs.add(new MoreArr(arr_date, dep_date, flt_no, nonstop, time, zone));

                    }
                    arrival.add(new Arrival(air_arr, air_dep, arr_code, dep_code));
                    if (options2.length() > 1) {
                        more = 1;
                    }
                    if (options1.length() >= options2.length()) {
                        more = options1.length();
                    } else {
                        more = options2.length();
                    }

                    flight_results.add(new FlightResults(i,arl_cod, arl_name, fare_before, tot_fare, more, moreDeps, moreArrs));
                  }


            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            ListView listview = (ListView) findViewById(R.id.list);
            ItemListAdapter adapter = new ItemListAdapter(getApplicationContext(), R.layout.layout_search_result, flight_results, departure, arrival,flightdetails);
            listview.setAdapter(adapter);
        }
    }


}
