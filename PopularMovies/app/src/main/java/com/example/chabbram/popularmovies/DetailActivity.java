package com.example.chabbram.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by chabbram on 12/6/2016.
 */

public class DetailActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
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
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class DetailFragment extends Fragment {

        public DetailFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            Intent intent = getActivity().getIntent();
            Bundle extras = intent.getExtras();
            String showTitle = extras.getString("EXTRA_TITLE");
            String showrelease = extras.getString("EXTRA_DATE");
            String showPoster = extras.getString("EXTRA_POSTER");
            String showPlot = extras.getString("EXTRA_PLOT");
            double avg = extras.getDouble("EXTRA_AVG");
            ((TextView)rootView.findViewById(R.id.movie_title)).setText(showTitle);
            ((TextView)rootView.findViewById(R.id.movie_release)).setText(showrelease);
            ImageView moviePoster = (ImageView)rootView.findViewById(R.id.movie_poster);
            Picasso.with(getContext()).load(showPoster).into(moviePoster);
            ((TextView)rootView.findViewById(R.id.movie_plot)).setText(showPlot);
            ((TextView)rootView.findViewById(R.id.movie_rating)).setText(Double.toString(avg));
            return rootView;
        }
    }
}
