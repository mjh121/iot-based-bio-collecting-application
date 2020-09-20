/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kr.ac.ssu.ss.tempcollectmanager;

/**
 *
 * @author lssang
 */
public interface Constants {
    
    //Signal
    public String RUN_SIGNAL = "1";
    public String STOP_SIGNAL = "2";
    public String SATUTS_SIGNAL = "3";
    
    //PORT
    public int VISION_PORT = 60901;
    public int BIO_PORT = 60902;
    public int VOICE_PORT = 60903;
    
    //IP
    public String IP = "127.0.0.1";
    
    //User Id Control
    public int DONE = 0;
    public int INIT_RESULT = 1;
    public int ALREADY_EXIST = 2;
    
    //ButtonType
    public int ON = 1;
    public int OFF = 2;
    
    //Collector Type
    public int VISION = 10;
    public int BIO = 20;
    public int VOICE = 30;    
    
    //Collector Status
    public int RUNNING = 0;
    public int STOPPED = 1;
    
    //VISIOTN ERROR
    public int VISION_ERROR = -10;
    public int VISION_ERROR_SOMETHING = -11;
            
    //BIO_ERROR
    public int BIO_ERROR = -20;
    
    //VOICE_ERROR
    public int VOICE_ERROR = -30;
    
    // File Path
    public String PATH = "C:\\silverData\\";
    public String USER_FILE_NAME = "users\\users.list";
    public String VISION_FILE_NAME = "dmap\\vision.json";
    public String BIO_FILE_NAME = "dmap\\bio.json";
    public String VOICE_FILE_NAME = "dmap\\voice.json";
    
    // Device ID
    public String VISION_DEVICE_NAME = "cam1";
    public String VOICE_DEVICE_NAME = "mic1";
    public String BIO_DEVICE_NAME_1 = "gear1";
    public String BIO_DEVICE_NAME_2 = "gear2";
    public String BIO_DEVICE_NAME_3 = "gear3";
}
