/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kr.ac.ssu.ss.collectors;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import kr.ac.ssu.ss.tempcollectmanager.Constants;

import kr.ac.ssu.ss.tempcollectmanager.Constants;
/**
 *
 * @author lssang
 */
public class CollectorVision implements Collector{
    private Socket socket=null;
    private OutputStream os;
    private BufferedOutputStream bos;
    
    private InputStream is;
    private BufferedInputStream bis;
    
    //test Code
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
        int collectorStatus  = -10;
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
        socket = new Socket(Constants.IP, Constants.VISION_PORT);
        os = socket.getOutputStream();
        bos = new BufferedOutputStream(os);        
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
            bos.close();
            
            is.close();
            bis.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        
        return 0;
    }
    private int sendMessage(String message) {
        try {
            //write
            byte[] w_data = new byte[1024];
            w_data= message.getBytes();
            bos.write(w_data);
            bos.flush();
            // send check
            String byteToString = new String(w_data,0,w_data.length);
            System.out.println("send : "+byteToString);
        } catch(IOException ioe) {
            ioe.printStackTrace();
            return -1;
        }
        return 0;
    }
    
    private int receiveMessage() {
        // receive success : return 0 or -10
        int signal = 0;
        try {
            bis = new BufferedInputStream(is);
            bis = new BufferedInputStream(is);   
            byte[] rData = new byte[1024];
            bis.read(rData);
            String byteToString = new String(rData,0,rData.length);

            // receive check
            System.out.println("receive : "+byteToString);
            signal = Integer.parseInt(byteToString.trim());
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return signal;
    }
}
