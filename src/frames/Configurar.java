package frames;


import java.io.File;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Configurar extends javax.swing.JDialog {

private Principal Padre=null;
private FileManager FM=new FileManager();
private String file="skin.cfg";
private File Open=new File(file);
private RandomAccessFile Esc=null;
private RandomAccessFile Lee=null;
private String sel="";
private int cmi=4;
public File Carpeta=new File("Images/");
public File CarpetaInterna=null;
public File Folders[]=null;
public int CountImgs[]=null;
    /**
     * Creates new form Configurar (para las imagenes del memorama)
     */
    public Configurar(Principal parent, boolean modal) {
        
        super(parent, modal);
        Padre=parent;
        initComponents();
        this.setLocationRelativeTo(null);
        this.loadImageFolders();
        this.load();
        this.setImagenesDisponibles();
        
    }
    
    public int getFilas(){
        try{
            return Integer.parseInt(txFilas.getText());
        }catch(Exception e){
            return 0;
        }
    }
    
    public int getColumnas(){
        try{
            return Integer.parseInt(txColumnas.getText());
        }catch(Exception e){
            return 0;
        }
    }
    
    public int getImagenesNecesarias(){
        return (this.getFilas()*this.getColumnas())/2;
    }
    
    
    private boolean save(){
        try {
            Esc=new RandomAccessFile(Open,"rw");
            Esc.close();
            FM.saveConfig(""+Select.getSelectedItem(), Integer.parseInt(""+txFilas.getText()), Integer.parseInt(""+txColumnas.getText()));
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Configurar.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void load(){
        try {
            Lee=new RandomAccessFile(Open,"r");
            sel=Lee.readUTF();
            Lee.close();
            FM.loadConfig(this);
            CarpetaInterna=new File(Carpeta.getName()+"/"+Select.getSelectedItem());
        } catch (Exception ex) {
            Logger.getLogger(Configurar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadImageFolders(){
        
        Select.removeAllItems();
        File fi[]= Carpeta.listFiles();
        int c=0;
        int nie=0;
        int k=0;
        
        for(int i=0;i<fi.length;i++){
            if(fi[i].isDirectory()){
                
                for(int j=0;j<fi[i].listFiles().length;j++){
                    if(new ImageFilter().accept(fi[i].listFiles()[j])){
                        nie++;
                    }
                    
                }
                if(nie>=4){
                    Select.addItem(""+fi[i].getName());
                    c++;
                }
                nie=0;
            
            }
        }
        
        nie=0;
        
        CountImgs=new int[c];
        Folders=new File[c];
        c=0;
        for(int i=0;i<fi.length;i++){
            if(fi[i].isDirectory()){
                for(int j=0;j<fi[i].listFiles().length;j++){
                    if(new ImageFilter().accept(fi[i].listFiles()[j])){
                        nie++;
                    }
                    
                }
                if(nie>=4){
                CountImgs[c]=nie;
                Folders[c]=fi[i];
                    c++;    
                }
                nie=0;
            
            }
        }
        
    }
    
    private boolean setImagenesDisponibles(){
        try{
        lbImagDisp.setText(""+CountImgs[Select.getSelectedIndex()]);
        }catch(Exception e){
            
        }
        return false;
    }
    
   
   
    
    private boolean validation(){
        if(txFilas.getText().equals("")){
           JOptionPane.showMessageDialog(this,"No. de Fila es un Numero");
           return false;
        }
        
        if(txColumnas.getText().equals("")){
           JOptionPane.showMessageDialog(this,"No. de Columna es un Numero");
           return false;
        }
        
        if(this.getFilas()>6 ^ this.getFilas()<2){
            JOptionPane.showMessageDialog(this,"El No. de Filas debe estar en el Rango entre 2 y 6!");
            return false;
        }
        
        if(this.getColumnas()>6 ^ this.getColumnas()<2){
            JOptionPane.showMessageDialog(this,"El No. de Columnas debe estar en el Rango entre 2 y 6!");
            return false;
        }
        
        if((this.getFilas()*this.getColumnas())%2!=0){
            JOptionPane.showMessageDialog(this,"Debe ser una Matriz con un numero de casillas Par!");
            return false;
        }
        
        if(this.getImagenesNecesarias()>CountImgs[Select.getSelectedIndex()]){
            JOptionPane.showMessageDialog(this,"No hay Imagenes Suficientes");
        }
        
        return true;
    }
    
    
    
    
    
    public void setDataConfig(String folder, int filas,int columnas){
        Select.setSelectedItem(folder);
        txFilas.setText(""+filas);
        txColumnas.setText(""+columnas);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GB = new javax.swing.ButtonGroup();
        Pestanas = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Select = new javax.swing.JComboBox();
        lbImagDisp = new javax.swing.JLabel();
        txFilas = new javax.swing.JFormattedTextField();
        txColumnas = new javax.swing.JFormattedTextField();
        Aceptar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        Aplicar = new javax.swing.JButton();

        setTitle("Configuracion");
        setAlwaysOnTop(true);
        setResizable(false);

        jLabel1.setText("Tema de Juego:");

        jLabel2.setText("Imagenes Disponibles");

        jLabel4.setText("No. de Filas");

        jLabel5.setText("No. de Columnas");

        Select.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Colombia" }));

        lbImagDisp.setText("0");

        txFilas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txFilas.setText("2");

        txColumnas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txColumnas.setText("2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Select, 0, 160, Short.MAX_VALUE)
                    .addComponent(lbImagDisp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txFilas)
                    .addComponent(txColumnas))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbImagDisp))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txFilas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(144, Short.MAX_VALUE))
        );

        Pestanas.addTab("Opciones", jPanel1);

        Aceptar.setText("Aceptar");
        Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarActionPerformed(evt);
            }
        });

        Cancelar.setText("Cancelar");
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });

        Aplicar.setText("Aplicar");
        Aplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AplicarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pestanas)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Aplicar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Aceptar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Pestanas, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Aceptar)
                    .addComponent(Cancelar)
                    .addComponent(Aplicar))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarActionPerformed
        if(validation()){
        save();
        this.hide();
        }
    }//GEN-LAST:event_AceptarActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        this.hide();
    }//GEN-LAST:event_CancelarActionPerformed

    private void AplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AplicarActionPerformed
        if(validation()){
        save();
        }
    }//GEN-LAST:event_AplicarActionPerformed

    /**
     * @param args the command line arguments
     */

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aceptar;
    private javax.swing.JButton Aplicar;
    private javax.swing.JButton Cancelar;
    private javax.swing.ButtonGroup GB;
    private javax.swing.JTabbedPane Pestanas;
    private javax.swing.JComboBox Select;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbImagDisp;
    private javax.swing.JFormattedTextField txColumnas;
    private javax.swing.JFormattedTextField txFilas;
    // End of variables declaration//GEN-END:variables
}
