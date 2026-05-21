package ba.moviecatalog;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> favorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        recyclerView = findViewById(R.id.recyclerFavorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        favorites = new ArrayList<>();

        movieAdapter = new MovieAdapter(this, favorites);
        recyclerView.setAdapter(movieAdapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_favorites);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.nav_movies) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return true;
            }

            if (item.getItemId() == R.id.nav_actors) {
                startActivity(new Intent(this, ActorsActivity.class));
                finish();
                return true;
            }

            return item.getItemId() == R.id.nav_favorites;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        favorites.clear();

        for (Movie movie : MovieData.getMovies()) {
            if (movie.isFavorit()) {
                favorites.add(movie);
            }
        }

        movieAdapter.notifyDataSetChanged();
    }
}