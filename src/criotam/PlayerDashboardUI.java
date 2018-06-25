/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criotam;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import criotam.actionbarfiles.CriotamUI;
import criotam.actionbarfiles.PlayerSearchUI;
import criotam.database.Sensordb;
import criotam.graph.GraphHandler;
import criotam.graph.GraphHandler.Conn_Manager;
import criotam.graph.GraphHandler1;
import criotam.graph.GraphHandler1.Conn_Manager1;
import criotam.graph.GraphHandler2;
import criotam.graph.GraphHandler2.Conn_Manager2;
import criotam.graph.GraphHandler3;
import criotam.graph.GraphHandler3.Conn_Manager3;
import criotam.graph.GraphHandler4;
import criotam.graph.GraphHandler4.Conn_Manager4;
import criotam.websocketclient.DataListener;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.UnsupportedLookAndFeelException;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author AVINASH
 */
public class PlayerDashboardUI extends javax.swing.JFrame {

    
    public ResultSet playerInfo;
    
    public String playerID;
    
    public String[] resultID;
    
    public Sensordb exp1Sensordb;
    
    public String tableName;
    
    
    public GraphHandler graphHandler;
    
    public GraphHandler1 graphHandler1;
    
    public GraphHandler2 graphHandler2;
    
    public GraphHandler3 graphHandler3;
    
    public GraphHandler4 graphHandler4;
    
    
    public String exp1_lc_URI = "ws://localhost:8080/WebServer/loadCellStreaming";
    
    public String exp2_lc_URI = "ws://localhost:8080/WebServer/exp2loadcelllistener";
    
    public String exp2_emg_URI = "ws://localhost:8080/WebServer/exp2emglistener";
    
    public String exp3_fp_URI = "ws://localhost:8080/WebServer/exp3forceplatelistener";
    
    public String exp3_emg_URI = "ws://localhost:8080/WebServer/exp3emglistener";
    
    
    public String fileName_exp1_lc = "/criotam/csv/exp1/LoadCell/";
    
    public String fileName_exp2_lc = "/criotam/csv/exp2/LoadCell/";
    
    public String fileName_exp2_emg = "/criotam/csv/exp2/emg/";
    
    public String fileName_exp3_fp = "/criotam/csv/exp3/ForcePlate/";
    
    public String fileName_exp3_emg = "/criotam/csv/exp3/emg/";
    
    
    /**
     * Creates new form PlayerDashboardUI
     */
    public PlayerDashboardUI() {
        initializeFirebase();

        initComponents();
        getContentPane().setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);
    }

    public PlayerDashboardUI(ResultSet playerInfo) throws SQLException {
        initializeFirebase();
        this.playerInfo = playerInfo;
        initComponents();
        this.setLocationRelativeTo(null);
        setPlayerInfo();
        
        getMyDocumentsDirectory();
    }

    public void setPlayerInfo() throws SQLException{
        if(playerInfo!=null){
            System.out.println("valid resultset");
            exp1_info_name.setText(playerInfo.getString("playername").toUpperCase());
            exp1_info_playerID.setText(playerInfo.getString("playerid").toUpperCase());
            exp1_info_age.setText(playerInfo.getString("age"));
            exp1_info_weight.setText(playerInfo.getString("weight"));
            exp1_info_height.setText(playerInfo.getString("height"));
            exp1_info_sex.setText(playerInfo.getString("sex"));
            
            playerID = playerInfo.getString("playerid");
                    
        }else{
            System.out.println("null resultset");
        }
    }
    
    public void getMyDocumentsDirectory(){
        
        String myDocuments = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
        System.out.println(myDocuments);
         
        fileName_exp1_lc = myDocuments + fileName_exp1_lc;
        fileName_exp2_lc = myDocuments + fileName_exp2_lc;
        fileName_exp2_emg = myDocuments + fileName_exp2_emg;
        fileName_exp3_fp = myDocuments + fileName_exp3_fp;
        fileName_exp3_emg = myDocuments + fileName_exp3_emg;
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        jPanel2 = new javax.swing.JPanel();
        jDialog = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        exp1_info_name = new javax.swing.JLabel();
        exp1_info_playerID = new javax.swing.JLabel();
        exp1_info_age = new javax.swing.JLabel();
        exp1_info_weight = new javax.swing.JLabel();
        exp1_info_height = new javax.swing.JLabel();
        exp1_info_sex = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        start = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        stop = new javax.swing.JButton();
        exp1_ok = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        exp2_lc_start = new javax.swing.JButton();
        exp2_lc_stop = new javax.swing.JButton();
        exp2_emg_start = new javax.swing.JButton();
        exp2_emg_stop = new javax.swing.JButton();
        exp2_history_menu = new javax.swing.JComboBox<>();
        exp2_ok = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        exp3_fp_start = new javax.swing.JButton();
        exp3_fp_stop = new javax.swing.JButton();
        exp3_emg_start = new javax.swing.JButton();
        exp3_emg_stop = new javax.swing.JButton();
        exp3_history_menu = new javax.swing.JComboBox<>();
        exp3_ok = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        ActionBar = new javax.swing.JPanel();
        selectplayer = new javax.swing.JLabel();
        newplayer = new javax.swing.JLabel();
        live = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialogLayout = new javax.swing.GroupLayout(jDialog.getContentPane());
        jDialog.getContentPane().setLayout(jDialogLayout);
        jDialogLayout.setHorizontalGroup(
            jDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialogLayout.setVerticalGroup(
            jDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/drawables/logo.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setPreferredSize(new java.awt.Dimension(1000, 550));

        jPanel6.setBackground(new java.awt.Color(52, 51, 51));
        jPanel6.setPreferredSize(new java.awt.Dimension(180, 550));

        exp1_info_name.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        exp1_info_name.setForeground(new java.awt.Color(255, 255, 255));
        exp1_info_name.setText("AVINASH KUMAR RANJAN");

        exp1_info_playerID.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        exp1_info_playerID.setForeground(new java.awt.Color(255, 255, 255));
        exp1_info_playerID.setText("PLAYER ID");

        exp1_info_age.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        exp1_info_age.setForeground(new java.awt.Color(255, 255, 255));
        exp1_info_age.setText("Age");

        exp1_info_weight.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        exp1_info_weight.setForeground(new java.awt.Color(255, 255, 255));
        exp1_info_weight.setText("Weight");

        exp1_info_height.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        exp1_info_height.setForeground(new java.awt.Color(255, 255, 255));
        exp1_info_height.setText("Height");

        exp1_info_sex.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        exp1_info_sex.setForeground(new java.awt.Color(255, 255, 255));
        exp1_info_sex.setText("Male");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/logo2.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        jLabel4.setAlignmentX(0.5F);

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Age");

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Weight");

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Height");

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Gender");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("-");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("-");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("-");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("-");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(exp1_info_playerID)
                    .addComponent(exp1_info_name)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jLabel18))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel13))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))))
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(exp1_info_height)
                            .addComponent(exp1_info_sex)
                            .addComponent(exp1_info_weight)
                            .addComponent(exp1_info_age))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(29, 29, 29)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(exp1_info_playerID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exp1_info_name)
                .addGap(41, 41, 41)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel14)
                    .addComponent(exp1_info_age))
                .addGap(1, 1, 1)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel15)
                    .addComponent(exp1_info_weight))
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel16)
                    .addComponent(exp1_info_height))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exp1_info_sex, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(533, 533, 533))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(681, 550));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(289, 550));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(85, 83, 83));
        jLabel1.setText("Start Load Cells");

        start.setBackground(new java.awt.Color(204, 255, 0));
        start.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        start.setForeground(new java.awt.Color(255, 255, 255));
        start.setText("START");
        start.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        start.setContentAreaFilled(false);
        start.setOpaque(true);
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Load Cell", "Force", "Moment" }));
        jComboBox1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        jComboBox1.setPreferredSize(new java.awt.Dimension(37, 35));
        jComboBox1.setSelectedIndex(0);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        stop.setBackground(new java.awt.Color(204, 255, 0));
        stop.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        stop.setForeground(new java.awt.Color(255, 255, 255));
        stop.setText("STOP");
        stop.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        stop.setContentAreaFilled(false);
        stop.setOpaque(true);
        stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopActionPerformed(evt);
            }
        });

        exp1_ok.setBackground(new java.awt.Color(204, 255, 0));
        exp1_ok.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        exp1_ok.setForeground(new java.awt.Color(255, 255, 255));
        exp1_ok.setText("Ok");
        exp1_ok.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        exp1_ok.setContentAreaFilled(false);
        exp1_ok.setOpaque(true);
        exp1_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exp1_okActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(204, 255, 0));
        jLabel19.setText("Recording");

        jLabel20.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 255, 0));
        jLabel20.setText("History");

        jLabel21.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(85, 83, 83));
        jLabel21.setText("View");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(stop, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(exp1_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(201, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stop, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exp1_ok, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(218, 218, 218))
        );

        jTabbedPane1.addTab("Experiment1", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        exp2_lc_start.setBackground(new java.awt.Color(204, 255, 0));
        exp2_lc_start.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        exp2_lc_start.setForeground(new java.awt.Color(255, 255, 255));
        exp2_lc_start.setText("START");
        exp2_lc_start.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        exp2_lc_start.setContentAreaFilled(false);
        exp2_lc_start.setOpaque(true);
        exp2_lc_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exp2_lc_startActionPerformed(evt);
            }
        });

        exp2_lc_stop.setBackground(new java.awt.Color(204, 255, 0));
        exp2_lc_stop.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        exp2_lc_stop.setForeground(new java.awt.Color(255, 255, 255));
        exp2_lc_stop.setText("STOP");
        exp2_lc_stop.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        exp2_lc_stop.setContentAreaFilled(false);
        exp2_lc_stop.setOpaque(true);
        exp2_lc_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exp2_lc_stopActionPerformed(evt);
            }
        });

        exp2_emg_start.setBackground(new java.awt.Color(204, 255, 0));
        exp2_emg_start.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        exp2_emg_start.setForeground(new java.awt.Color(255, 255, 255));
        exp2_emg_start.setText("START");
        exp2_emg_start.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        exp2_emg_start.setContentAreaFilled(false);
        exp2_emg_start.setOpaque(true);
        exp2_emg_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exp2_emg_startActionPerformed(evt);
            }
        });

        exp2_emg_stop.setBackground(new java.awt.Color(204, 255, 0));
        exp2_emg_stop.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        exp2_emg_stop.setForeground(new java.awt.Color(255, 255, 255));
        exp2_emg_stop.setText("STOP");
        exp2_emg_stop.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        exp2_emg_stop.setContentAreaFilled(false);
        exp2_emg_stop.setOpaque(true);
        exp2_emg_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exp2_emg_stopActionPerformed(evt);
            }
        });

        exp2_history_menu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        exp2_history_menu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Load Cell", "Emg", "Force", "Moment" }));
        jComboBox1.setSelectedIndex(0);
        exp2_history_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exp2_history_menuActionPerformed(evt);
            }
        });

        exp2_ok.setBackground(new java.awt.Color(204, 255, 0));
        exp2_ok.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        exp2_ok.setForeground(new java.awt.Color(255, 255, 255));
        exp2_ok.setText("Ok");
        exp2_ok.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        exp2_ok.setContentAreaFilled(false);
        exp2_ok.setOpaque(true);
        exp2_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exp2_okActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(85, 83, 83));
        jLabel2.setText("Start Load Cells");

        jLabel22.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(204, 255, 0));
        jLabel22.setText("Recording");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(85, 83, 83));
        jLabel7.setText("Start EMG");

        jLabel23.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(204, 255, 0));
        jLabel23.setText("Recording");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(85, 83, 83));
        jLabel8.setText("View");

        jLabel24.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(204, 255, 0));
        jLabel24.setText("Recording");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel22))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(exp2_lc_start, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(34, 34, 34)
                            .addComponent(exp2_lc_stop, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(exp2_history_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(exp2_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(exp2_emg_start, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(exp2_emg_stop, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel23))))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exp2_lc_start, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exp2_lc_stop, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exp2_emg_start, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exp2_emg_stop, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exp2_history_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exp2_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(119, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Experiment2", jPanel3);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setPreferredSize(new java.awt.Dimension(676, 550));

        exp3_fp_start.setBackground(new java.awt.Color(204, 255, 0));
        exp3_fp_start.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        exp3_fp_start.setForeground(new java.awt.Color(255, 255, 255));
        exp3_fp_start.setText("START");
        exp3_fp_start.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        exp3_fp_start.setContentAreaFilled(false);
        exp3_fp_start.setOpaque(true);
        exp3_fp_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exp3_fp_startActionPerformed(evt);
            }
        });

        exp3_fp_stop.setBackground(new java.awt.Color(204, 255, 0));
        exp3_fp_stop.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        exp3_fp_stop.setForeground(new java.awt.Color(255, 255, 255));
        exp3_fp_stop.setText("STOP");
        exp3_fp_stop.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        exp3_fp_stop.setContentAreaFilled(false);
        exp3_fp_stop.setOpaque(true);
        exp3_fp_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exp3_fp_stopActionPerformed(evt);
            }
        });

        exp3_emg_start.setBackground(new java.awt.Color(204, 255, 0));
        exp3_emg_start.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        exp3_emg_start.setForeground(new java.awt.Color(255, 255, 255));
        exp3_emg_start.setText("START");
        exp3_emg_start.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        exp3_emg_start.setContentAreaFilled(false);
        exp3_emg_start.setOpaque(true);
        exp3_emg_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exp3_emg_startActionPerformed(evt);
            }
        });

        exp3_emg_stop.setBackground(new java.awt.Color(204, 255, 0));
        exp3_emg_stop.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        exp3_emg_stop.setForeground(new java.awt.Color(255, 255, 255));
        exp3_emg_stop.setText("STOP");
        exp3_emg_stop.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        exp3_emg_stop.setContentAreaFilled(false);
        exp3_emg_stop.setOpaque(true);
        exp3_emg_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exp3_emg_stopActionPerformed(evt);
            }
        });

        exp3_history_menu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        exp3_history_menu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Load Cell", "Emg", "Force", "Moment" }));
        jComboBox1.setSelectedIndex(0);
        exp3_history_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exp3_history_menuActionPerformed(evt);
            }
        });

        exp3_ok.setBackground(new java.awt.Color(204, 255, 0));
        exp3_ok.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        exp3_ok.setForeground(new java.awt.Color(255, 255, 255));
        exp3_ok.setText("Ok");
        exp3_ok.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        exp3_ok.setContentAreaFilled(false);
        exp3_ok.setOpaque(true);
        exp3_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exp3_okActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(85, 83, 83));
        jLabel9.setText("Start Load Cells");

        jLabel25.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(204, 255, 0));
        jLabel25.setText("Recording");

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(85, 83, 83));
        jLabel10.setText("Start EMG");

        jLabel26.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(204, 255, 0));
        jLabel26.setText("Recording");

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(85, 83, 83));
        jLabel11.setText("View");

        jLabel27.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(204, 255, 0));
        jLabel27.setText("History");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(exp3_history_menu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exp3_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                            .addComponent(exp3_emg_start, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(exp3_emg_stop, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(exp3_fp_start, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(exp3_fp_stop, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel26))))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exp3_fp_start, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exp3_fp_stop, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exp3_emg_start, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exp3_emg_stop, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exp3_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exp3_history_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(104, 104, 104))
        );

        jTabbedPane1.addTab("Experiment3", jPanel7);

        ActionBar.setBackground(new java.awt.Color(255, 255, 255));
        ActionBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        selectplayer.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        selectplayer.setForeground(new java.awt.Color(85, 83, 83));
        selectplayer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        selectplayer.setText("Select Player");
        selectplayer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectplayerMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                selectplayerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                selectplayerMouseExited(evt);
            }
        });

        newplayer.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        newplayer.setForeground(new java.awt.Color(85, 83, 83));
        newplayer.setText("New Player");
        newplayer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newplayerMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                newplayerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                newplayerMouseExited(evt);
            }
        });

        live.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        live.setForeground(new java.awt.Color(85, 83, 83));
        live.setText("Live");

        javax.swing.GroupLayout ActionBarLayout = new javax.swing.GroupLayout(ActionBar);
        ActionBar.setLayout(ActionBarLayout);
        ActionBarLayout.setHorizontalGroup(
            ActionBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ActionBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectplayer)
                .addGap(18, 18, 18)
                .addComponent(newplayer)
                .addGap(18, 18, 18)
                .addComponent(live)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ActionBarLayout.setVerticalGroup(
            ActionBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(selectplayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(newplayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(live, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE))
            .addComponent(ActionBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ActionBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        // TODO add your handling code here:
        if(graphHandler == null){//TODO: check for existing opened window
          tableName = "exp1_"+playerID+"_lc";
          graphHandler = new GraphHandler(fileName_exp1_lc, playerID, 
                  tableName, exp1_lc_URI, 4, "identifier_exp1lc", new Conn_Manager() {
              @Override
              public void onConnClosed(String fileName) {
                  saveFile(Long.parseLong(playerID), "exp1", "raw_sensor_loadcell"
                          , getTimeStamp(), "exp1/loadCell/"+getTimeStamp()+"/", new File(fileName));
              }
          });
          graphHandler.start();
        }else if(graphHandler.isClosed()){
          tableName = "exp1_"+playerID+"_lc";
          graphHandler = new GraphHandler(fileName_exp1_lc, playerID, 
                  tableName, exp1_lc_URI, 4, "identifier_exp1lc", new Conn_Manager() {
              @Override
              public void onConnClosed(String fileName) {
                  saveFile(Long.parseLong(playerID), "exp1", "raw_sensor_loadcell"
                          , getTimeStamp(), "exp1/loadCell/"+getTimeStamp()+"/", new File(fileName));
              }
          });
          graphHandler.start();
        }else{
            graphHandler.reOpenPlot();
            //infoBox("Recording already started!");
        }
    }//GEN-LAST:event_startActionPerformed

    private void stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopActionPerformed
        // TODO add your handling code here:
        if(graphHandler!=null)
        graphHandler.close();
    }//GEN-LAST:event_stopActionPerformed

    
    private void exp1_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exp1_okActionPerformed
       //jComboBox1.getSelectedIndex()==4
       
        fileChooser.setCurrentDirectory(new File(fileName_exp1_lc));
        
        int returnVal = fileChooser.showOpenDialog(this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
 
            File file = fileChooser.getSelectedFile();
            {
                try {
                    System.out.println(file.getAbsolutePath()+"");
                    
                    displayGraph(file.getAbsolutePath()+"");
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } 
        } else{
            System.out.println("File access cancelled by user.");
        }
    
    }//GEN-LAST:event_exp1_okActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void exp2_lc_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exp2_lc_startActionPerformed
        // TODO add your handling code here:
        if(graphHandler1 == null){
            tableName = "exp2_"+playerID+"_lc";
            graphHandler1 = new GraphHandler1(fileName_exp2_lc, playerID, 
                    tableName, exp2_lc_URI, 4, "identifier_exp2lc", new Conn_Manager1() {
              @Override
              public void onConnClosed(String fileName) {
                  saveFile(Long.parseLong(playerID), "exp2", "raw_sensor_loadcell"
                          , getTimeStamp(), "exp2/loadCell/"+getTimeStamp()+"/", new File(fileName));
              }
          });
            graphHandler1.start();
        }else if(graphHandler1.isClosed()){
            tableName = "exp2_"+playerID+"_lc";
            graphHandler1 = new GraphHandler1(fileName_exp2_lc, playerID, 
                    tableName, exp2_lc_URI, 4, "identifier_exp2lc", new Conn_Manager1() {
              @Override
              public void onConnClosed(String fileName) {
                  saveFile(Long.parseLong(playerID), "exp2", "raw_sensor_emg"
                          , getTimeStamp(), "exp2/emg/"+getTimeStamp()+"/", new File(fileName));
              }
          });
            graphHandler1.start();
        }else{
            graphHandler1.reOpenPlot();
            //infoBox("Recording already started!");
        }
    }//GEN-LAST:event_exp2_lc_startActionPerformed

    private void exp2_lc_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exp2_lc_stopActionPerformed
        // TODO add your handling code here:
        if(graphHandler1!=null)
        graphHandler1.close();
        //store file
    }//GEN-LAST:event_exp2_lc_stopActionPerformed

    private void exp2_emg_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exp2_emg_stopActionPerformed
        // TODO add your handling code here:
        if(graphHandler2!=null)
        graphHandler2.close();
    }//GEN-LAST:event_exp2_emg_stopActionPerformed

    private void exp2_history_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exp2_history_menuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exp2_history_menuActionPerformed

    private void exp2_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exp2_okActionPerformed
        // TODO add your handling code here:
        fileChooser.setCurrentDirectory(new File(fileName_exp2_emg));
        
        switch(exp2_history_menu.getSelectedIndex()){
            case 1: fileChooser.setCurrentDirectory(new File(fileName_exp2_emg));break;
            default: fileChooser.setCurrentDirectory(new File(fileName_exp2_lc));break;
        }
        
        int returnVal = fileChooser.showOpenDialog(this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
 
            File file = fileChooser.getSelectedFile();
            {
                try {
                    System.out.println(file.getAbsolutePath()+"");
                    
                    displayGraph(file.getAbsolutePath()+"");
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } 
        } else{
            System.out.println("File access cancelled by user.");
        }

    }//GEN-LAST:event_exp2_okActionPerformed

    private void exp3_fp_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exp3_fp_startActionPerformed
        // TODO add your handling code here:
        if(graphHandler3 == null){
            tableName = "exp3_"+playerID+"_fp";
            graphHandler3 = new GraphHandler3(fileName_exp3_fp, playerID, 
                    tableName, exp3_fp_URI, 3, "identifier_exp3fp", new Conn_Manager3() {
              @Override
              public void onConnClosed(String fileName) {
                  saveFile(Long.parseLong(playerID), "exp3", "raw_sensor_forceplate"
                          , getTimeStamp(), "exp3/forceplate/"+getTimeStamp()+"/", new File(fileName));
              }
          });
            graphHandler3.start();
        }else if(graphHandler3.isClosed()){
            tableName = "exp3_"+playerID+"_fp";
            graphHandler3 = new GraphHandler3(fileName_exp3_fp, playerID, 
                    tableName, exp3_fp_URI, 3, "identifier_exp3fp", new Conn_Manager3() {
              @Override
              public void onConnClosed(String fileName) {
                  saveFile(Long.parseLong(playerID), "exp3", "raw_sensor_forceplate"
                          , getTimeStamp(), "exp3/forceplate/"+getTimeStamp()+"/", new File(fileName));
              }
          });
            graphHandler3.start();
        }else{
            graphHandler3.reOpenPlot();
            infoBox("Recording already started!");
        }
    }//GEN-LAST:event_exp3_fp_startActionPerformed

    private void exp3_fp_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exp3_fp_stopActionPerformed
        // TODO add your handling code here:
        if(graphHandler3!=null)
        graphHandler3.close();
    }//GEN-LAST:event_exp3_fp_stopActionPerformed

    private void exp3_emg_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exp3_emg_startActionPerformed
        // TODO add your handling code here:
        if(graphHandler4 == null){
            tableName = "exp3_"+playerID+"_emg";
            graphHandler4 = new GraphHandler4(fileName_exp3_emg, playerID,
                    tableName, exp3_emg_URI, 1, "identifier_exp3emg", new Conn_Manager4() {
              @Override
              public void onConnClosed(String fileName) {
                  saveFile(Long.parseLong(playerID), "exp3", "raw_sensor_emg"
                          , getTimeStamp(), "exp3/emg/"+getTimeStamp()+"/", new File(fileName));
              }
          });
            graphHandler4.start();
        }else if(graphHandler4.isClosed()){
            tableName = "exp3_"+playerID+"_emg";
            graphHandler4 = new GraphHandler4(fileName_exp3_emg, playerID,
                    tableName, exp3_emg_URI, 1, "identifier_exp3emg", new Conn_Manager4() {
              @Override
              public void onConnClosed(String fileName) {
                  saveFile(Long.parseLong(playerID), "exp3", "raw_sensor_emg"
                          , getTimeStamp(), "exp3/emg/"+getTimeStamp()+"/", new File(fileName));
              }
          });
            graphHandler4.start();
        }else{
            graphHandler4.reOpenPlot();
            infoBox("Recording already started!");
        }  
    }//GEN-LAST:event_exp3_emg_startActionPerformed

    private void exp3_emg_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exp3_emg_stopActionPerformed
        // TODO add your handling code here:
        if(graphHandler4!=null)
        graphHandler4.close();
    }//GEN-LAST:event_exp3_emg_stopActionPerformed

    private void exp3_history_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exp3_history_menuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exp3_history_menuActionPerformed

    private void exp3_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exp3_okActionPerformed
        // TODO add your handling code here:
        fileChooser.setCurrentDirectory(new File(fileName_exp3_fp));
        
        switch(exp3_history_menu.getSelectedIndex()){
            case 0: fileChooser.setCurrentDirectory(new File(fileName_exp3_fp));break;
            case 1: fileChooser.setCurrentDirectory(new File(fileName_exp3_emg));break;
            default: fileChooser.setCurrentDirectory(new File(fileName_exp3_fp));break;
        }
        
        int returnVal = fileChooser.showOpenDialog(this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
 
            File file = fileChooser.getSelectedFile();
            {
                try {
                    System.out.println(file.getAbsolutePath()+"");
                    
                    displayGraph(file.getAbsolutePath()+"");
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } 
        } else{
            System.out.println("File access cancelled by user.");
        }

    }//GEN-LAST:event_exp3_okActionPerformed

    private void selectplayerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectplayerMouseClicked
        // TODO add your handling code here:
        new PlayerSearchUI("exit").setVisible(true);
    }//GEN-LAST:event_selectplayerMouseClicked

    private void newplayerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newplayerMouseClicked
        // TODO add your handling code here:
        new CriotamUI("exit").setVisible(true);
    }//GEN-LAST:event_newplayerMouseClicked

    private void exp2_emg_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exp2_emg_startActionPerformed
        // TODO add your handling code here:
        if(graphHandler2 == null){
            tableName = "exp2_"+playerID+"_emg";
            graphHandler2 = new GraphHandler2(fileName_exp2_emg, playerID,
                tableName, exp2_emg_URI, 1, "identifier_exp2emg", new Conn_Manager2() {
              @Override
              public void onConnClosed(String fileName) {
                  
              }
          });
            graphHandler2.start();
        }else if(graphHandler2.isClosed()){
            tableName = "exp2_"+playerID+"_emg";
            graphHandler2 = new GraphHandler2(fileName_exp2_emg, playerID,
                tableName, exp2_emg_URI, 1, "identifier_exp2emg", new Conn_Manager2() {
              @Override
              public void onConnClosed(String fileName) {
                  
              }
          });
            graphHandler2.start();
        }else{
            graphHandler2.reOpenPlot();
            infoBox("Recording already started!");
        }
    }//GEN-LAST:event_exp2_emg_startActionPerformed

    private void selectplayerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectplayerMouseEntered
        // TODO add your handling code here:
        selectplayer.setForeground(new java.awt.Color(204, 255, 0));
    }//GEN-LAST:event_selectplayerMouseEntered

    private void selectplayerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectplayerMouseExited
        // TODO add your handling code here:
        selectplayer.setForeground(new java.awt.Color(85, 83, 83));
    }//GEN-LAST:event_selectplayerMouseExited

    private void newplayerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newplayerMouseEntered
        // TODO add your handling code here:
        newplayer.setForeground(new java.awt.Color(204, 255, 0));
    }//GEN-LAST:event_newplayerMouseEntered

    private void newplayerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newplayerMouseExited
        // TODO add your handling code here:
        newplayer.setForeground(new java.awt.Color(85, 83, 83));
    }//GEN-LAST:event_newplayerMouseExited

    //for exp1
    public void showExp1RawSensorHistory() throws SQLException{
        exp1Sensordb = new Sensordb(tableName);
        ResultSet rs = exp1Sensordb.getRawSensorFileNames(tableName);
        if(rs == null){
            infoBox("No History Data available!");
        }else
            showPopUpMenu(rs, "fileName");
    }
    
    public void showPopUpMenu(ResultSet rs, String coloumn) throws SQLException{
        
        DefaultListModel<String> model = new DefaultListModel<>();
        JList list = new JList(model);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        
        while(rs.next()){
            model.addElement(rs.getString(coloumn));
        }
        
        CustomDialog dialog = new CustomDialog("Choose file: ", list);
        dialog.setOnOk(e -> {
            try {
                displayGraph(dialog.getSelectedItem().toString().trim()+"");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        dialog.show();
        
    }
    
    public void infoBox(String message)
    {
        JOptionPane.showMessageDialog(null, message,
                "Error " + "", JOptionPane.INFORMATION_MESSAGE);
        
    }
    
    public void displayGraph(String fileName) throws Exception{
        
        new ReadCsvFile(fileName);
        
    } 
  
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
            java.util.logging.Logger.getLogger(PlayerDashboardUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlayerDashboardUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlayerDashboardUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlayerDashboardUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlayerDashboardUI().setVisible(true);
            }
        });
        
    }
    
    
    public String getTimeStamp(){
        
        long epoch = 0;
                
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(); 
        System.out.println(dateFormat.format(date));
        
        String d = dateFormat.format(date);
        /*
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        
        try {
            Date dat = df.parse(d);
            epoch = date.getTime();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        */
        return d;
        
    }

    public void saveFile(long player_id, String exp, String parameter,
            String date, String destination, File rawFile) {
        
        MediaType MEDIA_TYPE_CSV = MediaType.parse("text/csv");
        
        OkHttpClient client = new OkHttpClient();
 
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("player_id", ""+player_id)
                .addFormDataPart("exp", exp)
                .addFormDataPart("parameter", parameter)
                .addFormDataPart("date", ""+date)
                .addFormDataPart("destination", destination)
                .addFormDataPart("rawFile", rawFile.getName(),RequestBody.create(MEDIA_TYPE_CSV, rawFile))
                .build();
 
        Request request = new Request.Builder().url("https://us-central1-criotam-bec9b.cloudfunctions.net/uploadFile")
                .post(requestBody).build();
 
        Response response;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
            System.out.println("Not Successful response");
        }else{
                
            System.out.println("Successful response 200 Ok"+response.body().string());
        }
        } catch (IOException ex) {
            Logger.getLogger(PlayerDashboardUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    Map<String, String> parameters;
    
    public void storeDataOnBlockChain( String blockChaindata) throws MalformedURLException, IOException /*throws MalformedURLException, IOException*/{
        
        blockChaindata = blockChaindata.substring(0, blockChaindata.length()-2);
        
        System.out.println("File content:"+ blockChaindata);
        
        URL url = new URL("http://192.168.1.14:3000/"
                + "api/org.criotam.prototype.iotTransactions.readexperiment2emgData");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        
        parameters = new HashMap();
        parameters.put("experiment1", "resource:org.criotam.prototype.iotAssets.experiment2emgData#EX02");
        parameters.put("Raw_value", blockChaindata);
        
        con.setConnectTimeout(100000);
        con.setReadTimeout(100000);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ParameterBuilder.getParamsString(parameters));
        out.flush();
        out.close();
        
        int status = con.getResponseCode();
        if(status == 200){
            System.out.println("#################### add successful----------------------------");
            con.disconnect();
        }else if(status == 500){
            System.out.println("Internal server error");
            con.disconnect();
        }else{
            System.out.println("error");
            con.disconnect();
        }
        con.disconnect();

    }

    
    
    public void initializeFirebase(){
        
        try {
            FileInputStream serviceAccount;
            serviceAccount = new FileInputStream("C://Users/AVINASH/Desktop/CriotamPrototype/criotam-bec9b-firebase-adminsdk-k6t7p-c3740d0a3f.json");
            
            FirebaseOptions options = new FirebaseOptions.Builder()                        
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://criotam-bec9b.firebaseio.com/")
                .build();

            FirebaseApp.initializeApp(options);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataListener.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ActionBar;
    private javax.swing.JLabel exp1_info_age;
    private javax.swing.JLabel exp1_info_height;
    private javax.swing.JLabel exp1_info_name;
    private javax.swing.JLabel exp1_info_playerID;
    private javax.swing.JLabel exp1_info_sex;
    private javax.swing.JLabel exp1_info_weight;
    private javax.swing.JButton exp1_ok;
    private javax.swing.JButton exp2_emg_start;
    private javax.swing.JButton exp2_emg_stop;
    private javax.swing.JComboBox<String> exp2_history_menu;
    private javax.swing.JButton exp2_lc_start;
    private javax.swing.JButton exp2_lc_stop;
    private javax.swing.JButton exp2_ok;
    private javax.swing.JButton exp3_emg_start;
    private javax.swing.JButton exp3_emg_stop;
    private javax.swing.JButton exp3_fp_start;
    private javax.swing.JButton exp3_fp_stop;
    private javax.swing.JComboBox<String> exp3_history_menu;
    private javax.swing.JButton exp3_ok;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDialog jDialog;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel live;
    private javax.swing.JLabel newplayer;
    private javax.swing.JLabel selectplayer;
    private javax.swing.JButton start;
    private javax.swing.JButton stop;
    // End of variables declaration//GEN-END:variables
    
}
