/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kr.ac.ssu.ss.collectors;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.net.Socket;
import kr.ac.ssu.ss.tempcollectmanager.Constants;

/**
 *
 * @author lssang
 */
public class CollectorVoice implements Collector{
    private Socket socket=null;
    private OutputStream os;
    private InputStream is;
    private OutputStreamWriter osw;
    private PrintWriter pw;
    private boolean isStatusTest = false;
    private boolean isSocketTest = true;
    
    public int startCollecting() {
        if(isStatusTest) { System.out.println("--test start--"); }
        else {
            connectSocket();
            sendMessage(Constants.STOP_SIGNAL);
            //test code
            if(isSocketTest) {}
            else {
                closeSocket();
            }
        }       
        return getStatus();
    }

    public int getStatus() {
        int collectorStatus  = -30;
        if(isStatusTest) {
            System.out.println("--test status--");
            return 0;
        }
        else {
            if(isSocketTest) {}
            else {
                // testCode
                connectSocket();
            }
            
            sendMessage(Constants.SATUTS_SIGNAL);
            collectorStatus = receiveMessage();
            closeSocket();
        }
        return collectorStatus;
    }

    public int stopCollecting() {
        if(isStatusTest) { System.out.println("--status test stop--"); }
        else {
            connectSocket();
            sendMessage(Constants.STOP_SIGNAL);
            //test code
            if(isSocketTest) {}
            else {
                closeSocket();
            }
        }
        return getStatus();
    }
    
    private int connectSocket() {
        // connect success : return 0, fail : -1
        try {
        socket = new Socket(Constants.IP, Constants.VOICE_PORT);
        os = socket.getOutputStream();
        is = socket.getInputStream();

        } catch(IOException ioe) {
            ioe.printStackTrace();
            return -1;
        }
        return 0;
    }
    
    private int closeSocket() {
        // close success : return 0, fail : -1
        try {
            socket.close();
            socket = null;
            
            os.close();
            osw.close();
            pw.close();
            
            is.close();
            
            
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        
        return 0;
    }
    private int sendMessage(String message) {
        //write
        osw = new OutputStreamWriter(os);
        pw = new PrintWriter(osw);
        pw.println(message);
        pw.flush();
        System.out.println("send : "+message);

        return 0;
    }
    
    private int receiveMessage() {
        // receive success : return 0 or -10
        
        int signal = 0;
        try {
            String byteToString = "";
            byte[] rData = new byte[512];
            is.read(rData);
            System.out.println("receive data");
            byteToString = new String(rData,0,rData.length);
            // receive check
            signal = Integer.parseInt(byteToString.trim());
            System.out.println("receive : "+signal);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return signal;
    }
}
