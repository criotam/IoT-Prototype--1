package com.logui.arghasen.criotam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExperimentActivity extends AppCompatActivity {

    @BindView(R.id.player_icon)
    TextView player_icon;
    @BindView(R.id.player_name)
    TextView player_name;
    @BindView(R.id.player_id)
    TextView player_id;
    @BindView(R.id.age)
    TextView age;
    @BindView(R.id.weight)
    TextView weight;
    @BindView(R.id.height)
    TextView height;
    @BindView(R.id.gender)
    TextView gender;
    @BindView(R.id.btnexp1)
    Button btnexp1;
    @BindView(R.id.btnexp2)
    Button btnexp2;
    @BindView(R.id.btnexp3)
    Button btnexp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);

        ButterKnife.bind(this);

        if(getIntent().getStringExtra("player_id")!=null){

            player_icon.setText(getIntent().getStringExtra("player_name").toString().trim().charAt(0)+"");
            player_id.setText(getIntent().getStringExtra("player_id").toString()+"");
            player_name.setText(getIntent().getStringExtra("player_name")+"");
            age.setText(getIntent().getStringExtra("player_age")+"");
            weight.setText(getIntent().getStringExtra("player_weight")+"");
            height.setText(getIntent().getStringExtra("player_height")+"");
            gender.setText(getIntent().getStringExtra("player_sex")+"");

        }

        btnexp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ExperimentActivity.this, GraphPlotActivity.class);
                intent.putExtra("player_name", getIntent().getStringExtra("player_name")+"");
                intent.putExtra("player_id", getIntent().getStringExtra("player_id").toString()+"");
                intent.putExtra("player_age", getIntent().getStringExtra("player_age")+"");
                intent.putExtra("player_weight", getIntent().getStringExtra("player_weight")+"");
                intent.putExtra("player_height", getIntent().getStringExtra("player_height")+"");
                intent.putExtra("player_sex", getIntent().getStringExtra("player_sex")+"");
                intent.putExtra("exp","exp1");

                startActivity(intent);

            }
        });

        btnexp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ExperimentActivity.this, GraphPlotActivity.class);
                intent.putExtra("player_name", getIntent().getStringExtra("player_name")+"");
                intent.putExtra("player_id", getIntent().getStringExtra("player_id").toString()+"");
                intent.putExtra("player_age", getIntent().getStringExtra("player_age")+"");
                intent.putExtra("player_weight", getIntent().getStringExtra("player_weight")+"");
                intent.putExtra("player_height", getIntent().getStringExtra("player_height")+"");
                intent.putExtra("player_sex", getIntent().getStringExtra("player_sex")+"");
                intent.putExtra("exp","exp2");

                startActivity(intent);
            }
        });

        btnexp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ExperimentActivity.this, GraphPlotActivity.class);
                intent.putExtra("player_name", getIntent().getStringExtra("player_name")+"");
                intent.putExtra("player_id", getIntent().getStringExtra("player_id").toString()+"");
                intent.putExtra("player_age", getIntent().getStringExtra("player_age")+"");
                intent.putExtra("player_weight", getIntent().getStringExtra("player_weight")+"");
                intent.putExtra("player_height", getIntent().getStringExtra("player_height")+"");
                intent.putExtra("player_sex", getIntent().getStringExtra("player_sex")+"");
                intent.putExtra("exp","exp2");

                startActivity(intent);
            }
        });

    }
}
