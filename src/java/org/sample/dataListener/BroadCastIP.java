/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sample.dataListener;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author AVINASH
 */
public class BroadCastIP extends HttpServlet {
    
    private static String ip;
    
    private static DatagramSocket socket = null;
    
     public void init() throws ServletException {
         
         Timer t = new Timer();
         
         t.scheduleAtFixedRate(new TimerTask() {
             @Override
             public void run() {
                 try {
                     
                     for(InetAddress address: listAllBroadcastAddresses())
                         broadcast("Hello!! #myIP@"+ ip, address);
                     
                 } catch (UnknownHostException ex) {
                     ex.printStackTrace();
                 } catch (IOException ex) {
                     ex.printStackTrace();
                 }
             }
         }, 0, 2000);
         
    }
     
     
     public static List<InetAddress> listAllBroadcastAddresses() throws SocketException {
         
         List<InetAddress> broadcastList = new ArrayList<>();
         Enumeration<NetworkInterface> interfaces 
                 = NetworkInterface.getNetworkInterfaces();
         while (interfaces.hasMoreElements()) {
             NetworkInterface networkInterface = interfaces.nextElement();
             if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                 continue;
             }
             
             
             Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
             while(addresses.hasMoreElements()) {
                 InetAddress addr = addresses.nextElement();
                 // *EDIT*
                 if (addr instanceof Inet6Address) continue;
                 ip = addr.getHostAddress();
                 //System.out.println(networkInterface.getDisplayName() + " " + ip);
        }
 
        networkInterface.getInterfaceAddresses().stream() 
          .map(a -> a.getBroadcast())
          .filter(Objects::nonNull)
          .forEach(broadcastList::add);
         }
         
         //System.out.println("List size:"+broadcastList.size());
         
         return broadcastList;
     }
     
     
     public static void broadcast(
      String broadcastMessage, InetAddress address) throws IOException {
        socket = new DatagramSocket();
        socket.setBroadcast(true);
 
        byte[] buffer = broadcastMessage.getBytes();
 
        DatagramPacket packet 
          = new DatagramPacket(buffer, buffer.length, address, 4445);
        socket.send(packet);
        socket.close();
    }
    
}
