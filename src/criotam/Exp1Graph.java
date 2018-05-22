/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criotam;

import java.awt.BorderLayout;
import java.util.ArrayList;
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
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;


/**
 *
 * @author AVINASH
 */
public class Exp1Graph {
    
 
    public XYChart chart;
    
    public SwingWrapper<XYChart> sw;
    
    public JPanel chartPanel;
    
    public Exp1Graph() throws Exception {
   
    // Create Chart
    //chart = QuickChart.getChart("Test Accelerometer Data", "time", 
    //        "Accelerometer Reading", "raw", new double[] { 0 }, new double[] { 0 });
 
    chart = new XYChartBuilder().title("Accelerometer plot").xAxisTitle("X").yAxisTitle("Y").build();
    chart.getStyler().setMarkerSize(2);//radius of the marker
    
    //chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
    //chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area);
    ArrayList<Integer> xinit = new ArrayList();
    ArrayList<Double> yinit = new ArrayList();
    xinit.add(0);
    yinit.add(0.0);
    
    chart.addSeries("test", xinit, yinit);
    
    JFrame frame = new JFrame("Advanced Example");
    frame.setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    //Setting tabbed pane
    JTabbedPane tabbedPane = new JTabbedPane();
    // chart
    chartPanel = new XChartPanel<XYChart>(chart);
    
    JScrollPane scrollPane = new JScrollPane(chartPanel);
   
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    
    tabbedPane.addTab("Sensor1",scrollPane);
    
    frame.add(tabbedPane, BorderLayout.CENTER);

    // label
    JLabel label = new JLabel("yo", SwingConstants.CENTER);
    frame.add(label, BorderLayout.SOUTH);

    frame.pack();
    frame.setVisible(true);
 
  }
    
    public void plotData(ArrayList<Integer> time, ArrayList<Double> val){
        
        chart.updateXYSeries("test", time, val, null);
        chartPanel.revalidate();
        chartPanel.repaint();
        
    }
    
}
