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
public class User {
   
    private String id;
    private String name;
    private String vision_d_id;
    private String bio_d_id;
    private String voice_d_id;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        vision_d_id = "cam0";
        bio_d_id = "gear0";
        voice_d_id = "mic0";
    }

    public void setVision_d_id(String vision_d_id) {
        this.vision_d_id = vision_d_id;
    }

    public String getVision_d_id() {
        return vision_d_id;
    }

    public void setBio_d_id(String bio_d_id) {
        this.bio_d_id = bio_d_id;
    }

    public String getBio_d_id() {
        return bio_d_id;
    }

    public void setVoice_d_id(String voice_d_id) {
        this.voice_d_id = voice_d_id;
    }

    public String getVoice_d_id() {
        return voice_d_id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    
    
    
}
