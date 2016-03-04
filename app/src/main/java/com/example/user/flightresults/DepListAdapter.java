package com.example.user.flightresults;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by USER on 12/3/2015.
 */
public class DepListAdapter extends ArrayAdapter<MoreDep> {

    ArrayList<MoreDep> listItem;
    String uri;
    int Resource;
    LayoutInflater inflater;
    ViewHolder holder;
    String dep;
    String arr;
    Context context;
    Integer selected_position = 0;

    public DepListAdapter(Context context, int resource, ArrayList<MoreDep> objects,String imgUri,String depcode,String arrcode) {
        super(context, resource, objects);
        listItem = objects;
        uri=imgUri;
        Resource = resource;
        dep=depcode;
        arr=arrcode;



        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {


        if (convertView == null) {
            convertView = inflater.inflate(Resource, null);

            holder = new ViewHolder();
            holder.itemImage = (ImageView) convertView.findViewById(R.id.image);
            holder.depcode1 = (TextView) convertView.findViewById(R.id.depcode1);
            holder.airarr1 = (TextView) convertView.findViewById(R.id.arrcode1);
            holder.deptime1 = (TextView) convertView.findViewById(R.id.deptime1);
            holder.arrtime1 = (TextView) convertView.findViewById(R.id.arrtime1);
            holder.dur1 = (TextView) convertView.findViewById(R.id.dur1);
            holder.chkbox=(CheckBox) convertView.findViewById(R.id.dep_check);



            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(position==selected_position)
        {
            holder.chkbox.setChecked(true);
        }
        else
        {
            holder.chkbox.setChecked(false);
        }
        notifyDataSetInvalidated();
        notifyDataSetChanged();

        //  String imageURI = "http://192.168.56.1/fmf/airlogo/airlogo/sm" + listItem.get(position).getAirline() + ".gif";

         new DownloadImage(holder.itemImage).execute(uri);
     //   holder.chkbox.setChecked(true);
        holder.depcode1.setText(dep);
        holder.airarr1.setText(arr);

        holder.deptime1.setText(listItem.get(position).getDep_date());
        holder.arrtime1.setText(listItem.get(position).getArr_date());
        holder.dur1.setText(listItem.get(position).getTime());
        holder.chkbox.setTag(position);
        holder.chkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selected_position = (Integer) view.getTag();

                notifyDataSetInvalidated();
                notifyDataSetChanged();
            }
        });

      /*  holder.layoutId.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Intent intent=new Intent(parent.getContext(), HotelDetails.class);
                        Intent intent = new Intent(parent.getContext(),ProductActivity.class);

                        intent.putExtra("unique_id",listItem.get(position).unique_id);
                        parent.getContext().startActivity(intent);
                    }
                }

        ); */


        return convertView;
    }

    static class ViewHolder {

        TextView depcode1;
        TextView airarr1;
        TextView deptime1;
        TextView arrtime1;
        TextView dur1;
        ImageView itemImage;
        CheckBox chkbox;


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
