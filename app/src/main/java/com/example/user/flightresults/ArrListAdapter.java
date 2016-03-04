package com.example.user.flightresults;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;


public class ArrListAdapter extends ArrayAdapter<MoreArr> {

    ArrayList<MoreArr> listItem;
    String uri;
    int Resource;
    LayoutInflater inflater;
    ViewHolder holder;
    String dep;
    String arr;
    Context context;
    Integer selected_position = 0;

    public ArrListAdapter(Context context, int resource, ArrayList<MoreArr> objects,String imgUri,String depcode,String arrcode) {
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
            holder.depcode2 = (TextView) convertView.findViewById(R.id.depcode2);
            holder.airarr2 = (TextView) convertView.findViewById(R.id.arrcode2);
            holder.deptime2 = (TextView) convertView.findViewById(R.id.deptime2);
            holder.arrtime2 = (TextView) convertView.findViewById(R.id.arrtime2);
            holder.dur2 = (TextView) convertView.findViewById(R.id.dur2);
            holder.chkbox=(CheckBox) convertView.findViewById(R.id.arr_check);
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

        holder.depcode2.setText(dep);
        holder.airarr2.setText(arr);

        holder.deptime2.setText(listItem.get(position).getDep_date());
        holder.arrtime2.setText(listItem.get(position).getArr_date());
        holder.dur2.setText(listItem.get(position).getTime());

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

        TextView depcode2;
        TextView airarr2;
        TextView deptime2;
        TextView arrtime2;
        TextView dur2;
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
