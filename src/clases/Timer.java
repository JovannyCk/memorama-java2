/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 * @author user
 */

//clase para el tiempo del juego

public class Timer extends Thread{
    int timeseg=0;
    int limit=1000;
    JLabel etitiempo=null;
    private int hors=0;
    private int mins=0;
    private int segs=0;
    
    public Timer(){
    }
    
    public Timer(int lim){
        limit=lim;
    }
    
    public void run(){
        while(true){
            if(limit>timeseg){
            timeseg++;
            }else{
                this.suspend();
            }
            try {
                Thread.sleep(1000);
                //System.out.println("Tiempo: "+timeseg+" segundos");
                if(etitiempo!=null){
                    etitiempo.setText(this.getTime(timeseg));
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public void setJLabel(JLabel TimeTag){
        etitiempo=TimeTag;
    }
    
    public void reStart(){
        this.suspend();
        timeseg=0;
        this.resume();
    }
    
    public int getSecondsTime(){
        return timeseg;
    }
    
    public boolean isFinished(){
        if(limit==timeseg){
            return true;
        }
        return false;
    }
    
    public String getTime(int dur){
       
       String Durat="00:00:00";
       String S="";
       double a=0;
       double a2=0;

               a=dur/3600;
               S=""+a;
               hors=Integer.parseInt(S.substring(0,S.lastIndexOf(".")));
               a2=((dur)-(hors*3600))/60;
               S=""+a2;
               mins=Integer.parseInt(S.substring(0,S.lastIndexOf(".")));
               segs=dur-(hors*3600)-(mins*60);

       if(hors<=9){
           Durat="0"+hors+":";
       }else{
           Durat=hors+":";
       }
       
       if(mins<=9){
           Durat=Durat+"0"+mins+":";
       }else{
           Durat=Durat+mins+":";
       }
       
       if(segs<=9){
           Durat=Durat+"0"+segs+"";
       }else{
           Durat=Durat+segs+"";
       }
       
       S=null;
       return Durat;
       
    }
    
    
}
