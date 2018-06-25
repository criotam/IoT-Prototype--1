package com.logui.arghasen.criotam;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveStreamingActivity extends AppCompatActivity {

    private LineGraphSeries<DataPoint> stream_points1;

    private LineGraphSeries<DataPoint> stream_points2;

    private LineGraphSeries<DataPoint> stream_points3;

    private LineGraphSeries<DataPoint> stream_points4;

    @BindView(R.id.parent_layout)
    LinearLayout parent_layout;
    @BindView(R.id.live_stream_graph)
    GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_stream);

        ButterKnife.bind(this);

        stream_points1 = new LineGraphSeries<DataPoint>();

        stream_points2 = new LineGraphSeries<DataPoint>();

        stream_points3 = new LineGraphSeries<DataPoint>();

        stream_points4 = new LineGraphSeries<DataPoint>();

        //graphView.getViewport().setXAxisBoundsManual(true);

        graphView.getViewport().setScrollable(true);
        graphView.getViewport().setScrollableY(true);
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScalableY(true);

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

                    plotGraph(data);

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


    public void plotGraph(String message){

        //System.out.println("Received message at GraphPlotter: " + message);

            if (message.toString().split(":")[0].equalsIgnoreCase("identifier_exp3fp")) {


                if (message.toString().split(":")[1].equalsIgnoreCase("mac_id")) {

                    //device mac id

                } else {

                    graphView.getViewport().scrollToEnd();

                    if (initial_time == -1) {

                        graphView.addSeries(stream_points1);

                        graphView.addSeries(stream_points2);

                        graphView.addSeries(stream_points3);

                        stream_points1.setTitle("Load Cell 1");
                        stream_points2.setTitle("Load Cell 2");
                        stream_points2.setColor(Color.RED);
                        stream_points3.setTitle("Load Cell 3");
                        stream_points3.setColor(Color.GREEN);
                        graphView.getLegendRenderer().setVisible(true);
                        graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

                        initial_time = Double.parseDouble(message.toString().split(":")[4] + "");
                    }

                    double time = (Double.parseDouble(message.toString().split(":")[4] + "")
                            - initial_time) / 1000;


                    stream_points1.appendData(new DataPoint(time
                            ,(Double.parseDouble(message.split(":")[1]))), true, 1000);

                    stream_points2.appendData(new DataPoint(time
                            ,(Double.parseDouble(message.split(":")[2]))), true, 1000);

                    stream_points3.appendData(new DataPoint(time
                            ,(Double.parseDouble(message.split(":")[3]))), true, 1000);


                }
            } else if (message.toString().split(":")[0].equalsIgnoreCase("identifier_exp2emg")) {


                if (message.toString().split(":")[1].equalsIgnoreCase("start_time")) {

                    //drawLine("Start time", start_x_point, 0);

                } else if (message.toString().split(":")[1].equalsIgnoreCase("end_time")) {

                    //drawLine("End time", end_x_point, 0);

                } else if (message.toString().split(":")[1].equalsIgnoreCase("start_race")) {

                    //drawLine("Start time", race_start_x_point, 0);

                } else if (message.toString().split(":")[1].equalsIgnoreCase("mac_id")) {

                    //device mac id

                } else {

                    if (initial_time == -1) {

                        graphView.addSeries(stream_points1);

                        initial_time = Double.parseDouble(message.toString().split(":")[2] + "");
                    }

                    double time = (Double.parseDouble(message.toString().split(":")[2] + "")
                            - initial_time) / 1000;

                    stream_points1.appendData(new DataPoint(time
                            ,(Double.parseDouble(message.split(":")[1]))), true, 1000);

                }
            } else if (message.toString().split(":")[0].equalsIgnoreCase("identifier_exp1lc")) {


                if (message.toString().split(":")[1].equalsIgnoreCase("mac_id")) {

                    //device mac id

                } else {

                    graphView.getViewport().scrollToEnd();

                    if (initial_time == -1) {

                        graphView.addSeries(stream_points1);

                        graphView.addSeries(stream_points2);

                        stream_points1.setTitle("Load Cell 1");
                        stream_points2.setTitle("Load Cell 2");
                        stream_points2.setColor(Color.RED);
                        graphView.getLegendRenderer().setVisible(true);
                        graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

                        initial_time = Double.parseDouble(message.toString().split(":")[3] + "");
                    }

                    double time = (Double.parseDouble(message.toString().split(":")[3] + "")
                            - initial_time) / 1000;

                    stream_points1.appendData(new DataPoint(time
                            ,(Double.parseDouble(message.split(":")[1]))), true, 1000);

                    stream_points2.appendData(new DataPoint(time
                            ,(Double.parseDouble(message.split(":")[2]))), true, 1000);


                }
            } else if (message.toString().split(":")[0].equalsIgnoreCase("identifier_exp2lc")) {


                if (message.toString().split(":")[1].equalsIgnoreCase("start_time")) {

                    //drawLine("Start time", start_x_point, 1);

                } else if (message.toString().split(":")[1].equalsIgnoreCase("end_time")) {

                    //drawLine("End time", end_x_point, 1);

                } else if (message.toString().split(":")[1].equalsIgnoreCase("start_race")) {

                    //drawLine("Start race", race_start_x_point, 1);

                    //temp_arrList_time = new ArrayList<>();
                    //race started

                } else if (message.toString().split(":")[1].equalsIgnoreCase("mac_id")) {

                    //device mac id

                } else {

                    graphView.getViewport().scrollToEnd();

                    if (initial_time == -1) {

                        graphView.addSeries(stream_points1);

                        graphView.addSeries(stream_points2);

                        stream_points1.setTitle("Load Cell 1");
                        stream_points2.setTitle("Load Cell 2");
                        stream_points2.setColor(Color.RED);
                        graphView.getLegendRenderer().setVisible(true);
                        graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

                        initial_time = Double.parseDouble(message.toString().split(":")[3] + "");
                    }

                    double time = (Double.parseDouble(message.toString().split(":")[3] + "")
                            - initial_time) / 1000;

                    stream_points1.appendData(new DataPoint(time
                            ,(Double.parseDouble(message.split(":")[1]))), true, 100000);

                    stream_points2.appendData(new DataPoint(time
                            ,(Double.parseDouble(message.split(":")[2]))), true, 100000);


                }
            } else if (message.toString().split(":")[0].equalsIgnoreCase("identifier_exp3emg")) {


                if (message.toString().split(":")[1].equalsIgnoreCase("mac_id")) {

                    //device mac id

                } else {

                    graphView.getViewport().scrollToEnd();

                    if (initial_time == -1) {

                        graphView.addSeries(stream_points1);

                        initial_time = Double.parseDouble(message.toString().split(":")[2] + "");
                    }

                    double time = (Double.parseDouble(message.toString().split(":")[2] + "")
                            - initial_time) / 1000;

                    stream_points1.appendData(new DataPoint(time
                            ,(Double.parseDouble(message.split(":")[1]))), true, 1000);

                }
            }

    }


}
