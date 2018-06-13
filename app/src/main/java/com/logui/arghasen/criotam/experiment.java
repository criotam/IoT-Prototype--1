package com.logui.arghasen.criotam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class experiment extends AppCompatActivity {
    private Button exp1;
    private Button exp2;
    private Button exp3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);
        exp1 = (Button)findViewById(R.id.btnexp1);
        exp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}
