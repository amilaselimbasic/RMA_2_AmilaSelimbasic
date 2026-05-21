package ba.moviecatalog;

import android.os.Bundle;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;
import java.util.List;

// Glavni ekran aplikacije – prikazuje listu filmova
public class MainActivity extends AppCompatActivity {

    // Globalna lista filmova – koristi se u svim aktivnostima
    public static List<Movie> movieList;

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private SearchView searchView;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerMovies);
        searchView = findViewById(R.id.searchMovies);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Inicijaliziraj listu filmova samo jednom
        if (movieList == null) {
            movieList = MovieData.getMovies();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MovieAdapter(this, movieList);
        recyclerView.setAdapter(adapter);

        // Pretraga filmova
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        // Navigacija – ista logika kao u ostalim aktivnostima
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_movies) {
                return true; // već smo na ekranu filmova
            } else if (item.getItemId() == R.id.nav_actors) {
                startActivity(new Intent(this, ActorsActivity.class));
                return true;
            } else if (item.getItemId() == R.id.nav_favorites) {
                startActivity(new Intent(this, FavoritesActivity.class));
                return true;
            }
            return false;
        });
    }
}
