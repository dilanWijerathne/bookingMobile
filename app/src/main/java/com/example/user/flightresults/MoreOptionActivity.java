package com.example.user.flightresults;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class MoreOptionActivity extends AppCompatActivity {
    String dep_date;
    String text=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_option);
                Bundle bundleObject = getIntent().getExtras();

            // Get ArrayList Bundle
            ArrayList<MoreDep> dep_list = (ArrayList<MoreDep>) bundleObject.getSerializable("departure");
              // Get ArrayList Bundle
        ArrayList<MoreArr> arr_list = (ArrayList<MoreArr>) bundleObject.getSerializable("arrival");

        String imgUri=getIntent().getStringExtra("imageURI");
        String depcode1=getIntent().getStringExtra("depcode1");
        String arrcode1=getIntent().getStringExtra("arrcode1");
        String depcode2=getIntent().getStringExtra("depcode2");
        String arrcode2=getIntent().getStringExtra("arrcode2");
        String price=getIntent().getStringExtra("price");
        Integer index=getIntent().getIntExtra("index",0);

        ListView listview_dep = (ListView) findViewById(R.id.listdeparture);
   //     listview_dep.setChoiceMode(listview_dep.CHOICE_MODE_SINGLE);
        DepListAdapter adapter_dep = new DepListAdapter(getApplicationContext(), R.layout.layout_departure, dep_list,imgUri,depcode1,arrcode1);
        listview_dep.setAdapter(adapter_dep);
        ListView listview_arr = (ListView) findViewById(R.id.listarrival);
     //   listview_arr.setChoiceMode(listview_arr.CHOICE_MODE_SINGLE);
        ArrListAdapter adapter_arr = new ArrListAdapter(getApplicationContext(), R.layout.layout_arrival, arr_list,imgUri,depcode2,arrcode2);
        listview_arr.setAdapter(adapter_arr);

        TextView amt=(TextView) findViewById(R.id.price);
        amt.setText("LKR "+price);

    }

}
