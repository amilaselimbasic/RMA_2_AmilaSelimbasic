package com.example.gdjedanas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class EventDetailsActivity extends AppCompatActivity {

    ImageView detailsImage;
    TextView detailsTitle, detailsLocation, detailsDate, detailsDescription, detailsRating, reviewList;
    EditText reviewInput;
    Button addReviewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        detailsImage = findViewById(R.id.detailsImage);
        detailsTitle = findViewById(R.id.detailsTitle);
        detailsLocation = findViewById(R.id.detailsLocation);
        detailsDate = findViewById(R.id.detailsDate);
        detailsDescription = findViewById(R.id.detailsDescription);
        detailsRating = findViewById(R.id.detailsRating);
        reviewList = findViewById(R.id.reviewList);
        reviewInput = findViewById(R.id.reviewInput);
        addReviewButton = findViewById(R.id.addReviewButton);

        Bundle b = getIntent().getExtras();

        if (b != null) {

            String title = b.getString("title");

            detailsImage.setImageResource(b.getInt("image"));
            detailsTitle.setText(title);
            detailsLocation.setText(b.getString("city") + " • " + b.getString("place"));
            detailsDate.setText(b.getString("date") + " u " + b.getString("time"));
            detailsDescription.setText(b.getString("description"));
            detailsRating.setText("Ocjena: " + b.getFloat("rating"));

            loadReviews(title);
        }

        addReviewButton.setOnClickListener(v -> {

            String review = reviewInput.getText().toString();
            String title = detailsTitle.getText().toString();

            if (!review.isEmpty()) {

                SharedPreferences sp = getSharedPreferences("reviews", MODE_PRIVATE);
                String old = sp.getString(title, "");

                sp.edit().putString(title, old + "\n• " + review).apply();

                reviewInput.setText("");
                loadReviews(title);

            } else {
                Toast.makeText(this, "Unesi recenziju", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadReviews(String title) {

        SharedPreferences sp = getSharedPreferences("reviews", MODE_PRIVATE);
        reviewList.setText(sp.getString(title, ""));
    }
}