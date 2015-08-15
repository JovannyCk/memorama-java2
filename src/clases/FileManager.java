/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.io.File;
import java.io.RandomAccessFile;

/**
 *
 * @author user
 */
public class FileManager {
    
    private String filecfg="config.dll";
    private String filegoal="score.sog"; //archivo donde se guardara los records
    private RandomAccessFile Read=null;
    private RandomAccessFile Write=null;
    File conffile=new File(filecfg);
    
    
    public boolean addGamer(Gamer Jugador) {
        try{
        if(Jugador!=null){
        Write=new RandomAccessFile(filegoal,"rw"); 
           Write.seek(Write.length());
           Write.writeBytes("Next\n"); //datos a guardar del juego
           Write.writeUTF(Jugador.getName()); //nombre
           Write.writeInt(Jugador.getDuration()); //duracion 
           Write.writeInt(Jugador.getScore()); // record
           Write.writeLong(Jugador.getDateTime()); //fecha
           
        Write.close();
        Write=null;
        }
        }catch(Exception e){ //atrapa el error
            return false;
        }
        return true;
    }
    
    
    private Gamer getGamer() {
        Gamer Jugador=new Gamer();
        try{
        Jugador.setName(Read.readUTF());
        Jugador.setDuration(Read.readInt());
        Jugador.setScore(Read.readInt());
        Jugador.setDateTime(Read.readLong());
        }catch(Exception e){
            System.err.println(e);
        }
        return Jugador;
    }
    
    public Gamer[] getGamers() {
        try{
        int c=0;
        Read=new RandomAccessFile(filegoal,"r");
        while(Read.readLine()!=null){
            c++;
        }
        c=c-1;
        Read.close();
        Gamer Jugadores[]=new Gamer[c];
        Read=new RandomAccessFile(filegoal,"r");
        
        for(int i=0;i<c;i++){
            Read.readLine();
            Jugadores[i]=this.getGamer();
        }
        Read.close();
        return Jugadores;
        }catch(Exception e){
            System.err.println(e);
        }
        return null;
    }
    
    public boolean saveConfig(String folder, int filas, int columnas){
        
        File temp=new File(filecfg+".tmp");
        try {
            Write=new RandomAccessFile(temp,"rw");
            Write.writeUTF(folder);
            Write.writeInt(filas);
            Write.writeInt(columnas);
            Write.close();
            
        if(conffile.exists()){
            conffile.delete();
        }
        temp.renameTo(conffile);
        return true;
        }catch(Exception e){
            System.err.println(e);
            return false;
        }
        
    }
    
    public boolean loadConfig(Configurar conf){
        try {
            if(conffile.exists()){
            Read=new RandomAccessFile(filecfg,"r");
            conf.setDataConfig(Read.readUTF(),Read.readInt(), Read.readInt());
            Read.close();
            return true;
            }else{
                if(conf.Folders.length>0){
                this.saveConfig(conf.Folders[0].getName(),4,4);
                System.out.println("Creando Configuracion Predeterminada!...");
                return this.loadConfig(conf);
                }else{
                    return false;
                }
            }
        }catch(Exception e){
            System.err.println(e);
            return false;
        }
    }

}
