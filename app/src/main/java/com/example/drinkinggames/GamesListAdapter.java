package com.example.drinkinggames;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GamesListAdapter extends RecyclerView.Adapter<GamesListAdapter.Holder> {

    List<ModelGames> games;
    Context context;

    public GamesListAdapter(List<ModelGames> games, Context context){
        this.games = games;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.games_card, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final String name = games.get(position).getName();

        holder.nameOfGameCard.setText(name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, IndividualGame.class);
                intent.putExtra("ModelGame", games.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView nameOfGameCard;
        public Holder(@NonNull View itemView) {
            super(itemView);

            nameOfGameCard = itemView.findViewById(R.id.nameOfGameCard);
        }
    }
}
