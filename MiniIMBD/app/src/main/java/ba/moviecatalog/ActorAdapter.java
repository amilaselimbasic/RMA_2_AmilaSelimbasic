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

// Adapter za prikaz glumaca u RecyclerView-u
// Povezuje listu Actor objekata sa XML layoutom (actor_item.xml)
public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ActorViewHolder> {

    private Context context;
    private List<Actor> actorList; // lista glumaca

    // Konstruktor – prima kontekst i listu glumaca
    public ActorAdapter(Context context, List<Actor> actorList) {
        this.context = context;
        this.actorList = actorList;
    }

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Povezuje XML layout (actor_item.xml) sa ViewHolder-om
        View view = LayoutInflater.from(context).inflate(R.layout.actor_item, parent, false);
        return new ActorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {
        // Uzima glumca sa određene pozicije u listi
        Actor actor = actorList.get(position);

        // Postavlja podatke u UI elemente
        holder.actorImage.setImageResource(actor.getImage()); // slika glumca
        holder.actorName.setText(actor.getName());            // ime glumca
    }

    @Override
    public int getItemCount() {
        // Vraća broj glumaca u listi
        return actorList.size();
    }

    // ViewHolder klasa – čuva reference na UI elemente iz actor_item.xml
    public static class ActorViewHolder extends RecyclerView.ViewHolder {
        ImageView actorImage;
        TextView actorName;

        public ActorViewHolder(@NonNull View itemView) {
            super(itemView);
            actorImage = itemView.findViewById(R.id.actorImage); // povezuje ImageView
            actorName = itemView.findViewById(R.id.actorName);   // povezuje TextView
        }
    }
}
