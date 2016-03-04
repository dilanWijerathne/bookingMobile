package com.example.user.flightresults;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

import static com.example.user.flightresults.R.id.layout1;

/**
 * Created by USER on 12/3/2015.
 */
public class ItemListAdapter extends ArrayAdapter<FlightResults> {

    ArrayList<FlightResults> listItem;
    ArrayList<Departure> deplist;
    ArrayList<Arrival> arrlist;
     int Resource;
    LayoutInflater inflater;
    ViewHolder holder;
    Context context;
    Integer ismore;
    JSONObject flt_detail;
    public ItemListAdapter(Context context, int resource, ArrayList<FlightResults> objects, ArrayList<Departure> obj, ArrayList<Arrival> ob,JSONObject flightdetails) {
        super(context, resource, objects);
        listItem = objects;
        Resource = resource;
        deplist = obj;
        arrlist = ob;
        flt_detail=flightdetails;


        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

         if (convertView == null) {
            convertView = inflater.inflate(Resource, null);

            holder = new ViewHolder();
            holder.itemImage = (ImageView) convertView.findViewById(R.id.image);
            holder.cod = (TextView) convertView.findViewById(R.id.cod);
            holder.before = (TextView) convertView.findViewById(R.id.before);
            holder.total = (TextView) convertView.findViewById(R.id.total);
            holder.depcode1 = (TextView) convertView.findViewById(R.id.depcode1);
            holder.airarr1 = (TextView) convertView.findViewById(R.id.arrcode1);
            holder.depcode2 = (TextView) convertView.findViewById(R.id.depcode2);
            holder.airarr2 = (TextView) convertView.findViewById(R.id.arrcode2);
            holder.deptime1 = (TextView) convertView.findViewById(R.id.deptime1);
            holder.arrtime1 = (TextView) convertView.findViewById(R.id.arrtime1);
            holder.dur1 = (TextView) convertView.findViewById(R.id.dur1);
            holder.deptime2 = (TextView) convertView.findViewById(R.id.deptime2);
            holder.arrtime2 = (TextView) convertView.findViewById(R.id.arrtime2);
            holder.dur2 = (TextView) convertView.findViewById(R.id.dur2);
             holder.layoutId=(LinearLayout) convertView.findViewById(R.id.layout1);

            holder.more=(TextView)convertView.findViewById(R.id.more);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

       ismore=listItem.get(position).getMore();


             holder.more.setText((ismore - 1) + " more time option available");
             if((ismore-1)>0) {
                 holder.more.setVisibility(convertView.VISIBLE);
             }
            else
             {
                 holder.more.setVisibility(convertView.GONE);
             }

        final String imageURI = "http://192.168.56.1/fmf/airlogo/airlogo/sm" + listItem.get(position).getAirline() + ".gif";
        //  String imageURI="https://ibe.findmyfare.com/images/airlogo/smEK.gif";
        new DownloadImage(holder.itemImage).execute(imageURI);
        holder.cod.setText(listItem.get(position).getArl_code());
        // holder.name.setText(listItem.get(position).getAirline());
        holder.before.setText("LKR " + listItem.get(position).getFare_before());
        holder.before.setPaintFlags(holder.before.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.total.setText("LKR " + listItem.get(position).getFare_total());
        holder.depcode1.setText(deplist.get(position).getDEPaircode());
        holder.airarr1.setText(deplist.get(position).getARRaircode());
        holder.depcode2.setText(arrlist.get(position).getDEPaircode());
        holder.airarr2.setText(arrlist.get(position).getARRaircode());
        final ArrayList<MoreDep> dep=listItem.get(position).getDeparture();
        final ArrayList<MoreArr> arr=listItem.get(position).getArrival();
        holder.deptime1.setText(dep.get(0).getDep_date());
        holder.arrtime1.setText(dep.get(0).getArr_date());
        holder.dur1.setText(dep.get(0).getTime());
        holder.deptime2.setText(arr.get(0).getDep_date());
        holder.arrtime2.setText(arr.get(0).getArr_date());
        holder.dur2.setText(arr.get(0).getTime());
        holder.layoutId.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Intent intent=new Intent(parent.getContext(), HotelDetails.class);
                        Intent intent = new Intent(parent.getContext(),FlightDetails.class);

                        intent.putExtra("index",listItem.get(position).getI());
                        parent.getContext().startActivity(intent);
                    }
                }

        );
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                Bundle b = new Bundle();
                 b.putSerializable("departure", dep);
                i.putExtra("imageURI", imageURI);
                i.putExtra("depcode1",deplist.get(position).getDEPaircode());
                i.putExtra("arrcode1", deplist.get(position).getARRaircode());
                b.putSerializable("arrival", arr);
                i.putExtra("depcode2", arrlist.get(position).getDEPaircode());
                i.putExtra("arrcode2",arrlist.get(position).getARRaircode());
                i.putExtra("price",listItem.get(position).getFare_total());
                i.putExtra("index",listItem.get(position).getI());
                Log.v("--", "OK");
                i.putExtras(b);
                i.setClass(parent.getContext(), MoreOptionActivity.class);
                parent.getContext().startActivity(i);


            }
        });


        return convertView;
    }

    static class ViewHolder {
        TextView cod;
        TextView name;
        TextView before;
        TextView total;
        LinearLayout layoutId;
        ImageView itemImage;
        TextView depcode1;
        TextView airdep1;
        TextView ARRcode1;
        TextView airarr1;
        TextView airarr2;
        TextView airdep2;
        TextView depcode2;
        TextView deptime1;
        TextView arrtime1;
        TextView dur1;
        TextView deptime2;
        TextView arrtime2;
        TextView dur2;
        TextView more;


    }


    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        ImageView bmImage;

        public DownloadImage(ImageView bmImage) {

            this.bmImage = bmImage;

        }

        @Override
        protected Bitmap doInBackground(String... urls) {

            String urldisplay = urls[0];
            Bitmap Icon = null;
            Log.d("IMG", "img ok");

            try {

                InputStream in = new java.net.URL(urldisplay).openStream();

                Icon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return Icon;
        }

        @Override
        protected void onPostExecute(Bitmap result) {

            bmImage.setImageBitmap(result);
        }

    }


}
