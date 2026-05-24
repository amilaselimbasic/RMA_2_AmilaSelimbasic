package com.example.gdjedanas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gdjedanas.adapters.FavoriteAdapter;
import com.example.gdjedanas.storage.FavoritesStorage;
import com.example.gdjedanas.storage.VisitedStorage;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    RecyclerView favoriteRecycler;
    RecyclerView visitedRecycler;

    TextView userEmail;

    Button logoutButton;
    Button resetPasswordButton;

    //  DODANO ZA EDIT PROFIL
    EditText editName;
    Button saveProfileButton;

    FavoriteAdapter favoriteAdapter;
    FavoriteAdapter visitedAdapter;

    BottomNavigationView bottomNavigation;

    //  Firebase DB
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // INIT VIEW
        userEmail = findViewById(R.id.userEmail);
        favoriteRecycler = findViewById(R.id.favoriteRecycler);
        visitedRecycler = findViewById(R.id.visitedRecycler);
        logoutButton = findViewById(R.id.logoutButton);
        resetPasswordButton = findViewById(R.id.resetPasswordButton);


        editName = findViewById(R.id.editName);
        saveProfileButton = findViewById(R.id.saveProfileButton);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        // Firebase reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // USER EMAIL
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            String email =
                    FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getEmail();

            userEmail.setText(email);

            // RESET PASSWORD
            resetPasswordButton.setOnClickListener(v -> {

                FirebaseAuth.getInstance()
                        .sendPasswordResetEmail(email)
                        .addOnSuccessListener(unused ->
                                Toast.makeText(
                                        this,
                                        "Reset email poslan",
                                        Toast.LENGTH_SHORT
                                ).show())
                        .addOnFailureListener(e ->
                                Toast.makeText(
                                        this,
                                        e.getMessage(),
                                        Toast.LENGTH_LONG
                                ).show());
            });
        }

        //   UČITAJ IME IZ FIREBASE
        databaseReference.child(userId)
                .child("name")
                .get()
                .addOnSuccessListener(snapshot -> {
                    if (snapshot.exists()) {
                        editName.setText(snapshot.getValue(String.class));
                    }
                });

        //  SAVE IME
        saveProfileButton.setOnClickListener(v -> {

            String newName = editName.getText().toString().trim();

            if (newName.isEmpty()) {
                editName.setError("Unesi ime");
                return;
            }

            databaseReference.child(userId)
                    .child("name")
                    .setValue(newName)
                    .addOnSuccessListener(unused ->
                            Toast.makeText(this,
                                    "Profil ažuriran",
                                    Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e ->
                            Toast.makeText(this,
                                    e.getMessage(),
                                    Toast.LENGTH_LONG).show());
        });

        // FAVORITES
        favoriteRecycler.setLayoutManager(
                new LinearLayoutManager(this));

        favoriteAdapter =
                new FavoriteAdapter(
                        FavoritesStorage.load(this));

        favoriteRecycler.setAdapter(favoriteAdapter);

        // VISITED
        if (visitedRecycler != null) {

            visitedRecycler.setLayoutManager(
                    new LinearLayoutManager(this));

            visitedAdapter =
                    new FavoriteAdapter(
                            VisitedStorage.load(this));

            visitedRecycler.setAdapter(visitedAdapter);
        }

        // LOGOUT
        logoutButton.setOnClickListener(v -> {

            FirebaseAuth.getInstance().signOut();

            Intent intent =
                    new Intent(this, LoginActivity.class);

            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_CLEAR_TASK
            );

            startActivity(intent);

            finish();
        });

        // BOTTOM NAVIGATION
        bottomNavigation.setSelectedItemId(R.id.menu_profile);

        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.menu_home) {

                startActivity(
                        new Intent(this, HomeActivity.class));

                finish();

                return true;
            }

            if (id == R.id.menu_settings) {

                startActivity(
                        new Intent(this, SettingsActivity.class));

                finish();

                return true;
            }

            return true;
        });
    }

    @Override
    protected void onResume() {

        super.onResume();

        favoriteAdapter =
                new FavoriteAdapter(
                        FavoritesStorage.load(this));

        favoriteRecycler.setAdapter(favoriteAdapter);

        if (visitedRecycler != null) {

            visitedAdapter =
                    new FavoriteAdapter(
                            VisitedStorage.load(this));

            visitedRecycler.setAdapter(visitedAdapter);
        }
    }
}