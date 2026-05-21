package ba.moviecatalog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Adapter za prikaz svih glumaca u RecyclerView-u
public class ActorAdapterAll extends RecyclerView.Adapter<ActorAdapterAll.ActorViewHolder> {

    private Context context;
    private List<Actor> actorList;

    // Konstruktor – prima kontekst i listu glumaca
    public ActorAdapterAll(Context context, List<Actor> actorList) {
        this.context = context;
        this.actorList = actorList;
    }

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflater kreira izgled jednog item-a iz XML-a (actor_item.xml)
        View view = LayoutInflater.from(context).inflate(R.layout.actor_item, parent, false);
        return new ActorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {
        // Dohvati glumca na datoj poziciji
        Actor actor = actorList.get(position);

        // Postavi sliku i ime glumca
        holder.actorImage.setImageResource(actor.getImage());
        holder.actorName.setText(actor.getName());
    }

    @Override
    public int getItemCount() {
        return actorList.size();
    }

    // ViewHolder klasa – drži reference na elemente unutar jednog item-a
    public static class ActorViewHolder extends RecyclerView.ViewHolder {
        ImageView actorImage;
        TextView actorName;

        public ActorViewHolder(@NonNull View itemView) {
            super(itemView);
            actorImage = itemView.findViewById(R.id.actorImage);
            actorName = itemView.findViewById(R.id.actorName);
        }
    }
}
