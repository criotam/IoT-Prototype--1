package com.logui.arghasen.criotam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParametersActivity extends AppCompatActivity {

    @BindView(R.id.load_cell)
    Button load_cell;
    @BindView(R.id.force)
    Button force;
    @BindView(R.id.moment)
    Button moment;
    @BindView(R.id.emg)
    Button emg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent().getStringExtra("exp")!=null){

            if(getIntent().getStringExtra("exp").toString().trim().equalsIgnoreCase("exp1")){

                setContentView(R.layout.activity_parameters_exp1);

            }else if(getIntent().getStringExtra("exp").toString().trim().equalsIgnoreCase("exp2")){

                setContentView(R.layout.activity_parameters_exp2);

            }else if(getIntent().getStringExtra("exp").toString().trim().equalsIgnoreCase("exp3")){

                setContentView(R.layout.activity_parameters_exp3);

            }else{
                setContentView(R.layout.activity_parameters_exp1);
            }
        }

        ButterKnife.bind(this);

        load_cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToStreamingOrHistory(getIntent().getStringExtra("state"), "load_cell");
            }
        });

        emg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToStreamingOrHistory(getIntent().getStringExtra("state"), "emg");
            }
        });

        force.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToStreamingOrHistory(getIntent().getStringExtra("state"), "force");
            }
        });

        moment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToStreamingOrHistory(getIntent().getStringExtra("state"), "moment");
            }
        });

    }

    public void goToStreamingOrHistory(String state, String  parameter){

        if(state.toString().trim().equalsIgnoreCase("history")) {
            Intent intent = new Intent(ParametersActivity.this, FetchFiles.class);
            intent.putExtra("player_name", getIntent().getStringExtra("player_name") + "");
            intent.putExtra("player_id", getIntent().getStringExtra("player_id").toString() + "");
            intent.putExtra("player_age", getIntent().getStringExtra("player_age") + "");
            intent.putExtra("player_weight", getIntent().getStringExtra("player_weight") + "");
            intent.putExtra("player_height", getIntent().getStringExtra("player_height") + "");
            intent.putExtra("player_sex", getIntent().getStringExtra("player_sex") + "");
            intent.putExtra("exp", getIntent().getStringExtra("exp")+" ");
            intent.putExtra("state", "history");
            intent.putExtra("parameter", parameter);

            startActivity(intent);

        }else if(state.toString().trim().equalsIgnoreCase("live")){

            Intent intent = new Intent(ParametersActivity.this, LiveStreamingActivity.class);
            intent.putExtra("player_name", getIntent().getStringExtra("player_name") + "");
            intent.putExtra("player_id", getIntent().getStringExtra("player_id").toString() + "");
            intent.putExtra("player_age", getIntent().getStringExtra("player_age") + "");
            intent.putExtra("player_weight", getIntent().getStringExtra("player_weight") + "");
            intent.putExtra("player_height", getIntent().getStringExtra("player_height") + "");
            intent.putExtra("player_sex", getIntent().getStringExtra("player_sex") + "");
            intent.putExtra("exp", getIntent().getStringExtra("exp")+" ");
            intent.putExtra("state", "live");
            intent.putExtra("parameter", parameter);

            startActivity(intent);
        }
    }


}
