package com.example.gdjedanas.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.gdjedanas.models.Event;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

// klasa služi za spremanje i učitavanje favorite događaja

public class FavoritesStorage {

    // naziv SharedPreferences fajla
    private static final String PREF = "favorites_pref";

    // ključ pod kojim se sprema lista favorita
    private static final String KEY = "favorites";

    // metoda za spremanje favorite liste
    public static void save(Context context, List<Event> list) {

        // otvaranje SharedPreferences
        SharedPreferences sp =
                context.getSharedPreferences(
                        PREF,
                        Context.MODE_PRIVATE);

        // editor za upis podataka
        SharedPreferences.Editor editor = sp.edit();

        // Gson pretvara Java objekte u JSON format
        Gson gson = new Gson();

        // spremanje liste kao JSON string
        editor.putString(KEY, gson.toJson(list));

        // potvrda spremanja
        editor.apply();
    }

    // metoda za učitavanje favorite liste
    public static List<Event> load(Context context) {

        // otvaranje SharedPreferences
        SharedPreferences sp =
                context.getSharedPreferences(
                        PREF,
                        Context.MODE_PRIVATE);

        // učitavanje JSON stringa
        String json = sp.getString(KEY, null);

        // ako nema podataka vraća praznu listu
        if (json == null)
            return new ArrayList<>();

        // kreiranje Gson objekta
        Gson gson = new Gson();

        // definisanje tipa liste
        Type type =
                new TypeToken<List<Event>>() {}.getType();

        // pretvaranje JSON stringa nazad u listu događaja
        return gson.fromJson(json, type);
    }

    // metoda za brisanje svih favorita
    public static void clearFavorites(Context context) {

        context.getSharedPreferences(
                        PREF,
                        Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }
}