package com.example.gdjedanas.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gdjedanas.R;
import com.example.gdjedanas.models.Event;

import java.util.List;

public class EventAdapter
        extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> eventList;

    public EventAdapter(List<Event> eventList) {

        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.event_item,
                                parent,
                                false);

        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull EventViewHolder holder,
            int position) {

        Event event = eventList.get(position);

        // postavljanje slike

        holder.eventImage.setImageResource(
                event.getImage());

        // naziv eventa

        holder.eventTitle.setText(
                event.getTitle());

        // grad i lokacija

        holder.eventPlace.setText(
                event.getCity()
                        + " • "
                        + event.getPlace());

        // datum i vrijeme

        holder.eventDateTime.setText(
                event.getDate()
                        + " u "
                        + event.getTime());

        // opis događaja

        holder.eventDescription.setText(
                event.getDescription());

        // ocjena i broj recenzija

        holder.eventRating.setText(
                "Ocjena: "
                        + event.getRating()
                        + " ⭐ (" +
                        event.getReviews()
                        + " recenzija)");
    }

    @Override
    public int getItemCount() {

        return eventList.size();
    }

    public static class EventViewHolder
            extends RecyclerView.ViewHolder {

        ImageView eventImage;

        TextView eventTitle,
                eventPlace,
                eventDateTime,
                eventDescription,
                eventRating;

        public EventViewHolder(
                @NonNull View itemView) {

            super(itemView);

            eventImage =
                    itemView.findViewById(R.id.eventImage);

            eventTitle =
                    itemView.findViewById(R.id.eventTitle);

            eventPlace =
                    itemView.findViewById(R.id.eventPlace);

            eventDateTime =
                    itemView.findViewById(R.id.eventDateTime);

            eventDescription =
                    itemView.findViewById(R.id.eventDescription);

            eventRating =
                    itemView.findViewById(R.id.eventRating);
        }
    }
}