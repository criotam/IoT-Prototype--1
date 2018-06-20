package com.logui.arghasen.criotam;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
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

    private LineGraphSeries<DataPoint> stream_points;

    public ArrayList<String> message;

    public String line;

    public String blockChaindata = "";

    String fileName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_stream);

        ButterKnife.bind(this);

        stream_points = new LineGraphSeries<DataPoint>();

        graphView.addSeries(stream_points);
        graphView.getViewport().setXAxisBoundsManual(true);

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

                System.out.println("reading csv...");

                line = scanner.nextLine().toString().trim();

                blockChaindata = blockChaindata + line + "$";

                message.add(line);

            }

            showPlot(message);


        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }


    }

    Double initial_time = -1.0;


    public void showPlot(ArrayList<String> message) {

        //GraphPlotterActivity graphPlotActivity = new GraphPlotterActivity(4, message.get(0).split(":")[0]);
        //graphPlotActivity.plotHistoryGraph(message);

        //storeDataOnBlockChain();

        if(initial_time == -1){
            initial_time = Double.parseDouble(message.get(0).split(":")[3]);
        }

        for(String msg: message){
            plot_points(new DataPoint((Double.parseDouble(msg.split(":")[3])-initial_time)
                    ,(Double.parseDouble(msg.split(":")[1]))));
        }

    }

    public void plot_points(DataPoint dataPoint){

        stream_points.appendData(dataPoint, true, 1000);

    }
}