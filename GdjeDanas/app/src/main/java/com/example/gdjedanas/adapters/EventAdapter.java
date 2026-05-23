package com.example.gdjedanas.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gdjedanas.EventDetailsActivity;
import com.example.gdjedanas.R;
import com.example.gdjedanas.models.Event;
import com.example.gdjedanas.storage.FavoritesStorage;
import com.example.gdjedanas.storage.VisitedStorage;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> fullList;
    private List<Event> shownList;
    private List<Event> favorites;

    public EventAdapter(List<Event> list, Context context) {

        this.fullList = new ArrayList<>(list);
        this.shownList = new ArrayList<>(list);

        this.favorites = FavoritesStorage.load(context);

        for (Event e : fullList) {
            for (Event f : favorites) {
                if (e.equals(f)) {
                    e.setFavorite(true);
                }
            }
        }
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);

        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {

        Event event = shownList.get(position);
        Context context = holder.itemView.getContext();

        holder.eventImage.setImageResource(event.getImage());
        holder.eventTitle.setText(event.getTitle());
        holder.eventPlace.setText(event.getCity() + " • " + event.getPlace());
        holder.eventDateTime.setText(event.getDate() + " " + event.getTime());
        holder.eventDescription.setText(event.getDescription());
        holder.eventRating.setText("⭐ " + event.getRating());

        holder.btnFavorite.setImageResource(
                event.isFavorite()
                        ? android.R.drawable.btn_star_big_on
                        : android.R.drawable.btn_star_big_off
        );

        holder.btnFavorite.setOnClickListener(v -> {

            event.setFavorite(!event.isFavorite());

            if (event.isFavorite()) {
                if (!favorites.contains(event)) favorites.add(event);
                Toast.makeText(context, "Dodano u favorite", Toast.LENGTH_SHORT).show();
            } else {
                favorites.remove(event);
                Toast.makeText(context, "Uklonjeno", Toast.LENGTH_SHORT).show();
            }

            FavoritesStorage.save(context, favorites);
            notifyItemChanged(position);
        });

        holder.btnVisit.setOnClickListener(v -> {

            List<Event> visited = VisitedStorage.load(context);

            if (!visited.contains(event)) {
                visited.add(event);
                VisitedStorage.save(context, visited);

                Toast.makeText(context,
                        "Dodano u Želim posjetiti",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context,
                        "Već dodano",
                        Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnClickListener(v -> {

            Intent i = new Intent(context, EventDetailsActivity.class);

            i.putExtra("image", event.getImage());
            i.putExtra("title", event.getTitle());
            i.putExtra("city", event.getCity());
            i.putExtra("place", event.getPlace());
            i.putExtra("date", event.getDate());
            i.putExtra("time", event.getTime());
            i.putExtra("description", event.getDescription());
            i.putExtra("rating", event.getRating());

            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return shownList.size();
    }

    public void filter(List<Event> list) {
        shownList = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    public void reset() {
        shownList = new ArrayList<>(fullList);
        notifyDataSetChanged();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {

        ImageView eventImage;
        TextView eventTitle, eventPlace, eventDateTime, eventDescription, eventRating;
        ImageButton btnFavorite;
        Button btnVisit;

        EventViewHolder(@NonNull View itemView) {
            super(itemView);

            eventImage = itemView.findViewById(R.id.eventImage);
            eventTitle = itemView.findViewById(R.id.eventTitle);
            eventPlace = itemView.findViewById(R.id.eventPlace);
            eventDateTime = itemView.findViewById(R.id.eventDateTime);
            eventDescription = itemView.findViewById(R.id.eventDescription);
            eventRating = itemView.findViewById(R.id.eventRating);
            btnFavorite = itemView.findViewById(R.id.btnFavorite);
            btnVisit = itemView.findViewById(R.id.btnVisit);
        }
    }
}