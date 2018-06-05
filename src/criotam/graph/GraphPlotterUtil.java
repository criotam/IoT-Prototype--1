/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criotam.graph;

import com.mysql.cj.util.StringUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ScrollPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.SeriesMarkers;


/**
 *
 * @author AVINASH
 */
public class GraphPlotterUtil {
    
  
    public JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8;
    
    public JPanel[] panel = {panel1, panel2, panel3, panel4};
    
    public JPanel[] force_panel = {panel5, panel6, panel7, panel8};
    
    public XYChart chart1, chart2, chart3, chart4, chart5, chart6, chart7, chart8;
    
    public XYChart[] chart = {chart1, chart2, chart3, chart4};
    
    public XYChart[] force_chart = {chart5, chart6, chart7, chart8};
    
    public SwingWrapper<XYChart> sw;
   
    public double start_x_point = -1;
    
    public double start_y_point = -1;
    
    public int tab_count = 0;
    
    public ArrayList<Double> yAxis_sensor1;
    
    public ArrayList<Double> yAxis_sensor2;
    
    public ArrayList<Double> yAxis_sensor3;
    
    public ArrayList<Double> yAxis_sensor4;
    
    public ArrayList<Double> xAxis;
    
    List<XYChart> chart_matrix;
    
    private String identifer;
    
    public GraphPlotterUtil(int tab_count, String identifier) throws Exception {
     
        this.start_x_point = 0;
        this.start_y_point = 0;
        
        yAxis_sensor1 = new ArrayList();
        yAxis_sensor2 = new ArrayList();
        yAxis_sensor3 = new ArrayList();
        yAxis_sensor4 = new ArrayList();
        xAxis = new ArrayList();
        chart_matrix = new ArrayList<XYChart>();
        
        this.tab_count = tab_count;
        this.identifer = identifier;
        setUpPlotArea(tab_count, identifier);
        
    }
    
    public void reOpenPlot(){
        
    }
    
    public void setUpPlotArea(int tab_count, String identifier){
        
        JFrame frame = new JFrame("");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Setting tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        
        if(identifier.equalsIgnoreCase("identifier_exp1lc")){
            
            frame.setTitle("Exp1");
            
            tabbedPane.add("Load Cell",setUpChartPannel(4, 2, 2));
            
        }else if(identifier.equalsIgnoreCase("identifier_exp2lc")){
            
            frame.setTitle("Exp2");
            
            tabbedPane.add("Load Cell",setUpChartPannel(4, 2, 2));
            
        }else if(identifier.equalsIgnoreCase("identifier_exp2emg")){
            
            frame.setTitle("Exp2");
            
            tabbedPane.add("EMG",setUpChartPannel(1, 1, 1));
            
        }else if(identifier.equalsIgnoreCase("identifier_exp3fp")){
            
            frame.setTitle("Exp3");
            
            tabbedPane.add("Load Cell",setUpChartPannel(4, 2, 2));
            
        }else if(identifier.equalsIgnoreCase("identifier_exp3emg")){
            
            frame.setTitle("Exp3");
            
            tabbedPane.add("EMG",setUpChartPannel(1, 1, 1));
            
        }
        
        
        frame.add(tabbedPane, BorderLayout.CENTER);
            
        //JLabel label = new JLabel("Raw sensor data", SwingConstants.CENTER);
        //frame.add(label, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        
        /*
        
            JScrollPane scrollPane = new JScrollPane(panel[index-1]);

            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

            //tabbedPane.addTab("Sensor"+index,scrollPane);
           
            
            
            //frame.add(tabbedPane, BorderLayout.CENTER);
            tabbedPane.addTab("View All", jpanel);
            
        }
        
        */
            
    }
    
    public JPanel setUpChartPannel(int matrix_element, int rows, int coloumns){
        
        JPanel jpanel;
        
        int index = 1;
        
        while(index <= matrix_element){
            
            chart[index-1] = new XYChartBuilder().title("Raw Sensor Data"+index).xAxisTitle("X").yAxisTitle("Y").build();
            chart[index-1].getStyler().setMarkerSize(2);//radius of the marker

            ArrayList<Integer> xinit = new ArrayList();
            ArrayList<Double> yinit = new ArrayList();
            xinit.add(0);
            yinit.add(0.0);

            chart[index-1].addSeries("sensor", xinit, yinit);

            panel[index-1] = new XChartPanel<XYChart>(chart[index-1]);

            panel[index-1].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    displayPopUpChart((JPanel)e.getComponent(),
                            (JPanel)((JPanel)e.getComponent()).getParent());

                }});

            index++;
        }
        
        return setUpGridLayout(tab_count, rows, coloumns);

    }
    
    public JPanel setUpGridLayout(int tab_count, int rows, int coloumns){
        
        int index = 0;
        
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());
        JPanel jpanel = new JPanel();
        jpanel.setLayout(new GridLayout(rows, coloumns));
            
        while(index < tab_count){
                
            jpanel.add(panel[index]);
                
            index++;
                
        }
           
        parentPanel.add(jpanel,BorderLayout.CENTER);
        
        return parentPanel;
        
    }
    
    public void displayPopUpChart(JPanel jpanel, JPanel rootPanel){
        
        JFrame frame = new JFrame("");
        //frame.setLayout(new BorderLayout());
        //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //JPanel panel = new JPanel();
        //panel.add(jpanel);
        
        //frame.add(panel, BorderLayout.CENTER);
        
        rootPanel.setVisible(false);
        ((JPanel)rootPanel.getParent()).add(jpanel);
        JLabel label = new JLabel("<<Back", SwingConstants.LEFT);
        
              
        jpanel.add(label);
        //jpanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        //frame.pack();
        //frame.setVisible(true);
        
        frame.addWindowListener( new WindowAdapter(){
    
            public void windowClosing(WindowEvent e){
                rootPanel.add(jpanel);
                rootPanel.setVisible(true);
            }
        });
                
       }
    
    public void plotData(ArrayList<Double> time, ArrayList<Double> val, int index){
        
        chart[index].updateXYSeries("sensor", time, val, null);
        panel[index].revalidate();
        panel[index].repaint();
        
    }
    
    public void plotGraph(String message){
           
        //System.out.println("Received message at GraphPlotter: " + message);
  
        if(message.toString().split(":")[0].equalsIgnoreCase("identifier_exp3fp")){
        
                if(message.toString().split(":")[1].equalsIgnoreCase("start_race")){
                    
                    drawLine(start_x_point, 0);
                    
                    drawLine(start_x_point, 1);
                    
                    drawLine(start_x_point, 2);
                    
                }else{
                    
                    xAxis.add(Double.parseDouble(message.toString().split(":")[4]+""));

                    yAxis_sensor1.add(Double.parseDouble(message.toString().split(":")[1]+""));
                    
                    yAxis_sensor2.add(Double.parseDouble(message.toString().split(":")[2]+""));
                    
                    yAxis_sensor3.add(Double.parseDouble(message.toString().split(":")[3]+""));
                    
                    start_x_point =  Double.parseDouble(message.toString().split(":")[4]);
                   
                    plotData(xAxis, yAxis_sensor1, 0);
                    
                    plotData(xAxis, yAxis_sensor2, 1);
                    
                    plotData(xAxis, yAxis_sensor3, 2);
                    
                }
            }
        
        else if(message.toString().split(":")[0].equalsIgnoreCase("identifier_exp2emg")){
        
                if(message.toString().split(":")[1].equalsIgnoreCase("start_race")){
                    
                    drawLine(start_x_point, 0);
                    
                }else{
                    
                    xAxis.add(Double.parseDouble(message.toString().split(":")[2]+""));

                    yAxis_sensor1.add(Double.parseDouble(message.toString().split(":")[1]+""));
                    
                    start_x_point =  Double.parseDouble(message.toString().split(":")[2]);
                    
                    plotData(xAxis, yAxis_sensor1, 0);
                    
                }
            }
        
    }
    
    public void drawLine(double xpoint, int index){
        
        this.start_x_point = xpoint;
        
        ArrayList<Double> x_point = new ArrayList();
        x_point.add(xpoint);
        x_point.add(xpoint);
        
        ArrayList<Double> y_point = new ArrayList();
        y_point.add(0.0);
        y_point.add(100.0);
        
        XYSeries series = chart[index].addSeries("Start point", x_point, y_point);
        series.setMarker(SeriesMarkers.CIRCLE);
        series.setMarkerColor(Color.RED);
        
        panel[index].revalidate();
        panel[index].repaint();
        
    }
    
}
