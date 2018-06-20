package com.logui.arghasen.criotam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
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

public class FetchFiles extends AppCompatActivity {

    FilesRecyclerAdapter filesRecyclerAdapter;
    ArrayList<FilesPOJO> files_list;

    @BindView(R.id.files_recycler)
    RecyclerView files_recycler;
    @BindView(R.id.refresh_files)
    SwipeRefreshLayout refresh_files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_list);

        ButterKnife.bind(this);

        files_list = new ArrayList<>();

        refresh_files.setRefreshing(true);

        filesRecyclerAdapter = new FilesRecyclerAdapter(FetchFiles.this, files_list, new FilesRecyclerAdapter.ClickListener() {
            @Override
            public void onFileClickListener(View view, int position, ArrayList<String> file_urls) {

                Intent intent = new Intent(FetchFiles.this, ListFiles.class);
                intent.putStringArrayListExtra("file_list",file_urls);
                startActivity(intent);
            }

        });

        files_recycler.setLayoutManager(new LinearLayoutManager(this));
        files_recycler.setAdapter(filesRecyclerAdapter);

        getPlayerList();

        refresh_files.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPlayerList();
            }
        });

    }

    public void getPlayerList(){

        String param = "";

        if(getIntent().getStringExtra("exp").toString().trim().equalsIgnoreCase("exp1")){

            if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("load_cell")){

                param = "raw_sensor_loadcell";

            }else if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("force")){

                param = "raw_sensor_loadcell";

            }else if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("moment")){

                param = "raw_sensor_loadcell";
            }

        }else if(getIntent().getStringExtra("exp").toString().trim().equalsIgnoreCase("exp2")){

            if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("load_cell")){

                param = "raw_sensor_loadcell";

            }else if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("force")){

                param = "raw_sensor_loadcell";

            }else if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("moment")){

                param = "raw_sensor_loadcell";

            }else if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("emg")){

                param = "raw_sensor_emg";

            }

        }else if(getIntent().getStringExtra("exp").toString().trim().equalsIgnoreCase("exp3")){

            if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("load_cell")){

                param = "raw_sensor_forceplate";

            }else if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("force")){

                param = "raw_sensor_forceplate";

            }else if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("moment")){

                param = "raw_sensor_forceplate";

            }else if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("emg")){

                param = "raw_sensor_emg";

            }

        }

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://us-central1-criotam-bec9b.cloudfunctions.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        apiInterface.getFileList(getIntent().getStringExtra("player_id").toLowerCase().trim(),
                getIntent().getStringExtra("exp").toString().trim(),param).enqueue(new Callback<List<FilesPOJO>>() {
            @Override
            public void onResponse(Call<List<FilesPOJO>> call, Response<List<FilesPOJO>> response) {
                refresh_files.setRefreshing(false);
                if(response.isSuccessful()){
                    files_list.clear();
                    files_list.addAll(response.body());
                    filesRecyclerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<FilesPOJO>> call, Throwable t) {
                refresh_files.setRefreshing(false);
                t.printStackTrace();
            }
        });

    }
}
