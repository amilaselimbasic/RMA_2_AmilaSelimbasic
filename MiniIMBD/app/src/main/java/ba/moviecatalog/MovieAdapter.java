package ba.moviecatalog;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> implements Filterable {

    private Context context;
    private List<Movie> movieList;
    private List<Movie> movieListFull;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;

        // 🔥 BITNO: referenca ista, ne kopija
        this.movieListFull = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie movie = movieList.get(position);

        holder.poster.setImageResource(movie.getImageResId());
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.description.setText(movie.getDescription());
        holder.ratingBar.setRating(movie.getRating());

        // ⭐ FAVORIT ICON
        if (movie.isFavorit()) {
            holder.favoriteIcon.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            holder.favoriteIcon.setImageResource(android.R.drawable.btn_star_big_off);
        }

        // ⭐ TOGGLE FAVORIT
        holder.favoriteIcon.setOnClickListener(v -> {

            movie.setFavorit(!movie.isFavorit());
            notifyItemChanged(position);

        });

        // klik na film
        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, MovieDetailsActivity.class);
            intent.putExtra("movie", movie);
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView poster;
        ImageView favoriteIcon;
        TextView title;
        TextView genre;
        TextView description;
        RatingBar ratingBar;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.moviePoster);
            favoriteIcon = itemView.findViewById(R.id.movieFavorite);

            title = itemView.findViewById(R.id.movieTitle);
            genre = itemView.findViewById(R.id.movieGenre);
            description = itemView.findViewById(R.id.movieDescription);
            ratingBar = itemView.findViewById(R.id.movieRatingBar);
        }
    }

    @Override
    public Filter getFilter() {
        return movieFilter;
    }

    private Filter movieFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Movie> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(movieListFull);
            } else {

                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Movie movie : movieListFull) {
                    if (movie.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(movie);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            movieList.clear();
            movieList.addAll((List<Movie>) results.values);
            notifyDataSetChanged();

        }
    };
}