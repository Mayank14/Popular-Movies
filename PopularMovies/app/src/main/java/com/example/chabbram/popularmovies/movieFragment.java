package com.example.chabbram.popularmovies;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class movieFragment extends Fragment {
    private final String LOG_TAG= movieFragment.class.getSimpleName();
    GridView gridView;
    AndroidImageAdapter moviesList;
    ArrayList<myMovies> movieDetails = new ArrayList<myMovies>();
    String sortby = "popularity";
    public movieFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.moviefragment,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id == R.id.action_refresh) {
            onSort();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        gridView = (GridView) rootView.findViewById(R.id.gridview_movie);
           final String url = "https://api.themoviedb.org/3/movie/popular?api_key="+BuildConfig.TMDB_API_KEY;
            JsonObjectRequest jsonRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.v(LOG_TAG, "The response is" + response.toString());
                                JSONArray json = response.getJSONArray("results");
                                for (int i = 0; i < json.length(); i++) {
                                    JSONObject jsonObject = json.getJSONObject(i);
                                    String mthumbnail = "http://image.tmdb.org/t/p/w300/" + jsonObject.getString("poster_path");
                                    String title = jsonObject.getString("title");
                                    String backdrop = jsonObject.getString("backdrop_path");
                                    String poster = "http://image.tmdb.org/t/p/w500/" + backdrop;
                                    String plot = jsonObject.getString("overview");
                                    String release = jsonObject.getString("release_date");
                                    double popularity = jsonObject.getDouble("popularity");
                                    int votes = jsonObject.getInt("vote_count");
                                    double avg = jsonObject.getDouble("vote_average");
                                    movieDetails.add(new myMovies(title, release, plot, popularity, votes, avg, poster, mthumbnail));

                                }
                                moviesList = new AndroidImageAdapter(getActivity(), new ArrayList<String>());
                                gridView.setAdapter(moviesList);
                                onSort();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
            Volley.newRequestQueue(getActivity()).add(jsonRequest);
        return rootView;
    }

    public void onSort() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        sortby = prefs.getString(getString(R.string.pref_key),getString(R.string.value_popularity));
        ArrayList<String> imageList = new ArrayList<String>();
        if(sortby.equals("voting")){
            Collections.sort(movieDetails,new sortMovieByRating());
        }
        else if(sortby.equals("popularity")) {
            Collections.sort(movieDetails,new sortMovieByPopularity());
        }

        for(int i=0;i<movieDetails.size();i++) {
            imageList.add(movieDetails.get(i).getMthumbnail());
        }
        moviesList.clear();
        moviesList=new AndroidImageAdapter(getActivity(),imageList);
        gridView.setAdapter(moviesList);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String showTitle = movieDetails.get(position).getTitle();
                String releaseDate = movieDetails.get(position).getReleaseDate();
                String showPoster = movieDetails.get(position).getPoster_URL();
                String plot = movieDetails.get(position).getPlot();
                double avg = movieDetails.get(position).getAvg();
                Intent intent =  new Intent(getActivity(),DetailActivity.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_TITLE",showTitle);
                extras.putString("EXTRA_DATE",releaseDate);
                extras.putString("EXTRA_POSTER",showPoster);
                extras.putString("EXTRA_PLOT",plot);
                extras.putDouble("EXTRA_AVG",avg);
                intent.putExtras(extras);
                startActivity(intent);
            }

        });

    }

}

