/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.ssu.ss.tempcollectmanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author JongHyeok Mun
 */
public class DeviceUseManager {
    
    private File vision_file;
    private File voice_file;
    private File bio_file;
    
    private HashMap<String, String> bioDeviceUserID;
    private String visionDeviceUserID = "none";
    private String voiceDeviceUserID = "none";
    
    private HashMap<String, Integer> bioDeviceStatus;
    private int visionDeviceStatus = Constants.STOPPED;
    private int voiceDeviceStatus = Constants.STOPPED;
    
    public DeviceUseManager() {
        bioDeviceUserID = new HashMap<String, String>();
        bioDeviceStatus = new HashMap<String, Integer>();
        bioDeviceUserID.put(Constants.BIO_DEVICE_NAME_1, "none");
        bioDeviceUserID.put(Constants.BIO_DEVICE_NAME_2, "none");
        bioDeviceUserID.put(Constants.BIO_DEVICE_NAME_3, "none");
        
        bioDeviceStatus.put(Constants.BIO_DEVICE_NAME_1, Constants.STOPPED);
        bioDeviceStatus.put(Constants.BIO_DEVICE_NAME_2, Constants.STOPPED);
        bioDeviceStatus.put(Constants.BIO_DEVICE_NAME_3, Constants.STOPPED);        
        
        vision_file = new File(Constants.PATH+Constants.VISION_FILE_NAME);
        voice_file = new File(Constants.PATH+Constants.VOICE_FILE_NAME);
        bio_file = new File(Constants.PATH+Constants.BIO_FILE_NAME);
        
        JSONParser parser = new JSONParser();
        JSONObject jSONobj = null;
        Object obj = null;
        String line;
        
        try {
            BufferedReader visionReader = new BufferedReader(new InputStreamReader(new FileInputStream(vision_file), "euc-kr"));
            BufferedReader voiceReader = new BufferedReader(new InputStreamReader(new FileInputStream(voice_file), "euc-kr"));
            BufferedReader bioReader = new BufferedReader(new InputStreamReader(new FileInputStream(bio_file), "euc-kr"));
            
            if((line = visionReader.readLine()) != null) {
                jSONobj = (JSONObject) parser.parse(line);
                visionDeviceUserID = (String)jSONobj.get(Constants.VISION_DEVICE_NAME);
            }
            if((line = voiceReader.readLine()) != null) {
                jSONobj = (JSONObject) parser.parse(line);
                voiceDeviceUserID =(String)jSONobj.get(Constants.VOICE_DEVICE_NAME);
            }
            
           if((line = bioReader.readLine()) != null) {
                jSONobj = (JSONObject) parser.parse(line);                
                bioDeviceUserID.replace(Constants.BIO_DEVICE_NAME_1, (String)jSONobj.get(Constants.BIO_DEVICE_NAME_1));
                bioDeviceUserID.replace(Constants.BIO_DEVICE_NAME_2, (String)jSONobj.get(Constants.BIO_DEVICE_NAME_2));
                bioDeviceUserID.replace(Constants.BIO_DEVICE_NAME_3, (String)jSONobj.get(Constants.BIO_DEVICE_NAME_3));
            }
        } catch(FileNotFoundException fe) {
            fe.printStackTrace();
        } catch(IOException ioe) {
        ioe.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(DeviceUseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getVisionDeviceUserID() {
        return visionDeviceUserID;
    }
 
    public void setVisionDeviceUserID(String userID) {
        JSONObject jSONobj = new JSONObject();
        jSONobj.put(Constants.VISION_DEVICE_NAME, userID);
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(vision_file));
            writer.write(jSONobj.toJSONString());
            writer.newLine();
            writer.close();
            visionDeviceUserID = userID;
        } catch(IOException ie) {
            ie.printStackTrace();
        }
    }    
    
    public String getVoiceDeviceUserID() {
        return voiceDeviceUserID;
   }

    public void setVoiceDeviceUserID(String userID) {
        JSONObject jSONobj = new JSONObject();
        jSONobj.put(Constants.VOICE_DEVICE_NAME, userID);
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(voice_file));
            writer.write(jSONobj.toJSONString());
            writer.newLine();
            writer.close();
            voiceDeviceUserID = userID;
        } catch(IOException ie) {
            ie.printStackTrace();
        }
    }
    
    public String getBioDeviceUserID(String bioDeviceID) {
        return bioDeviceUserID.get(bioDeviceID);
    }
    
    public void setBioDeviceUserID(String bioDeviceID, String userID) {
        bioDeviceUserID.replace(bioDeviceID, userID);
        JSONObject jSONobj = new JSONObject();
        jSONobj.put(Constants.BIO_DEVICE_NAME_1, bioDeviceUserID.get(Constants.BIO_DEVICE_NAME_1));
        jSONobj.put(Constants.BIO_DEVICE_NAME_2, bioDeviceUserID.get(Constants.BIO_DEVICE_NAME_2));
        jSONobj.put(Constants.BIO_DEVICE_NAME_3, bioDeviceUserID.get(Constants.BIO_DEVICE_NAME_3));
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(bio_file));
            writer.write((String)jSONobj.toJSONString());
            writer.newLine();
            writer.close();
        } catch(IOException ie) {
            ie.printStackTrace();
        }
    }
    
    public void setVisionDeviceStatus(int btnType) {
        visionDeviceStatus = btnType;
    }
    
    public void setVoiceDeviceStatus(int btnType) {
        voiceDeviceStatus = btnType;
    }
    
    public void setBioDeviceStatus(String deviceID, int btnType) {
        bioDeviceStatus.replace(deviceID, btnType);
    }
    
    public int getVisionDeviceStatus() {
        return visionDeviceStatus;
    }
    
    public int getVoiceDeviceStatus() {
        return voiceDeviceStatus;
    }
    
    public int getVoiceDeviceStatus(String deviceID) {
        return bioDeviceStatus.get(deviceID);
    }
}
