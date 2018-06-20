package com.logui.arghasen.criotam;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveStreamingActivity extends AppCompatActivity {

    private LineGraphSeries<DataPoint> stream_points;

    @BindView(R.id.parent_layout)
    LinearLayout parent_layout;
    @BindView(R.id.live_stream_graph)
    GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_stream);

        ButterKnife.bind(this);

        stream_points = new LineGraphSeries<DataPoint>();

        graphView.addSeries(stream_points);
        graphView.getViewport().setXAxisBoundsManual(true);

        plotData();
    }

    Double initial_time = -1.0;



    public void plotData(){

        String param = "";

        if(getIntent().getStringExtra("exp").toString().trim().equalsIgnoreCase("exp1")){

            if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("load_cell")){

                param = "exp1_lc_streaming";

            }else if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("force")){

                param = "exp1_lc_streaming";

            }else if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("moment")){

                param = "exp1_lc_streaming";
            }

        }else if(getIntent().getStringExtra("exp").toString().trim().equalsIgnoreCase("exp2")){

            if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("load_cell")){

                param = "exp2_lc_streaming";

            }else if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("force")){

                param = "exp2_lc_streaming";

            }else if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("moment")){

                param = "exp2_lc_streaming";

            }else if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("emg")){

                param = "exp2_emg_streaming";

            }

        }else if(getIntent().getStringExtra("exp").toString().trim().equalsIgnoreCase("exp3")){

            if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("load_cell")){

                param = "exp3_fp_streaming";

            }else if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("force")){

                param = "exp3_fp_streaming";

            }else if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("moment")){

                param = "exp3_fp_streaming";

            }else if(getIntent().getStringExtra("parameter").toString().
                    trim().equalsIgnoreCase("emg")){

                param = "exp3_emg_streaming";

            }

        }

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userNameRef = dbRef.child("live_streaming")
                .child(getIntent().getStringExtra("player_id").toString()).child(param);
        ChildEventListener eventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    String data = (String) dataSnapshot.getValue();

                    Log.d("data size", data+"");


                    if(initial_time == -1){
                        initial_time = Double.parseDouble(data.split(":")[3]);
                    }

                    plot_points(new DataPoint((Double.parseDouble(data.split(":")[3])-initial_time)
                            ,(Double.parseDouble(data.split(":")[1]))));

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };

        userNameRef.addChildEventListener(eventListener);

    }

    public void plot_points(DataPoint dataPoint){

        stream_points.appendData(dataPoint, true, 1000);

    }
}
