package com.logui.arghasen.criotam;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayersRecyclerAdapter extends RecyclerView.Adapter<PlayersRecyclerAdapter.PlayerRecyclerViewHolder> {

    Context context;

    ArrayList<PlayerPOJO> arrayList;

    ClickListener clickListener;

    public interface ClickListener{
        public void onPlayerClickListener(View view, int position);
    }

    public PlayersRecyclerAdapter(Context context, ArrayList<PlayerPOJO> arrayList, ClickListener clickListener){
        this.clickListener = clickListener;
        this.context= context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public PlayerRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_element, parent, false);
        return new PlayerRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerRecyclerViewHolder holder, int position) {

        Log.d("bind", ""+position);

        holder.player_name.setText(arrayList.get(position).getPlayerName());
        holder.player_id.setText(arrayList.get(position).getPlayerId());
        holder.player_icon.setText(""+arrayList.get(position).getPlayerName().trim().charAt(0));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class PlayerRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView player_name;
        TextView player_icon;
        TextView player_id;

        public PlayerRecyclerViewHolder(View itemView) {
            super(itemView);

            player_name = (TextView) itemView.findViewById(R.id.player_name);
            player_icon = (TextView) itemView.findViewById(R.id.player_icon);
            player_id = (TextView) itemView.findViewById(R.id.player_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   clickListener.onPlayerClickListener(view, getAdapterPosition());
                }
            });

        }
    }


}
