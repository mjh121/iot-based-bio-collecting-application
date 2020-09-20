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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 *
 * @author lssang
 */
public class UserManager {
    
    private ArrayList<User> users;
    private File user_file = new File(Constants.PATH+Constants.USER_FILE_NAME);
    public UserManager() {
        users = new ArrayList<User>();
        
        // Jonghyeok
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(user_file), "euc-kr"));
            
            String line = null;
            String[] splitedStr = null;
            
            while((line = reader.readLine()) != null) {
                splitedStr = null;
                splitedStr = line.split(" ");

                for(int i=0; i<splitedStr.length; i++) {
                    splitedStr[i] = splitedStr[i].trim();
                }
                System.out.println("user info : "+splitedStr[0]+", "+splitedStr[1]);
                users.add(new User(splitedStr[0], splitedStr[1]));
           }
            
            reader.close();
        } catch(FileNotFoundException fe) {
            fe.printStackTrace();
        } catch(IOException ie) {
            ie.printStackTrace();
        }
    }
    
    public String[] getUserList(){
        int leng = users.size();
        String list[] = new String[leng];
                
        for(int i = 0 ; i < leng ; i++){
            User user = users.get(i);
            String item = user.getName()+"("+user.getId()+")";
            list[i] = item;
        }        
        return list;
    }
    
    public int addUser(String id, String name){
        int leng = users.size();
        
        for(int i = 0 ; i < leng ; i ++){
            String uId = users.get(i).getId();
            if(id.equals(uId)){
                return Constants.ALREADY_EXIST;
            }            
        }
        
        String user_info = id+" "+name;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(user_file, true));
            writer.write(user_info);
            writer.newLine();
            writer.close();
            users.add(new User(id, name));
        } catch(IOException ie) {
            ie.printStackTrace();
        }
        
        return Constants.DONE;
    }
    
    public boolean containId(String id){
        int leng = users.size();
        
        for(int i = 0 ; i < leng ; i ++){
             String uId = users.get(i).getId();
             if(id.equals(uId)){
                return true;
            } 
        }
        return false;
    }
    
    public boolean containName(String name){
        int leng = users.size();
        
        for(int i = 0 ; i < leng ; i ++){
             String uName = users.get(i).getName();
             if(name.equals(uName)){
                return true;
            } 
        }
        return false;
    }
     
    public String getNameById(String id){
        int leng = users.size();
        
        for(int i = 0 ; i < leng ; i ++){
             String uId = users.get(i).getId();
             if(id.equals(uId)){
                return users.get(i).getName();
            } 
        }
          
        return null;
    }
    
    public String getIdByName(String name){
        int leng = users.size();
        
        for(int i = 0 ; i < leng ; i ++){
             String uName = users.get(i).getName();
             if(name.equals(uName)){
                return users.get(i).getId();
            } 
        }
        return null;
    }
    
    public User getUser(String id){
        int leng = users.size();
        
        for(int i = 0 ; i < leng ; i ++){
             
             User user = users.get(i);
             String uId = user.getId();
             if(id.equals(uId)){
                return user;
            } 
        }
        return null;
    }
}
