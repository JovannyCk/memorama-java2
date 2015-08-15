/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class Principal extends javax.swing.JFrame implements ActionListener{
private Sobre about=null;
private Configurar conf=null;
private Puntaje punt=null;
private FileManager FM=new FileManager();

int n=0;
int m=0;
int f=0,c=0;
int in=0;
private JButton MatBut [][]=new JButton[n][n];
private int Matriz[][];
private ImageIcon Img[];
private ImageIcon un=new ImageIcon("Images/unitec.jpg");
private int dim=110;
private File Carpeta=new File("Images");
private File Imagenes[];
private int showTime=1000;
private Goal Puntos=null;
private Timer tiempo;
private WaitPlease wait;
private int f1=-5;
private int f2=-5;
private int pairsfound=0;
private int mod=1;
private String nomb="Anónimo";
private Gamer Jugador=null;


    /**
     * Creates new form Memorama
     */
    public Principal() {
        
        conf=new Configurar(this,true);
        Carpeta=conf.CarpetaInterna;
        if(Carpeta==null ^ Carpeta.exists()==false){
            Carpeta.mkdir();
            JOptionPane.showMessageDialog(this, "No hay Imagenes!, Debe Crear una Nueva Carpeta con Imagenes dentro de la Carpeta 'Images'...\nEl Sistema terminara su ejecucion.\n... Hasta Pronto!...","Error del Sistema",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        
        initComponents();
        this.setIconImage (new ImageIcon(getClass().getResource("Icon.png")).getImage());
        this.setLocationRelativeTo(null);
        
        
    }

    private ImageIcon resizeImage(ImageIcon Imag){
        return Imag = new ImageIcon(Imag.getImage().getScaledInstance(dim-20, dim-20,Image.SCALE_DEFAULT));
    }
    
    private void Init(){
        try{
        
        this.finish();
        while(nomb.equals("Anónimo")==true ^ nomb.replaceAll(" ", "").equals("")==true){
            nomb=JOptionPane.showInputDialog("Digite su Nombre: ");
        }
        Jugador=new Gamer();
        Jugador.setName(nomb);
        Jugador.setDateTime(new Date().getTime());
        
        conf.load();
        n=conf.getFilas();
        m=conf.getColumnas();
        in=conf.getImagenesNecesarias();
        MatBut=new JButton[n][m];
        Matriz=new int[n][m];
        
        if(tiempo!=null && wait!=null){
        tiempo.stop();
        tiempo=null;
        wait.stop();
        wait=null;
        }
        mod=1;
        pairsfound=0;
        f1=-5; f2=-5;
        
        PanelP.setLayout(new GridLayout(n,m));
        un=this.resizeImage(un);
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                Matriz[i][j]=-5;
                MatBut[i][j]=new JButton(un);
                MatBut[i][j].setSize(dim,dim);
                MatBut[i][j].addActionListener(this);
                PanelP.add(MatBut[i][j]);
            }
        }
        loadImages();
        setRandomImage();
        lbTiempo.setText("00:00:00");
        lbPunto.setText(nomb+": 0 Puntos");
        Puntos=new Goal(Img.length,1);
        tiempo=new Timer(Puntos.getTime());
        tiempo.setJLabel(lbTiempo);
        tiempo.start();
        wait= new WaitPlease();
        showTime=Puntos.getShowTime();
        wait.start();
        tiempo.reStart();
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Ha Ocurrido un fallo grave en el sistema...\nEl Sistema terminara su ejecucion.\n... Hasta Pronto!...","Error del Sistema",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
    
    public void loadImages(){
        File fi[]= Carpeta.listFiles();
        int nie=0;
        
        
        for(int i=0;i<fi.length;i++){
            if(new ImageFilter().accept(fi[i])){
                nie++;
            }
        }
        
        if(nie>=in){
        Imagenes=new File[nie]; int j=0;
        
        for(int i=0;i<fi.length;i++){
            if(new ImageFilter().accept(fi[i])){
                Imagenes[j]=fi[i]; 
                j++;
            }
        }
        j=0;
        Img=new ImageIcon[in];
        for(int i=0;i<in;i++){
                Img[i]=new ImageIcon(""+Imagenes[i].getAbsolutePath());
                Img[i]=this.resizeImage(Img[i]);
        }
        System.out.println(in+" Imagenes Necesarias");
        }else{
            JOptionPane.showMessageDialog(this,"No hay imagenes suficientes!. Matriz de "+n+"*"+m);
            System.exit(0);
        }
    }
   
    
    
    private void setRandomImage(){
        int numero=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(Matriz[i][j]==-5){
                    double num=Math.random()*((n*m)/2);
                    numero=(int)num;
                    while(this.count(numero)>=2){
                        num=Math.random()*((n*m)/2);
                        numero=(int)num;
                    }
                    Matriz[i][j]=numero;
                    
                }
            }
        }
    }
    
    private int count(int numero){
        int con=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(Matriz[i][j]==numero){
                    con++;
                }
            }
        }
        return con;
    }
    
    private void eventButton(int i,int j){
        if(MatBut[i][j].getIcon().equals(un)){
            MatBut[i][j].setIcon(Img[Matriz[i][j]]);
                        
            if(f1==-5){
                f1=Matriz[i][j];
            } else  if(f2==-5){
                f2=Matriz[i][j];
            }
            
        }
        
    }

    
    
    private void resetButton(int numero){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(Matriz[i][j]==numero){
                    MatBut[i][j].setIcon(un);
                }
            }
        }
    }
    
    
    private void finish(){
        if(tiempo!=null){
        tiempo.suspend();
        wait.suspend();
        int t=tiempo.getSecondsTime();
        int po=Puntos.getTotal(t);
        
        if(pairsfound==Img.length){
        JOptionPane.showMessageDialog(this, "Felicitaciones!, Su puntaje es: "+po);
        Jugador.setDuration(t);
        Jugador.setScore(po);
        FM.addGamer(Jugador);
        }
        tiempo=null;
        wait=null;
        
        }
        lbTiempo.setText("00:00:00");
        lbPunto.setText("");
        PanelP.removeAll();
        PanelP.repaint();
        
    }
    
    private void compare(){
            
        if(f1!=-5 && f2!=-5){
                
            mod=0;
                
                try {
                        Thread.sleep(showTime);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                if(f1==f2){
                    
                    pairsfound++;
                    System.out.println(pairsfound+" Parejas");
                    Puntos.setGoalPlus(f1);
                    if(pairsfound==Img.length){
                        this.finish();
                    }
                    
                }else{
                    resetButton(f1);
                    resetButton(f2);
                    Puntos.setGoalMinus(f1);
                    Puntos.setGoalMinus(f2);
                }
                
                mod=1;
                f1=-5; f2=-5;
                this.lbPunto.setText(nomb+": "+Puntos.getTotal(tiempo.getSecondsTime())+" Puntos");
        }

    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelP = new javax.swing.JPanel();
        lbTiempo = new javax.swing.JLabel();
        lbPunto = new javax.swing.JLabel();
        BarraMenu = new javax.swing.JMenuBar();
        MGame = new javax.swing.JMenu();
        NewGame = new javax.swing.JMenuItem();
        AbortGame = new javax.swing.JMenuItem();
        Puntaje = new javax.swing.JMenuItem();
        MExit = new javax.swing.JMenuItem();
        MConfig = new javax.swing.JMenu();
        Config = new javax.swing.JMenuItem();
        MHelp = new javax.swing.JMenu();
        Acerca = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Memorama");
        setMinimumSize(new java.awt.Dimension(500, 540));

        PanelP.setBorder(javax.swing.BorderFactory.createTitledBorder("Memorama"));
        PanelP.setMinimumSize(new java.awt.Dimension(480, 444));

        javax.swing.GroupLayout PanelPLayout = new javax.swing.GroupLayout(PanelP);
        PanelP.setLayout(PanelPLayout);
        PanelPLayout.setHorizontalGroup(
            PanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelPLayout.setVerticalGroup(
            PanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 421, Short.MAX_VALUE)
        );

        lbTiempo.setText("00:00:00");

        lbPunto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbPunto.setText("Jugador 1: 0 puntos");

        MGame.setText("Juego");

        NewGame.setText("Nuevo Juego");
        NewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewGameActionPerformed(evt);
            }
        });
        MGame.add(NewGame);

        AbortGame.setText("Abandonar Juego");
        AbortGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbortGameActionPerformed(evt);
            }
        });
        MGame.add(AbortGame);

        Puntaje.setText("Puntajes");
        Puntaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PuntajeActionPerformed(evt);
            }
        });
        MGame.add(Puntaje);

        MExit.setText("Salir");
        MExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MExitActionPerformed(evt);
            }
        });
        MGame.add(MExit);

        BarraMenu.add(MGame);

        MConfig.setText("Configuracion");

        Config.setText("Configuracion");
        Config.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfigActionPerformed(evt);
            }
        });
        MConfig.add(Config);

        BarraMenu.add(MConfig);

        MHelp.setText("Ayuda");

        Acerca.setText("Acerca de...");
        Acerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcercaActionPerformed(evt);
            }
        });
        MHelp.add(Acerca);

        BarraMenu.add(MHelp);

        setJMenuBar(BarraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                        .addComponent(lbPunto, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTiempo)
                    .addComponent(lbPunto))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewGameActionPerformed
        this.Init();
    }//GEN-LAST:event_NewGameActionPerformed

    private void AbortGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbortGameActionPerformed
        this.finish();
    }//GEN-LAST:event_AbortGameActionPerformed

    private void AcercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcercaActionPerformed
        about=new Sobre(this,true);
        about.show();
    }//GEN-LAST:event_AcercaActionPerformed

    private void ConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfigActionPerformed
        conf.show();
    }//GEN-LAST:event_ConfigActionPerformed

    private void PuntajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PuntajeActionPerformed
        punt=new Puntaje(this,true);
        punt.show();
    }//GEN-LAST:event_PuntajeActionPerformed

    private void MExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_MExitActionPerformed

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(mod==1){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(e.getSource().equals(MatBut[i][j])){
                    eventButton(i,j);
                }
            }
        }
        }            
                                
    }
    
    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AbortGame;
    private javax.swing.JMenuItem Acerca;
    private javax.swing.JMenuBar BarraMenu;
    private javax.swing.JMenuItem Config;
    private javax.swing.JMenu MConfig;
    private javax.swing.JMenuItem MExit;
    private javax.swing.JMenu MGame;
    private javax.swing.JMenu MHelp;
    private javax.swing.JMenuItem NewGame;
    private javax.swing.JPanel PanelP;
    private javax.swing.JMenuItem Puntaje;
    private javax.swing.JLabel lbPunto;
    private javax.swing.JLabel lbTiempo;
    // End of variables declaration//GEN-END:variables
    
    
    
    
    
    
    
    class WaitPlease extends Thread{
        public void run(){
            while(true){
                if(tiempo.isFinished()){
                    finish();
                }else{
                    compare();
                }
            }
        }
    }
    
}
