package ba.moviecatalog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;

public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView moviePoster;
    private TextView movieTitle;
    private TextView movieGenre;
    private TextView movieDescription;
    private RatingBar movieRatingBar;
    private Button favoriteButton;
    private RecyclerView recyclerActors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        moviePoster = findViewById(R.id.moviePoster);
        movieTitle = findViewById(R.id.movieTitle);
        movieGenre = findViewById(R.id.movieGenre);
        movieDescription = findViewById(R.id.movieDescription);
        movieRatingBar = findViewById(R.id.movieRatingBar);
        favoriteButton = findViewById(R.id.favoriteButton);
        recyclerActors = findViewById(R.id.recyclerActorsInMovie);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        if (movie != null) {

            moviePoster.setImageResource(movie.getImageResId());
            movieTitle.setText(movie.getTitle());
            movieGenre.setText(movie.getGenre());
            movieDescription.setText(movie.getDescription());
            movieRatingBar.setRating(movie.getRating());

            ActorAdapterAll actorAdapter =
                    new ActorAdapterAll(this, Arrays.asList(movie.getActors()));

            recyclerActors.setLayoutManager(new LinearLayoutManager(this));
            recyclerActors.setAdapter(actorAdapter);

            favoriteButton.setOnClickListener(v -> {

                movie.setFavorit(true);

                Toast.makeText(
                        MovieDetailsActivity.this,
                        movie.getTitle() + " added to favorites!",
                        Toast.LENGTH_SHORT
                ).show();
            });
        }

        BottomNavigationView bottomNavigationView =
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.nav_movies) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            }

            if (item.getItemId() == R.id.nav_actors) {
                startActivity(new Intent(this, ActorsActivity.class));
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