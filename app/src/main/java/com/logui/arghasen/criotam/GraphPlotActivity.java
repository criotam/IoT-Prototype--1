package com.logui.arghasen.criotam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GraphPlotActivity extends AppCompatActivity {

    @BindView(R.id.history_data)
    LinearLayout history;
    @BindView(R.id.live_streaming)
    LinearLayout streaming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_and_history);

        ButterKnife.bind(this);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GraphPlotActivity.this, ParametersActivity.class);
                intent.putExtra("player_name", getIntent().getStringExtra("player_name")+"");
                intent.putExtra("player_id", getIntent().getStringExtra("player_id").toString()+"");
                intent.putExtra("player_age", getIntent().getStringExtra("player_age")+"");
                intent.putExtra("player_weight", getIntent().getStringExtra("player_weight")+"");
                intent.putExtra("player_height", getIntent().getStringExtra("player_height")+"");
                intent.putExtra("player_sex", getIntent().getStringExtra("player_sex")+"");
                intent.putExtra("exp",getIntent().getStringExtra("exp")+"");
                intent.putExtra("state","history");

                startActivity(intent);

            }
        });

        streaming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(GraphPlotActivity.this, ParametersActivity.class);
                intent.putExtra("player_name", getIntent().getStringExtra("player_name")+"");
                intent.putExtra("player_id", getIntent().getStringExtra("player_id").toString()+"");
                intent.putExtra("player_age", getIntent().getStringExtra("player_age")+"");
                intent.putExtra("player_weight", getIntent().getStringExtra("player_weight")+"");
                intent.putExtra("player_height", getIntent().getStringExtra("player_height")+"");
                intent.putExtra("player_sex", getIntent().getStringExtra("player_sex")+"");
                intent.putExtra("exp",getIntent().getStringExtra("exp")+"");
                intent.putExtra("state","live");

                startActivity(intent);

            }
        });
    }
}
