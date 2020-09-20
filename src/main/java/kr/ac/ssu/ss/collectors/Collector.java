/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kr.ac.ssu.ss.collectors;

/**
 *
 * @author lssang
 */
public interface Collector {
    public int startCollecting();
    public int stopCollecting();
    
    public int getStatus(); // return 1 or 2 
}
