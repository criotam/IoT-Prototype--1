package com.logui.arghasen.criotam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class players extends AppCompatActivity {
    private Button player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        player=(Button)findViewById(R.id.btnplay);
        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openexperiment();
            }
        });
    }
    public void openexperiment(){
        Intent intent = Intent(this,experiment.class);
        startActivity(intent);

    }

}
