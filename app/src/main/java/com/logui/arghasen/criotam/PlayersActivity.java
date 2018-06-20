package com.logui.arghasen.criotam;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayersActivity extends AppCompatActivity {

    PlayersRecyclerAdapter playersRecyclerAdapter;
    ArrayList<PlayerPOJO> player_list;

    @BindView(R.id.player_recycler)
    RecyclerView player_recycler;
    @BindView(R.id.refresh_player)
    SwipeRefreshLayout refresh_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        ButterKnife.bind(this);

        player_list = new ArrayList<>();

        refresh_player.setRefreshing(true);

        playersRecyclerAdapter = new PlayersRecyclerAdapter(PlayersActivity.this, player_list, new PlayersRecyclerAdapter.ClickListener() {
            @Override
            public void onPlayerClickListener(View view, int position) {

                Intent intent = new Intent(PlayersActivity.this, ExperimentActivity.class);
                intent.putExtra("player_name", player_list.get(position).getPlayerName());
                intent.putExtra("player_id", player_list.get(position).getPlayerId());
                intent.putExtra("player_age", player_list.get(position).getPlayerAge());
                intent.putExtra("player_weight", player_list.get(position).getPlayerWeight());
                intent.putExtra("player_height", player_list.get(position).getPlayerHeight());
                intent.putExtra("player_sex", player_list.get(position).getPlayerSex());

                startActivity(intent);

            }
        });

        player_recycler.setLayoutManager(new LinearLayoutManager(this));
        player_recycler.setAdapter(playersRecyclerAdapter);

        getPlayerList();

        refresh_player.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPlayerList();
            }
        });

    }

    public void getPlayerList(){

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://us-central1-criotam-bec9b.cloudfunctions.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        apiInterface.getPlayerList().enqueue(new Callback<List<PlayerPOJO>>() {
            @Override
            public void onResponse(Call<List<PlayerPOJO>> call, Response<List<PlayerPOJO>> response) {

                refresh_player.setRefreshing(false);

                if(response.isSuccessful()){

                    player_list.clear();
                    player_list.addAll(response.body());

                    Log.d("size ", ""+player_list.size());
                    playersRecyclerAdapter.notifyDataSetChanged();

                }else{
                    Log.d("Error occured", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<PlayerPOJO>> call, Throwable t) {

                refresh_player.setRefreshing(false);
                t.printStackTrace();
            }
        });
    }
}
