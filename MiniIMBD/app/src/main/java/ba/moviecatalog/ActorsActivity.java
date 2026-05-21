package ba.moviecatalog;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actors);

        List<Movie> movies = MovieData.getMovies();

        Set<String> seenNames = new HashSet<>();
        List<Actor> allActors = new ArrayList<>();

        for (Movie movie : movies) {
            for (Actor actor : movie.getActors()) {
                if (!seenNames.contains(actor.getName())) {
                    allActors.add(actor);
                    seenNames.add(actor.getName());
                }
            }
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerActors);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ActorAdapterAll adapter = new ActorAdapterAll(this, allActors);
        recyclerView.setAdapter(adapter);

        // 🔽 DODANA NAVIGACIJA
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.nav_movies) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            }

            if (item.getItemId() == R.id.nav_actors) {
                return true;
            }

            if (item.getItemId() == R.id.nav_favorites) {
                startActivity(new Intent(this, FavoritesActivity.class));
                return true;
            }

            return false;
        });
    }
}