package com.example.gdjedanas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.gdjedanas.storage.FavoritesStorage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {

    Switch darkModeSwitch;
    Switch notificationSwitch;
    Button clearFavoritesButton;
    BottomNavigationView bottomNavigation;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        prefs = getSharedPreferences("settings", MODE_PRIVATE);

        boolean dark = prefs.getBoolean("darkmode", false);

        AppCompatDelegate.setDefaultNightMode(
                dark ? AppCompatDelegate.MODE_NIGHT_YES
                        : AppCompatDelegate.MODE_NIGHT_NO
        );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        darkModeSwitch = findViewById(R.id.darkModeSwitch);
        notificationSwitch = findViewById(R.id.notificationSwitch);
        clearFavoritesButton = findViewById(R.id.clearFavoritesButton);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        if (darkModeSwitch != null) {
            darkModeSwitch.setChecked(dark);

            darkModeSwitch.setOnCheckedChangeListener((b, isChecked) -> {
                prefs.edit().putBoolean("darkmode", isChecked).apply();

                AppCompatDelegate.setDefaultNightMode(
                        isChecked ? AppCompatDelegate.MODE_NIGHT_YES
                                : AppCompatDelegate.MODE_NIGHT_NO
                );
            });
        }

        clearFavoritesButton.setOnClickListener(v -> {
            FavoritesStorage.clearFavorites(this);
            Toast.makeText(this, "Favorites obrisani", Toast.LENGTH_SHORT).show();
        });

        // 🔥 BOTTOM NAV SAFE VERSION
        if (bottomNavigation != null) {

            bottomNavigation.setSelectedItemId(R.id.menu_settings);

            bottomNavigation.setOnItemSelectedListener(item -> {

                int id = item.getItemId();

                if (id == R.id.menu_home) {
                    startActivity(new Intent(this, HomeActivity.class));
                    finish();
                    return true;
                }

                if (id == R.id.menu_profile) {
                    startActivity(new Intent(this, ProfileActivity.class));
                    finish();
                    return true;
                }

                return true;
            });
        }
    }
}