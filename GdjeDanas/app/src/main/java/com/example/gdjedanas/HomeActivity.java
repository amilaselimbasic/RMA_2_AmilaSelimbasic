package com.example.gdjedanas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gdjedanas.adapters.EventAdapter;
import com.example.gdjedanas.models.Event;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EventAdapter adapter;

    List<Event> eventList = new ArrayList<>();

    SearchView searchView;

    Button btnAll, btnMusic, btnSport, btnTheater, btnOther;

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);

        btnAll = findViewById(R.id.btnAll);
        btnMusic = findViewById(R.id.btnMusic);
        btnSport = findViewById(R.id.btnSport);
        btnTheater = findViewById(R.id.btnTheater);
        btnOther = findViewById(R.id.btnOther);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadEvents();

        adapter = new EventAdapter(eventList, this);
        recyclerView.setAdapter(adapter);

        // SEARCH
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                List<Event> filtered = new ArrayList<>();

                for (Event e : eventList) {
                    if (e.getTitle().toLowerCase(Locale.ROOT)
                            .contains(text.toLowerCase(Locale.ROOT))) {
                        filtered.add(e);
                    }
                }

                adapter.filter(filtered);
                return true;
            }
        });

        // KATEGORIJE
        btnAll.setOnClickListener(v -> adapter.reset());

        btnMusic.setOnClickListener(v -> filter("muzika"));
        btnSport.setOnClickListener(v -> filter("sport"));
        btnTheater.setOnClickListener(v -> filter("predstava"));
        btnOther.setOnClickListener(v -> filter("ostalo"));

        // BOTTOM NAVIGATION (FIXED)
        bottomNavigation.setSelectedItemId(R.id.menu_home);

        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.menu_home) {
                return true;
            }

            if (id == R.id.menu_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                finish();
                return true;
            }

            if (id == R.id.menu_settings) {
                startActivity(new Intent(this, SettingsActivity.class));
                finish();
                return true;
            }

            return false;
        });
    }

    private void filter(String category) {

        List<Event> filtered = new ArrayList<>();

        for (Event e : eventList) {
            if (e.getCategory().equals(category)) {
                filtered.add(e);
            }
        }

        adapter.filter(filtered);
    }

    private void loadEvents() {

        eventList.clear();

        eventList.add(new Event(R.drawable.event1,
                "Ko je lud",
                "Zenica",
                "BNP",
                "10.06.2026",
                "20:00",
                "Predstava",
                4.7f,
                120,
                "predstava"));

        eventList.add(new Event(R.drawable.event2,
                "Crnogorska svadba",
                "Sarajevo",
                "Dom Mladih",
                "08.10.2026",
                "20:00",
                "Predstava",
                4.8f,
                200,
                "predstava"));

        eventList.add(new Event(R.drawable.event3,
                "BiH vs S. Makedonija",
                "Sarajevo",
                "Koševo",
                "29.05.2026",
                "20:30",
                "Sport",
                4.9f,
                500,
                "sport"));

        eventList.add(new Event(R.drawable.event4,
                "U19 Championship",
                "Zenica",
                "Bilino Polje",
                "27.06.2026",
                "15:00",
                "Sport",
                4.6f,
                300,
                "sport"));

        eventList.add(new Event(R.drawable.event5,
                "BiH vs Germany",
                "Zenica",
                "Trening centar",
                "27.06.2026",
                "17:00",
                "Sport",
                4.8f,
                420,
                "sport"));

        eventList.add(new Event(R.drawable.event6,
                "Sweden vs Poland",
                "Sarajevo",
                "Grbavica",
                "27.06.2026",
                "20:00",
                "Sport",
                4.5f,
                260,
                "sport"));

        eventList.add(new Event(R.drawable.event7,
                "BiH vs Sweden",
                "Zenica",
                "Bilino Polje",
                "30.06.2026",
                "17:00",
                "Sport",
                4.7f,
                310,
                "sport"));

        eventList.add(new Event(R.drawable.event8,
                "Crnogorska svadba 2",
                "Mostar",
                "Kosača",
                "07.10.2026",
                "20:00",
                "Predstava",
                4.4f,
                150,
                "predstava"));

        eventList.add(new Event(R.drawable.event9,
                "Who See",
                "Sarajevo",
                "Sloga",
                "22.05.2026",
                "20:00",
                "Koncert",
                4.9f,
                600,
                "muzika"));

        eventList.add(new Event(R.drawable.event10,
                "Da sam ja neko",
                "Banja Luka",
                "Banski Dvor",
                "20.06.2026",
                "20:00",
                "Predstava",
                4.6f,
                180,
                "predstava"));

        eventList.add(new Event(R.drawable.event11,
                "Petar Grašo",
                "Banja Luka",
                "Kastel",
                "02.07.2026",
                "20:30",
                "Koncert",
                4.8f,
                700,
                "muzika"));

        eventList.add(new Event(R.drawable.event12,
                "Yasmin Levy",
                "Banja Luka",
                "Kastel",
                "03.07.2026",
                "20:30",
                "Koncert",
                4.7f,
                500,
                "muzika"));

        eventList.add(new Event(R.drawable.event13,
                "Garden of Dreams",
                "Sarajevo",
                "Svjetlost",
                "13-19.06.2026",
                "18:00",
                "Festival",
                5.0f,
                1200,
                "muzika"));

        eventList.add(new Event(R.drawable.event14,
                "Mostar Summer Fest",
                "Mostar",
                "SC Kantarević",
                "02-04.07.2026",
                "20:00",
                "Festival",
                4.8f,
                900,
                "muzika"));

        eventList.add(new Event(R.drawable.event15,
                "Fresh Wave",
                "Banja Luka",
                "Kastel",
                "06-08.08.2026",
                "20:00",
                "Festival",
                4.9f,
                1100,
                "muzika"));

        eventList.add(new Event(R.drawable.event16,
                "Nebitno Kerim",
                "Sarajevo",
                "BKC",
                "17.06.2026",
                "20:00",
                "Predstava",
                4.5f,
                140,
                "predstava"));

        eventList.add(new Event(R.drawable.event17,
                "Da sam ja neko",
                "Međugorje",
                "Herceg Etno selo",
                "29.07.2026",
                "21:00",
                "Predstava",
                4.6f,
                160,
                "predstava"));

        eventList.add(new Event(R.drawable.event18,
                "Sarajevo Film Festival",
                "Sarajevo",
                "Centar",
                "14-21.08.2026",
                "Cijeli dan",
                "Festival",
                5.0f,
                2000,
                "ostalo"));

        eventList.add(new Event(R.drawable.event19,
                "Degustacija",
                "Sarajevo",
                "Hotel Bosna",
                "05.06.2026",
                "16:00-22:00",
                "Event",
                4.3f,
                90,
                "ostalo"));

        eventList.add(new Event(R.drawable.event20,
                "100 EXPO",
                "Sarajevo",
                "Hotel Hills",
                "20-21.10.2026",
                "10:00",
                "Sajam",
                4.4f,
                300,
                "ostalo"));
    }
}