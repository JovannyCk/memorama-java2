/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

/**
 *
 * @author user
 */
public class Gamer {
    private String nombre="Anónimo";
    private int tiempo=0;
    private int score=0;
    private long fecha=0;
    
    //getters y setters para el juego
    
    
    public void setName(String Name){
        nombre=Name;
    }
    
    public void setDuration(int Duration){
        tiempo=Duration;
    }
    
    public void setScore(int Score){
        score=Score;
    }
    
    public void setDateTime(long DateTime){
        fecha=DateTime;
    }
    
    public String getName(){
        return nombre;
    }
    
    public int getDuration(){
        return tiempo;
    }
    
    public int getScore(){
        return score;
    }
    
    public long getDateTime(){
        return fecha;
    }
}
