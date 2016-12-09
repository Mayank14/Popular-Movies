package com.example.chabbram.popularmovies;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AndroidImageAdapter extends ArrayAdapter<String> {

    ArrayList<String> moviesList;
    public AndroidImageAdapter(Activity context,ArrayList<String> moviesList) {
        super(context,0,moviesList);
        this.moviesList=new ArrayList<String>(moviesList);
    }
    @Override
    public int getCount() {
        return moviesList.size();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String imageURL = moviesList.get(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_movie,parent,false);
        }
        ImageView movieThumbnail = (ImageView)convertView.findViewById(R.id.grid_item_forecast_imageview);
        Picasso.with(getContext()).load(imageURL).into(movieThumbnail);
        return convertView;
    }

}