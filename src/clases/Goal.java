/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

/**
 *
 * @author user
 */
public class Goal {
    int level=1;
    int defaultTime=1000000;
    int defaultShowTime=1000;
    private int time=this.defaultTime;
    private int showtime=this.defaultShowTime;
    private int []goals=null;
    private int total=0;
    
    public Goal(int length, int lvl){
        goals=new int[length];
        if(lvl<=10){
        level=lvl;
        }
        this.setData();
    }
    
    
    private void setData(){
        time=this.defaultTime; showtime=this.defaultShowTime;
        time=time/level;
        showtime=showtime-(level*50);
        for(int i=0;i<goals.length;i++){
            goals[i]=100;
        }
    }
    
    public int getTime(){
        return (int)time/1000;
    }
    
    public int getShowTime(){
        return showtime;
    }
    
    public int getMinimum(){
        return (int)(goals.length*100*level);
    }
    
    public int getTotal(int ftime){
        return (int)total*(time/1000)/ftime;
    }
    
    public void setGoalMinus(int i){
        if(goals[i]>5 && i>=0 && i<goals.length){
        goals[i]=goals[i]-8;
        }
    }
    
    public void setGoalPlus(int i){
        if(goals[i]>5 && i>=0 && i<goals.length){
        total=total+goals[i];
        }
    }   
}
