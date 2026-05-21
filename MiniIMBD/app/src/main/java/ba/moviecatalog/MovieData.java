package ba.moviecatalog;

import java.util.ArrayList;
import java.util.List;

public class MovieData {

    private static List<Movie> movies;

    public static List<Movie> getMovies() {

        if (movies == null) {

            movies = new ArrayList<>();

            Actor[] titanicActors = {
                    new Actor(R.drawable.dicaprio, "Leonardo DiCaprio"),
                    new Actor(R.drawable.winslet, "Kate Winslet"),
                    new Actor(R.drawable.zane, "Billy Zane"),
                    new Actor(R.drawable.bates, "Kathy Bates"),
                    new Actor(R.drawable.frances, "Frances Fisher")
            };

            Actor[] nowYouSeeMeActors = {
                    new Actor(R.drawable.franco, "Dave Franco"),
                    new Actor(R.drawable.harrelson, "Woody Harrelson"),
                    new Actor(R.drawable.fisher, "Isla Fisher"),
                    new Actor(R.drawable.close, "Glenn Close"),
                    new Actor(R.drawable.rubeck, "Elias Rubeck")
            };

            Actor[] shutterActors = {
                    new Actor(R.drawable.dicaprio, "Leonardo DiCaprio"),
                    new Actor(R.drawable.rubeck, "Elias Rubeck"),
                    new Actor(R.drawable.williams, "Michelle Williams"),
                    new Actor(R.drawable.rodriguez, "Michelle Rodriguez"),
                    new Actor(R.drawable.brewster, "Jordana Brewster")
            };

            Actor[] fastActors = {
                    new Actor(R.drawable.walker, "Paul Walker"),
                    new Actor(R.drawable.rodriguez, "Michelle Rodriguez"),
                    new Actor(R.drawable.tyrese, "Tyrese Gibson"),
                    new Actor(R.drawable.brewster, "Jordana Brewster"),
                    new Actor(R.drawable.williams, "Michelle Williams")
            };

            Actor[] mondayActors = {
                    new Actor(R.drawable.noomi, "Noomi Rapace"),
                    new Actor(R.drawable.dafoe, "Willem Dafoe"),
                    new Actor(R.drawable.rubeck, "Elias Rubeck"),
                    new Actor(R.drawable.zane, "Billy Zane"),
                    new Actor(R.drawable.franco, "James Franco")
            };

            movies.add(new Movie(R.drawable.titanic, "Titanic", "Drama/Romansa", "Kultni film o ljubavi na Titanicu.", 4.5f, titanicActors));
            movies.add(new Movie(R.drawable.nowyou, "Now You See Me", "Krimi/Triler", "Magija i pljačke u modernom svijetu.", 4.3f, nowYouSeeMeActors));
            movies.add(new Movie(R.drawable.shutter, "Shutter Island", "Thriller", "Misterija na udaljenom ostrvu.", 4.2f, shutterActors));
            movies.add(new Movie(R.drawable.fast, "Fast & Furious", "Akcija", "Brza vožnja i adrenalinske scene.", 4.0f, fastActors));
            movies.add(new Movie(R.drawable.monday, "Monday", "Drama", "Priča o ljubavi i životu.", 3.5f, mondayActors));
        }

        return movies;
    }
}