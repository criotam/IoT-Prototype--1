package com.logui.arghasen.criotam;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadCsvFile extends AppCompatActivity{

    @BindView(R.id.live_stream_graph)
    GraphView graphView;

    //private LineGraphSeries<DataPoint> stream_points;

    LineGraphSeries<DataPoint> series1;

    LineGraphSeries<DataPoint> series2;

    LineGraphSeries<DataPoint> series3;

    LineGraphSeries<DataPoint> series4;


    public String parameter;

    public ArrayList<String> message;

    public String line;

    public String blockChaindata = "";

    String fileName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_stream);

        ButterKnife.bind(this);

        series1 = new LineGraphSeries<>( new DataPoint[]{new DataPoint(0, 0)});

        series2 = new LineGraphSeries<>( new DataPoint[]{new DataPoint(0, 0)});

        series3 = new LineGraphSeries<>( new DataPoint[]{new DataPoint(0, 0)});

        series4 = new LineGraphSeries<>( new DataPoint[]{new DataPoint(0, 0)});

        //graphView.addSeries(series1);
        //graphView.getViewport().setXAxisBoundsManual(true);

        graphView.getViewport().setScrollable(true);
        graphView.getViewport().setScrollableY(true);
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScalableY(true);

        fileName = getIntent().getStringExtra("filename");
        readCsv();
        //plotData();
    }

    public void readCsv(){

        this.fileName = fileName;
        message = new ArrayList();

        line = "";

        try {

            Scanner scanner = new Scanner(new File(fileName));
            Scanner dataScanner = null;
            int index = 0;
            int time_index = 0;

            while (scanner.hasNextLine()) {

                line = scanner.nextLine().toString().trim();

                System.out.println("reading csv..."+line);
                //blockChaindata = blockChaindata + line + "$";

                message.add(line);

            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    showPlot(message);

                }
            },300);


        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }


    }

    Double initial_time = -1.0;

    public void showPlot(ArrayList<String> message) {

        Log.d("size: ",""+message.size());
        //storeDataOnBlockChain();
        //DataPoint[] dataPoints = new DataPoint[message.size()];
/*
        if(initial_time == -1){
            initial_time = Double.parseDouble(message.get(0).split(":")[3]);
        }

        int i = 0;

        for(String msg: message){
            dataPoints[i] = new DataPoint(((Double.parseDouble(msg.split(":")[3]))-initial_time)/1000
                    ,(Double.parseDouble(msg.split(":")[1])));
            i++;
        }
        */

        //plot_points(dataPoints);

        plotHistoryGraph(message);
    }

    public void plot_points(DataPoint[] dataPoint){


        series1.resetData(dataPoint);
        graphView.addSeries(series1);

    }

    public void plotHistoryGraph(ArrayList<String> message){

        //System.out.println("Received message at GraphPlotter: " + message);

        int index = 0;

        int i = 0;


        DataPoint[] dataPoints1 = new DataPoint[message.size()];

        DataPoint[] dataPoints2 = new DataPoint[message.size()];

        DataPoint[] dataPoints3 = new DataPoint[message.size()];

        DataPoint[] dataPoints4 = new DataPoint[message.size()];


        for(String msg: message) {

            if (msg.toString().split(":")[0].equalsIgnoreCase("identifier_exp3fp")) {

                index = 3;

                if (msg.toString().split(":")[1].equalsIgnoreCase("mac_id")) {

                    //device mac id

                } else {
                    //xAxis.add(Double.parseDouble(msg.toString().split(":")[4]+""));

                    if (initial_time == -1) {
                        initial_time = Double.parseDouble(msg.toString().split(":")[4] + "");
                    }

                    double time = (Double.parseDouble(msg.toString().split(":")[4] + "")
                            - initial_time) / 1000;

                    if(time>=0 && Double.parseDouble(msg.toString().split(":")[4] + "")<900000) {

                        dataPoints1[i] = new DataPoint(time
                                , (Double.parseDouble(msg.split(":")[1])));

                        dataPoints2[i] = new DataPoint(time
                                , (Double.parseDouble(msg.split(":")[2])));

                        dataPoints3[i] = new DataPoint(time
                                , (Double.parseDouble(msg.split(":")[3])));
                        i++;
                    }

                }
            } else if (msg.toString().split(":")[0].equalsIgnoreCase("identifier_exp2emg")) {


                index = 1;

                if (msg.toString().split(":")[1].equalsIgnoreCase("start_time")) {

                    //drawLine("Start time", start_x_point, 0);

                } else if (msg.toString().split(":")[1].equalsIgnoreCase("end_time")) {

                    //drawLine("End time", end_x_point, 0);

                } else if (msg.toString().split(":")[1].equalsIgnoreCase("start_race")) {

                    //drawLine("Start time", race_start_x_point, 0);

                } else if (msg.toString().split(":")[1].equalsIgnoreCase("mac_id")) {

                    //device mac id

                } else {

                    //xAxis.add(Double.parseDouble(msg.toString().split(":")[2]+""));

                    if (initial_time == -1) {
                        initial_time = Double.parseDouble(msg.toString().split(":")[2] + "");
                    }

                    double time = (Double.parseDouble(msg.toString().split(":")[2] + "")
                            - initial_time) / 1000;

                    if(time>=0 && Double.parseDouble(msg.toString().split(":")[2] + "")<900000) {

                        dataPoints1[i] = new DataPoint(time
                                , (Double.parseDouble(msg.split(":")[1])));

                        i++;
                    }
                }
            } else if (msg.toString().split(":")[0].equalsIgnoreCase("identifier_exp1lc")) {

                index = 2;

                if (msg.toString().split(":")[1].equalsIgnoreCase("mac_id")) {

                    //device mac id

                } else {

                    //xAxis.add(Double.parseDouble(msg.toString().split(":")[3]+""));

                    if (initial_time == -1) {
                        initial_time = Double.parseDouble(msg.toString().split(":")[3] + "");
                    }

                    double time = (Double.parseDouble(msg.toString().split(":")[3] + "")
                            - initial_time) / 1000;

                    if(time>=0 && Double.parseDouble(msg.toString().split(":")[3] + "")<900000) {

                        dataPoints1[i] = new DataPoint(time
                                , (Double.parseDouble(msg.split(":")[1])));

                        dataPoints2[i] = new DataPoint(time
                                , (Double.parseDouble(msg.split(":")[2])));
                        i++;
                    }

                }
            } else if (msg.toString().split(":")[0].equalsIgnoreCase("identifier_exp2lc")) {

                index = 2;

                if (msg.toString().split(":")[1].equalsIgnoreCase("start_time")) {

                    //drawLine("Start time", start_x_point, 1);

                } else if (msg.toString().split(":")[1].equalsIgnoreCase("end_time")) {

                    //drawLine("End time", end_x_point, 1);

                } else if (msg.toString().split(":")[1].equalsIgnoreCase("start_race")) {

                    //drawLine("Start race", race_start_x_point, 1);

                    //temp_arrList_time = new ArrayList<>();
                    //race started

                } else if (msg.toString().split(":")[1].equalsIgnoreCase("mac_id")) {

                    //device mac id

                } else {

                    if (initial_time == -1) {
                        initial_time = Double.parseDouble(msg.toString().split(":")[3] + "");
                    }

                    double time = (Double.parseDouble(msg.toString().split(":")[3] + "")
                            - initial_time) / 1000;

                    if(time>=0 && Double.parseDouble(msg.toString().split(":")[3] + "")<900000) {

                        dataPoints1[i] = new DataPoint(time
                                , (Double.parseDouble(msg.split(":")[1])));

                        dataPoints2[i] = new DataPoint(time
                                , (Double.parseDouble(msg.split(":")[2])));
                        i++;
                    }

                }
            } else if (msg.toString().split(":")[0].equalsIgnoreCase("identifier_exp3emg")) {

                index = 1;

                if (msg.toString().split(":")[1].equalsIgnoreCase("mac_id")) {

                    //device mac id

                } else {

                    //xAxis.add(Double.parseDouble(msg.toString().split(":")[2]+""));

                    if (initial_time == -1) {
                        initial_time = Double.parseDouble(msg.toString().split(":")[2] + "");
                    }

                    double time = (Double.parseDouble(msg.toString().split(":")[2] + "")
                            - initial_time) / 1000;

                    if(time>=0 && Double.parseDouble(msg.toString().split(":")[3] + "")<900000) {

                        dataPoints1[i] = new DataPoint(time
                                , (Double.parseDouble(msg.split(":")[1])));

                        i++;
                    }

                }
            }
        }

        if(index == 1){

            series1.resetData(dataPoints1);
            graphView.addSeries(series1);

        }else if(index == 2){

            series1.resetData(dataPoints1);
            graphView.addSeries(series1);

            series2.resetData(dataPoints2);
            graphView.addSeries(series2);
            series2.setColor(Color.RED);
            //graphView.getGridLabelRenderer().setVerticalLabelsSecondScaleColor(Color.RED);

            series1.setTitle("Load Cell 1");
            series2.setTitle("Load Cell 2");
            graphView.getLegendRenderer().setVisible(true);
            graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

        }else if(index == 3){

            series1.resetData(dataPoints1);
            graphView.addSeries(series1);

            series2.resetData(dataPoints2);
            graphView.addSeries(series2);
            series2.setColor(Color.RED);
            //graphView.getGridLabelRenderer().setVerticalLabelsSecondScaleColor(Color.RED);

            series3.resetData(dataPoints3);
            graphView.addSeries(series3);
            series3.setColor(Color.GREEN);

            series1.setTitle("Load Cell 1");
            series2.setTitle("Load Cell 2");
            series3.setTitle("Load Cell 3");
            graphView.getLegendRenderer().setVisible(true);
            graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

        }

        graphView.getViewport().scrollToEnd();

    }

}