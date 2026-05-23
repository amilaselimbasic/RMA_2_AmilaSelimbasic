package com.example.gdjedanas.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gdjedanas.R;
import com.example.gdjedanas.models.Event;

import java.util.List;

public class FavoriteAdapter
        extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    List<Event> favoriteList;

    public FavoriteAdapter(List<Event> favoriteList) {

        this.favoriteList = favoriteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                android.R.layout.simple_list_item_2,
                                parent,
                                false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        Event event = favoriteList.get(position);

        holder.title.setText(
                event.getTitle());

        holder.subtitle.setText(
                event.getCity()
                        + " • "
                        + event.getPlace());
    }

    @Override
    public int getItemCount() {

        return favoriteList.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        TextView title;
        TextView subtitle;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            title =
                    itemView.findViewById(
                            android.R.id.text1);

            subtitle =
                    itemView.findViewById(
                            android.R.id.text2);
        }
    }
}