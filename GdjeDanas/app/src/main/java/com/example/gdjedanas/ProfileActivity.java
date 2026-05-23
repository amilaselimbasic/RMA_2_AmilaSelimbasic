package com.example.gdjedanas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gdjedanas.adapters.FavoriteAdapter;
import com.example.gdjedanas.storage.FavoritesStorage;
import com.example.gdjedanas.storage.VisitedStorage;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    RecyclerView fav, visited;
    TextView email;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_profile);

        email = findViewById(R.id.userEmail);
        fav = findViewById(R.id.favoriteRecycler);
        visited = findViewById(R.id.visitedRecycler);

        email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        fav.setLayoutManager(new LinearLayoutManager(this));
        visited.setLayoutManager(new LinearLayoutManager(this));

        fav.setAdapter(new FavoriteAdapter(FavoritesStorage.load(this)));
        visited.setAdapter(new FavoriteAdapter(VisitedStorage.load(this)));
    }
}