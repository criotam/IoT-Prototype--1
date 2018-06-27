/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criotam.graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.SeriesMarkers;

/**
 *
 * @author AVINASH
 */
public class GraphPlotterActivity extends javax.swing.JFrame {

    private static double front_block_angle = 50;
    
    private static double rear_block_angle = 70;
    
    private static double DIST_FRONT = 3.0;
    
    private static double DIST_REAR = 4.0;
    
    
    public GraphPlotterUtil graphPlotUtil;
    
    
    public JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8, panel9, panel10, panel11, panel12;
    
    public JPanel[] panel = {panel1, panel2, panel3, panel4};
    
    public JPanel[] force_panel = {panel5, panel6, panel7, panel8};
    
    public JPanel[] moment_panel = {panel9, panel10, panel11, panel12};
    
    
    
    public XYChart chart1, chart2, chart3, chart4, chart5, chart6, chart7, chart8, chart9, chart10, chart11, chart12;
    
    public XYChart[] chart = {chart1, chart2, chart3, chart4};
    
    public XYChart[] force_chart = {chart5, chart6, chart7, chart8};
    
    public XYChart[] moment_chart = {chart9, chart10, chart11, chart12};
    
    
    
    public SwingWrapper<XYChart> sw;
   
    public double start_x_point = -1;
    
    public double start_y_point = -1;
    
    public double end_x_point;
    
    public double race_start_x_point;
    
    public int tab_count = 0;
    
    public ArrayList<Double> yAxis_sensor1;
    
    public ArrayList<Double> yAxis_sensor2;
    
    public ArrayList<Double> yAxis_sensor3;
    
    public ArrayList<Double> yAxis_sensor4;
    
    public ArrayList<Double> xAxis;
    
    List<XYChart> chart_matrix;
    
    private String identifer;
    
    /**
     * Creates new form GraphPlotterActivity
     */
    public GraphPlotterActivity() {
        initComponents();
        getContentPane().setBackground(Color.WHITE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
    }
    
    public GraphPlotterActivity(int tab_count, String identifier) {
        
        initial_init();
        getContentPane().setBackground(Color.WHITE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        this.start_x_point = 0;
        this.start_y_point = 0;
        this.end_x_point = 0;
        this.race_start_x_point = 0;
        
        this.flag_end_time = false;
        this.flag_start_time = false;
        this.flag_start_race = false;
        
        yAxis_sensor1 = new ArrayList();
        yAxis_sensor2 = new ArrayList();
        yAxis_sensor3 = new ArrayList();
        yAxis_sensor4 = new ArrayList();
        xAxis = new ArrayList();
        chart_matrix = new ArrayList<XYChart>();
        
        this.tab_count = tab_count;
        this.identifer = identifier;
        setUpPlotArea(tab_count, identifier);
        
        reaction_time.setVisible(false);
        start_time.setVisible(false);
        end_time.setVisible(false);
        total_time.setVisible(false);
        
    }

    
    public void setUpPlotArea(int tab_count, String identifier){
        
        if(identifier.equalsIgnoreCase("identifier_exp1lc")){
            
            setTitle("Exp1");
           
            panel_tab1.setLayout(new BorderLayout());
            panel_tab1.add(setUpChartPannel(2, 2, 1, chart, panel, "load_cell"));
            
            
            panel_tab2.setLayout(new BorderLayout());
            panel_tab2.add(setUpChartPannel(4, 2, 2, force_chart, force_panel, "force"));
            
            panel_tab3.setLayout(new BorderLayout());
            panel_tab3.add(setUpChartPannel(2, 2, 1, moment_chart, moment_panel, "moment"));
    
            custom_initComponents();
            
            tab_container.addTab("Load Cell", jPanel1);
            tab_container.getComponent(0).setBackground(new Color(52, 51, 51));
            tab_container.addTab("Force", jPanel2);
            tab_container.getComponent(1).setBackground(new Color(52, 51, 51));
            tab_container.addTab("Moment", jPanel3);
            tab_container.getComponent(2).setBackground(new Color(52, 51, 51));
            
            //add more tabs
            
        }else if(identifier.equalsIgnoreCase("identifier_exp2lc")){
            
            setTitle("Exp2");
            
            panel_tab1.setLayout(new BorderLayout());
            panel_tab1.add(setUpChartPannel(2, 2, 1, chart, panel, "load_cell"));
            
            
            panel_tab2.setLayout(new BorderLayout());
            panel_tab2.add(setUpChartPannel(4, 2, 2, force_chart, force_panel, "force"));
            
            panel_tab3.setLayout(new BorderLayout());
            panel_tab3.add(setUpChartPannel(2, 2, 1, moment_chart, moment_panel, "moment"));
    
            custom_initComponents();
            
            tab_container.addTab("Load Cell", jPanel1);
            tab_container.getComponent(0).setBackground(new Color(52, 51, 51));
            tab_container.addTab("Force", jPanel2);
            tab_container.getComponent(1).setBackground(new Color(52, 51, 51));
            tab_container.addTab("Moment", jPanel3);
            tab_container.getComponent(2).setBackground(new Color(52, 51, 51));
            
        }else if(identifier.equalsIgnoreCase("identifier_exp2emg")){
            
            setTitle("Exp2");
            
            panel_tab1.setLayout(new BorderLayout());
            panel_tab1.add(setUpChartPannel(1, 1, 1, chart, panel, "emg"));
            
            custom_initComponents();
            
            tab_container.addTab("EMG", jPanel1);
            tab_container.getComponent(0).setBackground(new Color(52, 51, 51));
            
        }else if(identifier.equalsIgnoreCase("identifier_exp3fp")){
            
            setTitle("Exp3");
            
            panel_tab1.setLayout(new BorderLayout());
            panel_tab1.add(setUpChartPannel(3, 2, 2, chart, panel, "load_cell"));
            
            
            panel_tab2.setLayout(new BorderLayout());
            panel_tab2.add(setUpChartPannel(3, 2, 2, force_chart, force_panel, "force_fp"));
            
            panel_tab3.setLayout(new BorderLayout());
            panel_tab3.add(setUpChartPannel(3, 2, 2, moment_chart, moment_panel, "moment_fp"));
    
            custom_initComponents();
            
            tab_container.addTab("Load Cell", jPanel1);
            tab_container.getComponent(0).setBackground(new Color(52, 51, 51));
            tab_container.addTab("Force", jPanel2);
            tab_container.getComponent(1).setBackground(new Color(52, 51, 51));
            tab_container.addTab("Moment", jPanel3);
            tab_container.getComponent(2).setBackground(new Color(52, 51, 51));
            
        }else if(identifier.equalsIgnoreCase("identifier_exp3emg")){
            
            setTitle("Exp3");
            panel_tab1.setLayout(new BorderLayout());
            panel_tab1.add(setUpChartPannel(1, 1, 1, chart, panel,"emg"));
            
            custom_initComponents();
            
            tab_container.addTab("EMG", jPanel1);
            tab_container.getComponent(0).setBackground(new Color(52, 51, 51));
        }
        
        
        pack();
        setVisible(true);
            
    }
    
    public JPanel setUpChartPannel(int matrix_element, int rows, int coloumns, 
            XYChart[] chart, JPanel[] panel, String identifier){
        
        int index = 1;
        
        while(index <= matrix_element){
            
            ArrayList<Integer> xinit = new ArrayList();
            ArrayList<Double> yinit = new ArrayList();
            xinit.add(0);
            yinit.add(0.0);

            switch(identifier){
                case "load_cell" : chart[index-1] = new XYChartBuilder().title("load cell-"+index)
                        .xAxisTitle("time(in sec)").yAxisTitle("Load Cell Reading").build();
                        chart[index-1].addSeries("load cell", xinit, yinit)
                                .setMarkerColor(new Color(52, 51, 51));break;
                case "emg" : chart[index-1] = new XYChartBuilder().title("Emg")
                        .xAxisTitle("time(in sec)").yAxisTitle("Emg Reading").build();
                        chart[index-1].addSeries("Emg", xinit, yinit)
                                .setMarkerColor(new Color(52, 51, 51));break;
                case "force" :                     
                    if(index == 1){
                    chart[index-1] = new XYChartBuilder().title("Force X(Front Block)")
                        .xAxisTitle("time(in sec)").yAxisTitle("Force(in Kg)").build();
                        chart[index-1].addSeries("Force X Front", xinit, yinit)
                                .setMarkerColor(new Color(52, 51, 51));break;
                    }else if(index == 2){
                        chart[index-1] = new XYChartBuilder().title("Force Z(Front Block)")
                        .xAxisTitle("time(in sec)").yAxisTitle("Force(in Kg)").build();
                        chart[index-1].addSeries("Force Z Front", xinit, yinit)
                                .setMarkerColor(new Color(52, 51, 51));break;
                    }else if(index == 3){
                        chart[index-1] = new XYChartBuilder().title("Force X(Rear Block)")
                        .xAxisTitle("time(in sec)").yAxisTitle("Force(in Kg)").build();
                        chart[index-1].addSeries("Force X Rear", xinit, yinit)
                                .setMarkerColor(new Color(52, 51, 51));break;
                    }else if(index == 4){
                        chart[index-1] = new XYChartBuilder().title("Force Z(Rear Block)")
                        .xAxisTitle("time(in sec)").yAxisTitle("Force(in Kg)").build();
                        chart[index-1].addSeries("Force Z Rear", xinit, yinit)
                                .setMarkerColor(new Color(52, 51, 51));break;
                    }
                    
                case "force_fp" :  if(index == 1){
                    chart[index-1] = new XYChartBuilder().title("Force X")
                        .xAxisTitle("time(in sec)").yAxisTitle("Force(in Kg)").build();
                        chart[index-1].addSeries("Force X", xinit, yinit)
                                .setMarkerColor(new Color(52, 51, 51));break;
                    }else if(index == 2){
                        chart[index-1] = new XYChartBuilder().title("Force Y")
                        .xAxisTitle("time(in sec)").yAxisTitle("Force(in Kg)").build();
                        chart[index-1].addSeries("Force Y", xinit, yinit)
                                .setMarkerColor(new Color(52, 51, 51));break;
                    }else if(index == 3){
                        chart[index-1] = new XYChartBuilder().title("Force Z")
                        .xAxisTitle("time(in sec)").yAxisTitle("Force(in Kg)").build();
                        chart[index-1].addSeries("Force Z", xinit, yinit)
                                .setMarkerColor(new Color(52, 51, 51));break;
                    }
                
                case "moment" :  if(index == 1){
                    chart[index-1] = new XYChartBuilder().title("Moment Y(Front Block)")
                        .xAxisTitle("time(in sec)").yAxisTitle("Moment(in Kg-cm)").build();
                        chart[index-1].addSeries("Moment Y Front", xinit, yinit)
                                .setMarkerColor(new Color(52, 51, 51));break;
                    }else if(index == 2){
                        chart[index-1] = new XYChartBuilder().title("Moment Y(Rear Block)")
                        .xAxisTitle("time(in sec)").yAxisTitle("Moment(in Kg-cm)").build();
                        chart[index-1].addSeries("Moment Y Rear", xinit, yinit)
                                .setMarkerColor(new Color(52, 51, 51));break;
                    }
                
                case "moment_fp" :  if(index == 1){
                    chart[index-1] = new XYChartBuilder().title("Moment X")
                        .xAxisTitle("time(in sec)").yAxisTitle("Moment(in Kg-cm)").build();
                        chart[index-1].addSeries("Moment X", xinit, yinit)
                                .setMarkerColor(new Color(52, 51, 51));break;
                    }else if(index == 2){
                        chart[index-1] = new XYChartBuilder().title("Moment Y")
                        .xAxisTitle("time(in sec)").yAxisTitle("Moment(in Kg-cm)").build();
                        chart[index-1].addSeries("Moment Y", xinit, yinit)
                                .setMarkerColor(new Color(52, 51, 51));break;
                    }else if(index == 3){
                        chart[index-1] = new XYChartBuilder().title("Moment Z")
                        .xAxisTitle("time(in sec)").yAxisTitle("Moment(in Kg-cm)").build();
                        chart[index-1].addSeries("Moment Z", xinit, yinit)
                                .setMarkerColor(new Color(52, 51, 51));break;
                    }   
            
                default : chart[index-1] = new XYChartBuilder().title("Raw Sensor Data"+index).
                        xAxisTitle("time").yAxisTitle("Sensor Reading").build();
                        chart[index-1].addSeries("sensor", xinit, yinit)
                                .setMarkerColor(new Color(52, 51, 51));break;

            }
            //chart[index-1] = new XYChartBuilder().title("Raw Sensor Data"+index).xAxisTitle("time").yAxisTitle("Sensor Reading").build();
            chart[index-1].getStyler().setMarkerSize(2);//radius of the marker
            chart[index-1].getStyler().setChartBackgroundColor(new Color(52, 51, 51));
            chart[index-1].getStyler().setChartFontColor(new Color(204, 255, 0));
            chart[index-1].getStyler().setLegendPosition(LegendPosition.InsideNE);
            //chart[index-1].getStyler().setLegendPosition(LegendPosition.InsideSW);
            chart[index-1].getStyler().setAxisTickLabelsColor(Color.white);
            //chart[index-1].getStyler().setYAxisMax(50.0);
            //chart[index-1].getStyler().setYAxisMin(-50.0);
                    
            panel[index-1] = new XChartPanel<XYChart>(chart[index-1]);

            panel[index-1].setBackground(Color.WHITE);
            
            panel[index-1].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    if(e.getClickCount()==2){
                        
                        int i = 0;
                        while(((JPanel)e.getComponent())!=panel[i]){
                            i++;
                            if(i==matrix_element)break;
                        }
                        
                        JLabel label = null;
                        
                        JPanel root = ((JPanel)(((JPanel)(((JPanel)(((JPanel)e.getComponent()).
                                getParent())).getParent())).getParent()));
                        
                        if(root == panel_tab1){
                            label = back_tab1;
                        }else if(root == panel_tab2){
                            label = back_tab2;
                        }else if(root == panel_tab3){
                            label = back_tab3;
                        }

                        label.setVisible(true);
                        
                        System.out.println("index selected"+i);
                        
                            displayPopUpChart((JPanel)e.getComponent(),
                                (JPanel)((JPanel)e.getComponent()).getParent(),
                                 i, matrix_element, label);
                    
                    }

                }});

            index++;
        }
        
        return setUpGridLayout(matrix_element, rows, coloumns, chart, panel);

    }
    
    public JPanel setUpGridLayout(int matrix_element, int rows, int coloumns, XYChart[] chart, JPanel[] panel){
        
        int index = 0;
        
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());
        JPanel jpanel = new JPanel();
        jpanel.setLayout(new GridLayout(rows, coloumns));
            
        while(index < matrix_element){
                
            jpanel.setBackground(chart[index].getStyler().getChartBackgroundColor());
            //panel[index].setBackground(Color.red);
            jpanel.add(panel[index]);
                
            index++;
                
        }
           
        parentPanel.add(jpanel,BorderLayout.CENTER);
        
        return parentPanel;
        
    }
    
    public void displayPopUpChart(JPanel jpanel, JPanel rootPanel, int index, int matrix_element, JLabel jlabel){
        
        rootPanel.setVisible(false);
        (((JPanel)rootPanel).getParent()).add(jpanel);
        
        jlabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    
                    if(e.getClickCount() == 1){
                        
                        jlabel.setVisible(false);
                        rootPanel.add(jpanel, index);
                        rootPanel.setVisible(true);
                    }
                        
                }});

         
       }
    
    
    public void plotData(ArrayList<Double> time, ArrayList<Double> val,
            int index, String series){
        
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

        @Override
        public void run() {

          chart[index].updateXYSeries(series, time, val, null);
          panel[index].revalidate();
          panel[index].repaint();
        
        }
      });
        
    }
    
    
    
    ArrayList<Double> x_force_val_front = new ArrayList();
    ArrayList<Double> z_force_val_front = new ArrayList();
    ArrayList<Double> x_force_val_rear = new ArrayList();
    ArrayList<Double> z_force_val_rear = new ArrayList();
    
    ArrayList<Double> x_force = new ArrayList();
    ArrayList<Double> y_force = new ArrayList();
    ArrayList<Double> z_force = new ArrayList();
    
    ArrayList<Double> moment_val_front = new ArrayList();
    ArrayList<Double> moment_val_rear = new ArrayList();
    
    ArrayList<Double> x_moment = new ArrayList();
    ArrayList<Double> y_moment = new ArrayList();
    ArrayList<Double> z_moment = new ArrayList();
    
    double initial_time = -1;
    double initial_timestamp = -1;
    
    
    public void plotForce(ArrayList<Double> time, ArrayList<Double> val,
            int index, String series){
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

        @Override
        public void run() {

          force_chart[index].updateXYSeries(series, time, val, null);
          force_panel[index].revalidate();
          force_panel[index].repaint();
        
        }
      });
        
    }
    
    public void plotMoment(ArrayList<Double> time, ArrayList<Double> val,
            int index, String series){
  
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

        @Override
        public void run() {

           moment_chart[index].updateXYSeries(series, time, val, null);
           moment_panel[index].revalidate();
           moment_panel[index].repaint();
        
        }
      });
        
    }
    
    
    public Long getTimeStamp(){
        
        long epoch = 0;
                
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date date = new Date(); 
        System.out.println(dateFormat.format(date));
        
        String d = dateFormat.format(date);
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        
        try {
            Date dat = df.parse(d);
            epoch = date.getTime();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
        return epoch;
        
    }
    
    
    ArrayList<Double> temp_arrList_time = new ArrayList();
    
    ArrayList<Double> temp_arrList_val = new ArrayList<>();
    
    boolean flag_start_race = false, flag_start_time = false, flag_end_time = false,
            flag_reaction_time = false;
    
    
    
    public class reactiontime extends Thread{
        
        @Override
        public void run(){
            getReactiontime();
        }
        
    }
    
    
    public void getReactiontime(){
        
        int i=0;
        
        double max_time = 0, prev_val = Double.MIN_VALUE;
                
        double max = Double.MIN_VALUE;
        for(i=0; i<temp_arrList_val.size(); i++){
            if(temp_arrList_val.get(i) > max){
                max = temp_arrList_val.get(i);
                max_time = temp_arrList_time.get(i);
            }
        }
        
        System.out.println("MAX VAL:"+ max+ "max_time" + max_time);
        
        max = 0.05*max;
        
        double distance = Math.abs(temp_arrList_val.get(0) - max);
            int idx = 0;
            for(int c = 1; c < temp_arrList_val.size() ; c++){
                
                if(prev_val > temp_arrList_val.get(c)){
                    
                    if(prev_val > max){
                             
                        break;
                        
                    }
                }
                
                prev_val = temp_arrList_val.get(c);
                        
                if(temp_arrList_time.get(c) > max_time){
                  
                    break;
                    
                }
                
                double cdistance = Math.abs(temp_arrList_val.get(c) - max);
                if(cdistance < distance){
                    idx = c;
                    distance = cdistance;
                    }
            }
        
            reaction_time.setText("Reaction time: "+ 
                    ((double)Math.round((temp_arrList_time.get(idx) - race_start_x_point) * 1000d) / 1000d) +"s");
            
            System.out.println("Reaction point:"+ idx + "time" + temp_arrList_time.get(idx));
            
            System.out.println("Reaction time: "+
                    ((double)Math.round((temp_arrList_time.get(idx) - race_start_x_point) * 1000d) / 1000d) +"s");
            
    }
    
    public void plotGraph(String message){
           
        if(message.toString().split(":")[0].equalsIgnoreCase("identifier_exp3fp")){
        
                if(message.toString().split(":")[1].equalsIgnoreCase("mac_id")){
                    
                    //device mac id
                    
                }else{                
                    
                    if(initial_time == -1){
                        initial_time = Double.parseDouble(message.toString().split(":")[4]+""); 
                    }
            
                   double time = (Double.parseDouble(message.toString().split(":")[4]+"")
                           -initial_time)/1000;
            
                    //xAxis.add(Double.parseDouble(message.toString().split(":")[4]+""));

                    if(time>=0 && Double.parseDouble(message.toString().split(":")[4]+"")<900000000){
                        
                        xAxis.add(time);

                        yAxis_sensor1.add(Double.parseDouble(message.toString().split(":")[1]+""));

                        yAxis_sensor2.add(Double.parseDouble(message.toString().split(":")[2]+""));

                        yAxis_sensor3.add(Double.parseDouble(message.toString().split(":")[3]+""));


                        double fx1 = 0;

                        double fx2 = Double.parseDouble(message.toString().split(":")[2]+"")*
                                        Math.sin(Math.toRadians(30))*Math.sin(Math.toRadians(60));; 

                        double fx3 = Double.parseDouble(message.toString().split(":")[3]+"")*
                                        Math.sin(Math.toRadians(30))*Math.sin(Math.toRadians(60));

                        double fz1 = Double.parseDouble(message.toString().split(":")[1]+"")*
                                        Math.cos(Math.toRadians(30));

                        double fz2 = Double.parseDouble(message.toString().split(":")[2]+"")*
                                        Math.cos(Math.toRadians(30));;

                        double fz3 = Double.parseDouble(message.toString().split(":")[3]+"")*
                                        Math.cos(Math.toRadians(30));

                        double fy1 = Double.parseDouble(message.toString().split(":")[1]+"")*
                                        Math.sin(Math.toRadians(30));

                        double fy2 = Double.parseDouble(message.toString().split(":")[2]+"")*
                                        Math.sin(Math.toRadians(30))*Math.sin(Math.toRadians(60));

                        double fy3 = Double.parseDouble(message.toString().split(":")[3]+"")*
                                        Math.sin(Math.toRadians(30))*Math.sin(Math.toRadians(60));;


                        x_force.add(fx2 - fx3);

                        y_force.add(fy1 - (fy2 + fy3));

                        z_force.add(fz1 + fz2 + fz3);

                        x_moment.add((fx2 - fx3)*11);

                        y_moment.add((fy1 - (fy2 + fy3))*11);

                        z_moment.add( fx2*4*Math.cos(Math.toRadians(60)) - 
                                fx3*4*Math.cos(Math.toRadians(60)) - fy2*4*Math.sin(Math.toRadians(60))
                        + fy3*4*Math.sin(Math.toRadians(60)));


                        plotData(xAxis, yAxis_sensor1, 0, "load cell");

                        plotData(xAxis, yAxis_sensor2, 1, "load cell");

                        plotData(xAxis, yAxis_sensor3, 2, "load cell");


                        plotForce(xAxis, x_force, 0, "Force X");

                        plotForce(xAxis, y_force, 1, "Force Y");

                        plotForce(xAxis, z_force, 2, "Force Z");


                        plotMoment(xAxis, x_moment, 0, "Moment X");

                        plotMoment(xAxis, y_moment, 1, "Moment Y");

                        plotMoment(xAxis, z_moment, 2, "Moment Z");

                    //process message and plot
                    }
                }
            }
        
        else if(message.toString().split(":")[0].equalsIgnoreCase("identifier_exp2emg")){
        
                if(message.toString().split(":")[1].equalsIgnoreCase("start_time")){
                    
                    if(!flag_start_time){
                        
                        drawLine("Start time",start_x_point,
                                yAxis_sensor1.get(yAxis_sensor1.size()-1), 0);
                    
                    }
                    
                    flag_start_time = true;
                    
                }else if(message.toString().split(":")[1].equalsIgnoreCase("end_time") ){
                    
                    if(!flag_end_time){
                       drawLine("End time",end_x_point,
                               yAxis_sensor1.get(yAxis_sensor1.size()-1),0);
                    }
                    
                    flag_end_time = true;
                    
                }
                else if(message.toString().split(":")[1].equalsIgnoreCase("start_race")){
                    
                    if(!flag_start_race){
                        drawLine("Start race",race_start_x_point,
                                yAxis_sensor1.get(yAxis_sensor1.size()-1),0);
                    }
                    
                    flag_start_race = true;
                    
                }else if(message.toString().split(":")[1].equalsIgnoreCase("mac_id")){
                    
                    //device mac id
                    
                }else{
                    
                    //xAxis.add(Double.parseDouble(message.toString().split(":")[2]+""));

                    if(initial_time == -1){
                        initial_time = Double.parseDouble(message.toString().split(":")[2]+""); 
                    }
            
                   double time = (Double.parseDouble(message.toString().split(":")[2]+"")
                           -initial_time)/1000;
                   
                   
                   if(flag_start_race){
                        
                       if(time - race_start_x_point > 5){
                           
                           reaction_time.setVisible(true);
                           
                           (new reactiontime()).start();
                           
                           flag_reaction_time = true;
                                   
                       }else{
                           temp_arrList_time.add(time);
                           
                           temp_arrList_val.add(Double.parseDouble(message.toString().split(":")[1]+""));
                       }
                       
                    }else{
                        race_start_x_point = time; 
                    }
                   
                   if(flag_start_time){
                       
                       start_time.setVisible(true);
                       
                       start_time.setText("Start time:"+ start_x_point + "s");
                              
                   }else{
                       start_x_point =  time;
                   }
                   
                   if(flag_end_time){
                       
                       
                       end_time.setVisible(true);
                       
                       end_time.setText("End time:"+ end_x_point + "s");
                       
                       total_time.setVisible(true);
                       
                       total_time.setText("Total time:"+ (end_x_point - start_x_point) + "s");
                       
                   }else{
                       
                       end_x_point = time;
                   }
                   
            
                   if(time>=0 && Double.parseDouble(message.toString().split(":")[2]+"")<900000000){
                                        
                        xAxis.add(time);

                        //race_start_x_point = time; 
                        //end_x_point = time; 
                        //start_x_point =  time;

                        yAxis_sensor1.add(Double.parseDouble(message.toString().split(":")[1]+""));

                        //start_x_point =  Double.parseDouble(message.toString().split(":")[2]);

                        plotData(xAxis, yAxis_sensor1, 0, "Emg");
                    
                   }
                }
            }
        
        else if(message.toString().split(":")[0].equalsIgnoreCase("identifier_exp1lc")){
        
                if(message.toString().split(":")[1].equalsIgnoreCase("mac_id")){
                    
                    //device mac id
                    
                }else{
                    
                    //xAxis.add(Double.parseDouble(message.toString().split(":")[3]+""));

                    if(initial_time == -1){
                        initial_time = Double.parseDouble(message.toString().split(":")[3]+""); 
                    }
            
                   double time = (Double.parseDouble(message.toString().split(":")[3]+"")
                           -initial_time)/1000;
            
                   if(time>=0 && Double.parseDouble(message.toString().split(":")[4]+"")<900000000){
                    
                        xAxis.add(time);

                        yAxis_sensor1.add(Double.parseDouble(message.toString().split(":")[1]+""));

                        yAxis_sensor2.add(Double.parseDouble(message.toString().split(":")[2]+""));

                        x_force_val_front.add(
                                Double.parseDouble(message.toString().split(":")[1]+"")*
                                        Math.cos(Math.toRadians(front_block_angle)));

                        z_force_val_front.add(
                                Double.parseDouble(message.toString().split(":")[1]+"")*
                                        Math.sin(Math.toRadians(front_block_angle)));

                        x_force_val_rear.add(
                                Double.parseDouble(message.toString().split(":")[2]+"")*
                                        Math.cos(Math.toRadians(rear_block_angle)));

                        z_force_val_rear.add(
                                Double.parseDouble(message.toString().split(":")[2]+"")*
                                        Math.sin(Math.toRadians(rear_block_angle)));

                        moment_val_front.add(Double.parseDouble(message.toString().split(":")[1]+"")*DIST_FRONT);

                        moment_val_rear.add(Double.parseDouble(message.toString().split(":")[2]+"")*DIST_REAR);


                        plotData(xAxis, yAxis_sensor1, 0, "load cell");

                        plotData(xAxis, yAxis_sensor2, 1, "load cell");

                        plotForce(xAxis, x_force_val_front, 0, "Force X Front");

                        plotForce(xAxis, z_force_val_front, 1, "Force Z Front");

                        plotForce(xAxis, x_force_val_rear, 2, "Force X Rear");

                        plotForce(xAxis, z_force_val_rear, 3, "Force Z Rear");

                        plotMoment(xAxis, moment_val_front, 0, "Moment Y Front");

                        plotMoment(xAxis, moment_val_rear, 1, "Moment Y Rear");

                    //process message and plot
                   }
                    
                }
            }
        
        else if(message.toString().split(":")[0].equalsIgnoreCase("identifier_exp2lc")){
            
            
                if(message.toString().split(":")[1].equalsIgnoreCase("start_time")){                  
                    
                    if(!flag_start_time){
                        drawLine("Start time1",start_x_point,
                                yAxis_sensor1.get(yAxis_sensor1.size()-1),0);

                        drawLine("Start time2",start_x_point,
                                yAxis_sensor2.get(yAxis_sensor2.size()-1),1);
                    }
                    
                    flag_start_time = true;
                    
                }else if(message.toString().split(":")[1].equalsIgnoreCase("end_time")){
                    
                    
                    if(!flag_end_time){
                      
                        drawLine("End time1",end_x_point,
                                yAxis_sensor1.get(yAxis_sensor1.size()-1),0);

                        drawLine("End time2",end_x_point,
                                yAxis_sensor2.get(yAxis_sensor2.size()-1),1);
                    
                    }
                    
                    flag_end_time = true;
                    
                }
                else if(message.toString().split(":")[1].equalsIgnoreCase("start_race")/*&& !flag_start_race*/){
                    
                    
                    if( !flag_start_race) { 
                        
                        drawLine("Start race1",race_start_x_point,
                                yAxis_sensor1.get(yAxis_sensor1.size()-1),0);

                        drawLine("Start race2",race_start_x_point,
                                yAxis_sensor2.get(yAxis_sensor2.size()-1),1);
                        
                        temp_arrList_time = new ArrayList<>();
                        
                    }
                    
                    flag_start_race = true;
                    
                    //race started
                    
                }else if(message.toString().split(":")[1].equalsIgnoreCase("mac_id")){
                    
                    //device mac id
                    
                }else{
                    
                    
                    if(initial_time == -1){
                        initial_time = Double.parseDouble(message.toString().split(":")[3]+""); 
                    }
            
                   double time = (Double.parseDouble(message.toString().split(":")[3]+"")
                           -initial_time)/1000;
            
                   
                   
                   if(flag_start_race){
                        
                       if(time - race_start_x_point > 5){
                           
                       }else{
                          
                       }
                       
                    }else{
                        race_start_x_point = time; 
                    }
                   
                   if(flag_start_time){
                       
                       start_time.setVisible(true);
                       
                       start_time.setText("Start time:"+ start_x_point + "s");
                              
                   }else{
                       start_x_point =  time;
                   }
                   
                   if(flag_end_time){
                       
                       
                       end_time.setVisible(true);
                       
                       end_time.setText("End time:"+ end_x_point + "s");
                       
                       total_time.setVisible(true);
                       
                       total_time.setText("Total time:"+ (end_x_point - start_x_point) + "s");
                       
                   }else{
                       
                       end_x_point = time;
                   }
                   
                   
                   if(time>=0 && Double.parseDouble(message.toString().split(":")[3]+"") < 900000000){
                       
                  
                    xAxis.add(time);
                    
                    //xAxis.add(Double.parseDouble(message.toString().split(":")[3]+""));

                    yAxis_sensor1.add(Double.parseDouble(message.toString().split(":")[1]+""));
                    
                    yAxis_sensor2.add(Double.parseDouble(message.toString().split(":")[2]+""));
                    
                    x_force_val_front.add(
                            Double.parseDouble(message.toString().split(":")[1]+"")*
                                    Math.cos(Math.toRadians(front_block_angle)));
                    
                    z_force_val_front.add(
                            Double.parseDouble(message.toString().split(":")[1]+"")*
                                    Math.sin(Math.toRadians(front_block_angle)));
                    
                    x_force_val_rear.add(
                            Double.parseDouble(message.toString().split(":")[2]+"")*
                                    Math.cos(Math.toRadians(rear_block_angle)));
                    
                    z_force_val_rear.add(
                            Double.parseDouble(message.toString().split(":")[2]+"")*
                                    Math.sin(Math.toRadians(rear_block_angle)));
                    
                    moment_val_front.add(Double.parseDouble(message.toString().split(":")[1]+"")*DIST_FRONT);

                    moment_val_rear.add(Double.parseDouble(message.toString().split(":")[2]+"")*DIST_REAR);

                    plotData(xAxis, yAxis_sensor1, 0, "load cell");
                    
                    plotData(xAxis, yAxis_sensor2, 1, "load cell");
                    
                    plotForce(xAxis, x_force_val_front, 0, "Force X Front");
                    
                    plotForce(xAxis, z_force_val_front, 1, "Force Z Front");
                    
                    plotForce(xAxis, x_force_val_rear, 2, "Force X Rear");
                    
                    plotForce(xAxis, z_force_val_rear, 3, "Force Z Rear");
                    
                    plotMoment(xAxis, moment_val_front, 0, "Moment Y Front");
                    
                    plotMoment(xAxis, moment_val_rear, 1, "Moment Y Rear");
                    
                    //process message and plot
                    
                   }
                }
            }
        
        else if(message.toString().split(":")[0].equalsIgnoreCase("identifier_exp3emg")){
        
                if(message.toString().split(":")[1].equalsIgnoreCase("mac_id")){
                    
                    //device mac id
                    
                }else{
                    
                    //xAxis.add(Double.parseDouble(message.toString().split(":")[2]+""));

                    if(initial_time == -1){
                        initial_time = Double.parseDouble(message.toString().split(":")[2]+""); 
                    }
            
                   double time = (Double.parseDouble(message.toString().split(":")[2]+"")
                           -initial_time)/1000;
            
                    xAxis.add(time);
                            
                    yAxis_sensor1.add(Double.parseDouble(message.toString().split(":")[1]+""));
                    
                    start_x_point =  Double.parseDouble(message.toString().split(":")[2]);
                    
                    plotData(xAxis, yAxis_sensor1, 0, "Emg");
                    
                }
            }
        
    }
    
    
    public void plotHistoryGraph(String message){
           
        //System.out.println("Received message at GraphPlotter: " + message);
  
          
        if(message.toString().split(":")[0].equalsIgnoreCase("identifier_exp3fp")){
        
                if(message.toString().split(":")[1].equalsIgnoreCase("mac_id")){
                    
                    //device mac id
                    
                }else{                    
                    //xAxis.add(Double.parseDouble(message.toString().split(":")[4]+""));

                    if(initial_time == -1){
                        initial_time = Double.parseDouble(message.toString().split(":")[4]+""); 
                    }
            
                   double time = (Double.parseDouble(message.toString().split(":")[4]+"")
                           -initial_time)/1000;
            
                   
                   if(time>=0 && Double.parseDouble(message.toString().split(":")[4]+"")<900000){
                        
                        xAxis.add(time);

                        yAxis_sensor1.add(Double.parseDouble(message.toString().split(":")[1]+""));

                        yAxis_sensor2.add(Double.parseDouble(message.toString().split(":")[2]+""));

                        yAxis_sensor3.add(Double.parseDouble(message.toString().split(":")[3]+""));


                        double fx1 = 0;

                        double fx2 = Double.parseDouble(message.toString().split(":")[2]+"")*
                                        Math.sin(Math.toRadians(30))*Math.sin(Math.toRadians(60));; 

                        double fx3 = Double.parseDouble(message.toString().split(":")[3]+"")*
                                        Math.sin(Math.toRadians(30))*Math.sin(Math.toRadians(60));

                        double fz1 = Double.parseDouble(message.toString().split(":")[1]+"")*
                                        Math.cos(Math.toRadians(30));

                        double fz2 = Double.parseDouble(message.toString().split(":")[2]+"")*
                                        Math.cos(Math.toRadians(30));;

                        double fz3 = Double.parseDouble(message.toString().split(":")[3]+"")*
                                        Math.cos(Math.toRadians(30));

                        double fy1 = Double.parseDouble(message.toString().split(":")[1]+"")*
                                        Math.sin(Math.toRadians(30));

                        double fy2 = Double.parseDouble(message.toString().split(":")[2]+"")*
                                        Math.sin(Math.toRadians(30))*Math.sin(Math.toRadians(60));

                        double fy3 = Double.parseDouble(message.toString().split(":")[3]+"")*
                                        Math.sin(Math.toRadians(30))*Math.sin(Math.toRadians(60));;


                        x_force.add(fx2 - fx3);

                        y_force.add(fy1 - (fy2 + fy3));

                        z_force.add(fz1 + fz2 + fz3);

                        x_moment.add((fx2 - fx3)*11);

                        y_moment.add((fy1 - (fy2 + fy3))*11);

                        z_moment.add( fx2*4*Math.cos(Math.toRadians(60)) - 
                                fx3*4*Math.cos(Math.toRadians(60)) - fy2*4*Math.sin(Math.toRadians(60))
                        + fy3*4*Math.sin(Math.toRadians(60)));


                        plotData(xAxis, yAxis_sensor1, 0, "load cell");

                        plotData(xAxis, yAxis_sensor2, 1, "load cell");

                        plotData(xAxis, yAxis_sensor3, 2, "load cell");


                        plotForce(xAxis, x_force, 0, "Force X");

                        plotForce(xAxis, y_force, 1, "Force Y");

                        plotForce(xAxis, z_force, 2, "Force Z");


                        plotMoment(xAxis, x_moment, 0, "Moment X");

                        plotMoment(xAxis, y_moment, 1, "Moment Y");

                        plotMoment(xAxis, z_moment, 2, "Moment Z");

                        //process message and plot
                   }   
                }
            }
        
        else if(message.toString().split(":")[0].equalsIgnoreCase("identifier_exp2emg")){
        
                if(message.toString().split(":")[1].equalsIgnoreCase("start_time")){
                    
                    if(!flag_start_time)
                        drawLine("Start time",start_x_point,
                                yAxis_sensor1.get(yAxis_sensor1.size()-1),0);
                    
                    flag_start_time = true;
                    
                }else if(message.toString().split(":")[1].equalsIgnoreCase("end_time")){
                    
                    if(!flag_end_time)
                        drawLine("End time",end_x_point,
                                yAxis_sensor1.get(yAxis_sensor1.size()-1),0);
                    
                    flag_end_time = true;
                    
                }
                else if(message.toString().split(":")[1].equalsIgnoreCase("start_race")){
                    
                    if(!flag_start_race)
                        drawLine("Start race",race_start_x_point,
                                yAxis_sensor1.get(yAxis_sensor1.size()-1),0);
                    
                    flag_start_race = true;
                    
                }else if(message.toString().split(":")[1].equalsIgnoreCase("mac_id")){
                    
                    //device mac id
                    
                }else{
                    
                    //xAxis.add(Double.parseDouble(message.toString().split(":")[2]+""));

                    if(initial_time == -1){
                        initial_time = Double.parseDouble(message.toString().split(":")[2]+""); 
                    }
            
                   double time = (Double.parseDouble(message.toString().split(":")[2]+"")
                           -initial_time)/1000;
            
                   
                   
                   if(flag_start_race){
                        
                       if(time - race_start_x_point > 5){
                           
                           if(!flag_reaction_time){
                               
                               reaction_time.setVisible(true);
        
                               (new reactiontime()).start();
                               flag_reaction_time = true;
                           }
                                   
                       }else{
                           temp_arrList_time.add(time);
                           
                           temp_arrList_val.add(Double.parseDouble(message.toString().split(":")[1]+""));
                       }
                       
                    }else{
                        race_start_x_point = time; 
                    }
                   
                   if(flag_start_time){
                       
                       start_time.setVisible(true);
                       
                       start_time.setText("Start time:"+ start_x_point + "s");
                              
                   }else{
                       start_x_point =  time;
                   }
                   
                   if(flag_end_time){
                       
                       
                       end_time.setVisible(true);
                       
                       end_time.setText("End time:"+ end_x_point + "s");
                       
                       total_time.setVisible(true);
                       
                       total_time.setText("Total time:"+ (end_x_point - start_x_point) + "s");
                       
                   }else{
                       
                       end_x_point = time;
                   }
                   
                   
                   
                    xAxis.add(time);
                    
                    //race_start_x_point = time; 
                    //end_x_point = time; 
                    //start_x_point =  time;

                    yAxis_sensor1.add(Double.parseDouble(message.toString().split(":")[1]+""));
                    
                    //start_x_point =  Double.parseDouble(message.toString().split(":")[2]);
                    
                    plotData(xAxis, yAxis_sensor1, 0, "Emg");
                    
                }
            }
        
        else if(message.toString().split(":")[0].equalsIgnoreCase("identifier_exp1lc")){
        
                if(message.toString().split(":")[1].equalsIgnoreCase("mac_id")){
                    
                    //device mac id
                    
                }else{
                    
                    //xAxis.add(Double.parseDouble(message.toString().split(":")[3]+""));

                    if(initial_time == -1){
                        initial_time = Double.parseDouble(message.toString().split(":")[3]+""); 
                    }
            
                   double time = (Double.parseDouble(message.toString().split(":")[3]+"")
                           -initial_time)/1000;
            
                    xAxis.add(time);
                    
                    yAxis_sensor1.add(Double.parseDouble(message.toString().split(":")[1]+""));
                    
                    yAxis_sensor2.add(Double.parseDouble(message.toString().split(":")[2]+""));
                    
                    x_force_val_front.add(
                            Double.parseDouble(message.toString().split(":")[1]+"")*
                                    Math.cos(Math.toRadians(front_block_angle)));
                    
                    z_force_val_front.add(
                            Double.parseDouble(message.toString().split(":")[1]+"")*
                                    Math.sin(Math.toRadians(front_block_angle)));
                    
                    x_force_val_rear.add(
                            Double.parseDouble(message.toString().split(":")[2]+"")*
                                    Math.cos(Math.toRadians(rear_block_angle)));
                    
                    z_force_val_rear.add(
                            Double.parseDouble(message.toString().split(":")[2]+"")*
                                    Math.sin(Math.toRadians(rear_block_angle)));
                    
                    moment_val_front.add(Double.parseDouble(message.toString().split(":")[1]+"")*DIST_FRONT);

                    moment_val_rear.add(Double.parseDouble(message.toString().split(":")[2]+"")*DIST_REAR);

                    
                    plotData(xAxis, yAxis_sensor1, 0, "load cell");
                    
                    plotData(xAxis, yAxis_sensor2, 1, "load cell");
                    
                    plotForce(xAxis, x_force_val_front, 0, "Force X Front");
                    
                    plotForce(xAxis, z_force_val_front, 1, "Force Z Front");
                    
                    plotForce(xAxis, x_force_val_rear, 2, "Force X Rear");
                    
                    plotForce(xAxis, z_force_val_rear, 3, "Force Z Rear");
                    
                    plotMoment(xAxis, moment_val_front, 0, "Moment Y Front");
                    
                    plotMoment(xAxis, moment_val_rear, 1, "Moment Y Rear");
                    
                    //process message and plot
                    
                }
            }
        
        else if(message.toString().split(":")[0].equalsIgnoreCase("identifier_exp2lc")){
            
            
                if(message.toString().split(":")[1].equalsIgnoreCase("start_time")){                  
                    
                    if(!flag_start_time){
                        drawLine("Start time1",start_x_point,
                                yAxis_sensor1.get(yAxis_sensor1.size()-1),0);

                        drawLine("Start time2",start_x_point,
                                yAxis_sensor2.get(yAxis_sensor2.size()-1),1);
                    }
                    
                    flag_start_time = true;
                    
                }else if(message.toString().split(":")[1].equalsIgnoreCase("end_time")){
                    
                    
                    if(!flag_end_time){
                      
                        drawLine("End time1",end_x_point,
                                yAxis_sensor1.get(yAxis_sensor1.size()-1),0);

                        drawLine("End time2",end_x_point,
                                yAxis_sensor2.get(yAxis_sensor2.size()-1),1);
                    
                    }
                    
                    flag_end_time = true;
                    
                }
                else if(message.toString().split(":")[1].equalsIgnoreCase("start_race")/*&& !flag_start_race*/){
                    
                    
                    if( !flag_start_race) { 
                        
                        drawLine("Start race1",race_start_x_point,
                                yAxis_sensor1.get(yAxis_sensor1.size()-1),0);

                        drawLine("Start race2",race_start_x_point,
                                yAxis_sensor2.get(yAxis_sensor2.size()-1),1);
                    }
                    
                    flag_start_race = true;
                    
                    temp_arrList_time = new ArrayList<>();
                    //race started
                    
                }else if(message.toString().split(":")[1].equalsIgnoreCase("mac_id")){
                    
                    //device mac id
                    
                }else{
                    
                  
                    if(initial_time == -1){
                        initial_time = Double.parseDouble(message.toString().split(":")[3]+""); 
                    }
                    
                    
                    
                    double time = (Double.parseDouble(message.toString().split(":")[3]+"")
                           -initial_time)/1000;
                    
                    
                    if(flag_start_race){
                        
                       if(time - race_start_x_point > 5){
                           
                       }else{
                          
                       }
                       
                    }else{
                        race_start_x_point = time; 
                    }
                    
                    if(flag_start_time){
                       
                       start_time.setVisible(true);
                       
                       start_time.setText("Start time:"+ start_x_point + "s");
                              
                   }else{
                       start_x_point =  time;
                   }
                   
                   if(flag_end_time){
                                            
                       end_time.setVisible(true);
                       
                       end_time.setText("End time:"+ end_x_point + "s");
                       
                       total_time.setVisible(true);
                       
                       total_time.setText("Total time:"+ (end_x_point - start_x_point) + "s");
                       
                   }else{
                       
                       end_x_point = time;
                   }
                                      
            
                   
                   if(time>=0 && Double.parseDouble(message.toString().split(":")[3]+"")<900000){

                        xAxis.add(time);

                        //race_start_x_point = time; 
                        //end_x_point = time; 
                        //start_x_point =  time;
                        //xAxis.add(Double.parseDouble(message.toString().split(":")[3]+""));

                        yAxis_sensor1.add(Double.parseDouble(message.toString().split(":")[1]+""));

                        yAxis_sensor2.add(Double.parseDouble(message.toString().split(":")[2]+""));

                        x_force_val_front.add(
                                Double.parseDouble(message.toString().split(":")[1]+"")*
                                        Math.cos(Math.toRadians(front_block_angle)));

                        z_force_val_front.add(
                                Double.parseDouble(message.toString().split(":")[1]+"")*
                                        Math.sin(Math.toRadians(front_block_angle)));

                        x_force_val_rear.add(
                                Double.parseDouble(message.toString().split(":")[2]+"")*
                                        Math.cos(Math.toRadians(rear_block_angle)));

                        z_force_val_rear.add(
                                Double.parseDouble(message.toString().split(":")[2]+"")*
                                        Math.sin(Math.toRadians(rear_block_angle)));

                        moment_val_front.add(Double.parseDouble(message.toString().split(":")[1]+"")*DIST_FRONT);

                        moment_val_rear.add(Double.parseDouble(message.toString().split(":")[2]+"")*DIST_REAR);

                        plotData(xAxis, yAxis_sensor1, 0, "load cell");

                        plotData(xAxis, yAxis_sensor2, 1, "load cell");

                        plotForce(xAxis, x_force_val_front, 0, "Force X Front");

                        plotForce(xAxis, z_force_val_front, 1, "Force Z Front");

                        plotForce(xAxis, x_force_val_rear, 2, "Force X Rear");

                        plotForce(xAxis, z_force_val_rear, 3, "Force Z Rear");

                        plotMoment(xAxis, moment_val_front, 0, "Moment Y Front");

                        plotMoment(xAxis, moment_val_rear, 1, "Moment Y Rear");

                    //process message and plot
                   }
                }
            }
        
        else if(message.toString().split(":")[0].equalsIgnoreCase("identifier_exp3emg")){
        
                if(message.toString().split(":")[1].equalsIgnoreCase("mac_id")){
                    
                    //device mac id
                    
                }else{
                    
                    //xAxis.add(Double.parseDouble(message.toString().split(":")[2]+""));

                    if(initial_time == -1){
                        initial_time = Double.parseDouble(message.toString().split(":")[2]+""); 
                    }
            
                   double time = (Double.parseDouble(message.toString().split(":")[2]+"")
                           -initial_time)/1000;
            
                    xAxis.add(time);
                    
                    yAxis_sensor1.add(Double.parseDouble(message.toString().split(":")[1]+""));
                    
                    start_x_point =  Double.parseDouble(message.toString().split(":")[2]);
                    
                    plotData(xAxis, yAxis_sensor1, 0, "Emg");
                    
                }
            }
        
    }
    
    
    public void plotHistoryGraph(ArrayList<String> message){
        
        for(String ele: message){
            
            plotHistoryGraph(ele);
            
        }
        
    }
    
    
    public void drawLine(String series_name, double xpoint, double ypoint, int index){
        
        System.out.println("Called");
        
        this.start_x_point = xpoint;
        
        ArrayList<Double> x_point = new ArrayList();
        x_point.add(xpoint);
        x_point.add(xpoint);
        
        ArrayList<Double> y_point = new ArrayList();
        y_point.add(ypoint-5.0);
        y_point.add(ypoint + 5.0);
        
        int i = 0;
        
        //while(i<=index){
            
        XYSeries series = chart[index].addSeries(series_name, x_point, y_point);
        series.setMarker(SeriesMarkers.CIRCLE);
        series.setMarkerColor(Color.RED);
        
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

        @Override
        public void run() {

          panel[index].revalidate();
          panel[index].repaint();
        
        }
      });
   
    //}//
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
     private void initial_init(){
         
        graphPanel = new javax.swing.JPanel();
        tab_container = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        actionbar2 = new javax.swing.JPanel();
        back_tab1 = new javax.swing.JLabel();
        panel_tab1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        actionbar1 = new javax.swing.JPanel();
        back_tab2 = new javax.swing.JLabel();
        panel_tab2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        actionbar3 = new javax.swing.JPanel();
        back_tab3 = new javax.swing.JLabel();
        panel_tab3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        actionbar = new javax.swing.JPanel();
        back_tab4 = new javax.swing.JLabel();
        panel_tab4 = new javax.swing.JPanel();
        reaction_time = new javax.swing.JLabel();
        start_time = new javax.swing.JLabel();
        end_time = new javax.swing.JLabel();
        total_time = new javax.swing.JLabel();

        back_tab1.setVisible(false);
        back_tab2.setVisible(false);
        back_tab3.setVisible(false);
        back_tab4.setVisible(false);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        graphPanel.setBackground(new java.awt.Color(255, 255, 255));

        actionbar2.setBackground(new java.awt.Color(255, 255, 255));
        actionbar2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        back_tab1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back_tab1.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/back.jpg")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

        
        reaction_time.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        reaction_time.setText("Reaction Time");

        start_time.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        start_time.setText("Start Time");

        end_time.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        end_time.setText("End Time");

        total_time.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        total_time.setText("Total Time");

        
        javax.swing.GroupLayout actionbar2Layout = new javax.swing.GroupLayout(actionbar2);
        actionbar2.setLayout(actionbar2Layout);
        actionbar2Layout.setHorizontalGroup(
            actionbar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionbar2Layout.createSequentialGroup()
                .addComponent(back_tab1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(reaction_time)
                .addGap(61, 61, 61)
                .addComponent(start_time)
                .addGap(70, 70, 70)
                .addComponent(end_time)
                .addGap(52, 52, 52)
                .addComponent(total_time)
                .addContainerGap(76, Short.MAX_VALUE))
        );
        actionbar2Layout.setVerticalGroup(
            actionbar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back_tab1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, actionbar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(reaction_time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(start_time)
                .addComponent(end_time, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(total_time, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_tab1.setBackground(new java.awt.Color(255, 255, 255));
        panel_tab1.setForeground(new java.awt.Color(255, 255, 255));

        
        actionbar1.setBackground(new java.awt.Color(255, 255, 255));
        actionbar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        actionbar1.setForeground(new java.awt.Color(153, 153, 153));

        back_tab2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back_tab2.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/back.jpg")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

        javax.swing.GroupLayout actionbar1Layout = new javax.swing.GroupLayout(actionbar1);
        actionbar1.setLayout(actionbar1Layout);
        actionbar1Layout.setHorizontalGroup(
            actionbar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionbar1Layout.createSequentialGroup()
                .addComponent(back_tab2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 585, Short.MAX_VALUE))
        );
        actionbar1Layout.setVerticalGroup(
            actionbar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back_tab2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
        );

        panel_tab2.setBackground(new java.awt.Color(255, 255, 255));

        
        actionbar3.setBackground(new java.awt.Color(255, 255, 255));
        actionbar3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        back_tab3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back_tab3.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/back.jpg")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

        javax.swing.GroupLayout actionbar3Layout = new javax.swing.GroupLayout(actionbar3);
        actionbar3.setLayout(actionbar3Layout);
        actionbar3Layout.setHorizontalGroup(
            actionbar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionbar3Layout.createSequentialGroup()
                .addComponent(back_tab3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 585, Short.MAX_VALUE))
        );
        actionbar3Layout.setVerticalGroup(
            actionbar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back_tab3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panel_tab3.setBackground(new java.awt.Color(255, 255, 255));

        
        actionbar.setBackground(new java.awt.Color(255, 255, 255));
        actionbar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        back_tab4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back_tab4.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/back.jpg")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

        javax.swing.GroupLayout actionbarLayout = new javax.swing.GroupLayout(actionbar);
        actionbar.setLayout(actionbarLayout);
        actionbarLayout.setHorizontalGroup(
            actionbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionbarLayout.createSequentialGroup()
                .addComponent(back_tab4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 585, Short.MAX_VALUE))
        );
        actionbarLayout.setVerticalGroup(
            actionbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back_tab4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panel_tab4.setBackground(new java.awt.Color(255, 255, 255));

        
        javax.swing.GroupLayout graphPanelLayout = new javax.swing.GroupLayout(graphPanel);
        graphPanel.setLayout(graphPanelLayout);
        graphPanelLayout.setHorizontalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab_container)
        );
        graphPanelLayout.setVerticalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab_container)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(graphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(graphPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

     }
     
     private void custom_initComponents() {

        //
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(actionbar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_tab1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(actionbar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_tab1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        //tab_container.addTab("Tab1", jPanel1);
        
        //
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(actionbar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_tab2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(actionbar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_tab2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        //tab_container.addTab("Tab2", jPanel2);

        //
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(actionbar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_tab3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(actionbar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_tab3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        //tab_container.addTab("tab3", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(actionbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_tab4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(actionbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_tab4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        //tab_container.addTab("Tab4", jPanel4);

    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        graphPanel = new javax.swing.JPanel();
        tab_container = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        actionbar2 = new javax.swing.JPanel();
        back_tab1 = new javax.swing.JLabel();
        reaction_time = new javax.swing.JLabel();
        start_time = new javax.swing.JLabel();
        end_time = new javax.swing.JLabel();
        total_time = new javax.swing.JLabel();
        panel_tab1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        actionbar1 = new javax.swing.JPanel();
        back_tab2 = new javax.swing.JLabel();
        panel_tab2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        actionbar3 = new javax.swing.JPanel();
        back_tab3 = new javax.swing.JLabel();
        panel_tab3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        actionbar = new javax.swing.JPanel();
        back_tab4 = new javax.swing.JLabel();
        panel_tab4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        graphPanel.setBackground(new java.awt.Color(255, 255, 255));

        tab_container.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setForeground(new java.awt.Color(85, 83, 83));

        actionbar2.setBackground(new java.awt.Color(255, 255, 255));
        actionbar2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        back_tab1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back_tab1.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/back.jpg")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

        reaction_time.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        reaction_time.setText("Reaction Time");

        start_time.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        start_time.setText("Start Time");

        end_time.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        end_time.setText("End Time");

        total_time.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        total_time.setText("Total Time");

        javax.swing.GroupLayout actionbar2Layout = new javax.swing.GroupLayout(actionbar2);
        actionbar2.setLayout(actionbar2Layout);
        actionbar2Layout.setHorizontalGroup(
            actionbar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionbar2Layout.createSequentialGroup()
                .addComponent(back_tab1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(reaction_time)
                .addGap(61, 61, 61)
                .addComponent(start_time)
                .addGap(70, 70, 70)
                .addComponent(end_time)
                .addGap(52, 52, 52)
                .addComponent(total_time)
                .addContainerGap(76, Short.MAX_VALUE))
        );
        actionbar2Layout.setVerticalGroup(
            actionbar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back_tab1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, actionbar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(reaction_time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(start_time)
                .addComponent(end_time, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(total_time, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_tab1.setBackground(new java.awt.Color(255, 255, 255));
        panel_tab1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panel_tab1Layout = new javax.swing.GroupLayout(panel_tab1);
        panel_tab1.setLayout(panel_tab1Layout);
        panel_tab1Layout.setHorizontalGroup(
            panel_tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_tab1Layout.setVerticalGroup(
            panel_tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(actionbar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_tab1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(actionbar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_tab1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_container.addTab("Tab1", jPanel1);

        actionbar1.setBackground(new java.awt.Color(255, 255, 255));
        actionbar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        actionbar1.setForeground(new java.awt.Color(153, 153, 153));

        back_tab2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back_tab2.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/back.jpg")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

        javax.swing.GroupLayout actionbar1Layout = new javax.swing.GroupLayout(actionbar1);
        actionbar1.setLayout(actionbar1Layout);
        actionbar1Layout.setHorizontalGroup(
            actionbar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionbar1Layout.createSequentialGroup()
                .addComponent(back_tab2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 585, Short.MAX_VALUE))
        );
        actionbar1Layout.setVerticalGroup(
            actionbar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back_tab2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
        );

        panel_tab2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panel_tab2Layout = new javax.swing.GroupLayout(panel_tab2);
        panel_tab2.setLayout(panel_tab2Layout);
        panel_tab2Layout.setHorizontalGroup(
            panel_tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_tab2Layout.setVerticalGroup(
            panel_tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(actionbar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_tab2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(actionbar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_tab2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_container.addTab("Tab2", jPanel2);

        actionbar3.setBackground(new java.awt.Color(255, 255, 255));
        actionbar3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        back_tab3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back_tab3.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/back.jpg")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

        javax.swing.GroupLayout actionbar3Layout = new javax.swing.GroupLayout(actionbar3);
        actionbar3.setLayout(actionbar3Layout);
        actionbar3Layout.setHorizontalGroup(
            actionbar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionbar3Layout.createSequentialGroup()
                .addComponent(back_tab3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 585, Short.MAX_VALUE))
        );
        actionbar3Layout.setVerticalGroup(
            actionbar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back_tab3, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
        );

        panel_tab3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panel_tab3Layout = new javax.swing.GroupLayout(panel_tab3);
        panel_tab3.setLayout(panel_tab3Layout);
        panel_tab3Layout.setHorizontalGroup(
            panel_tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_tab3Layout.setVerticalGroup(
            panel_tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(actionbar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_tab3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(actionbar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_tab3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_container.addTab("tab3", jPanel3);

        actionbar.setBackground(new java.awt.Color(255, 255, 255));
        actionbar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        back_tab4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back_tab4.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/back.jpg")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

        javax.swing.GroupLayout actionbarLayout = new javax.swing.GroupLayout(actionbar);
        actionbar.setLayout(actionbarLayout);
        actionbarLayout.setHorizontalGroup(
            actionbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionbarLayout.createSequentialGroup()
                .addComponent(back_tab4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 585, Short.MAX_VALUE))
        );
        actionbarLayout.setVerticalGroup(
            actionbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back_tab4, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
        );

        panel_tab4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panel_tab4Layout = new javax.swing.GroupLayout(panel_tab4);
        panel_tab4.setLayout(panel_tab4Layout);
        panel_tab4Layout.setHorizontalGroup(
            panel_tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_tab4Layout.setVerticalGroup(
            panel_tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(actionbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_tab4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(actionbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_tab4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_container.addTab("Tab4", jPanel4);

        javax.swing.GroupLayout graphPanelLayout = new javax.swing.GroupLayout(graphPanel);
        graphPanel.setLayout(graphPanelLayout);
        graphPanelLayout.setHorizontalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab_container)
        );
        graphPanelLayout.setVerticalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab_container)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(graphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(graphPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GraphPlotterActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraphPlotterActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraphPlotterActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraphPlotterActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraphPlotterActivity().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionbar;
    private javax.swing.JPanel actionbar1;
    private javax.swing.JPanel actionbar2;
    private javax.swing.JPanel actionbar3;
    private javax.swing.JLabel back_tab1;
    private javax.swing.JLabel back_tab2;
    private javax.swing.JLabel back_tab3;
    private javax.swing.JLabel back_tab4;
    private javax.swing.JLabel end_time;
    private javax.swing.JPanel graphPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel panel_tab1;
    private javax.swing.JPanel panel_tab2;
    private javax.swing.JPanel panel_tab3;
    private javax.swing.JPanel panel_tab4;
    private javax.swing.JLabel reaction_time;
    private javax.swing.JLabel start_time;
    private javax.swing.JTabbedPane tab_container;
    private javax.swing.JLabel total_time;
    // End of variables declaration//GEN-END:variables
}
