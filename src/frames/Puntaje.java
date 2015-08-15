/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;


import java.io.File;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class Puntaje extends javax.swing.JDialog {
private FileManager FM=new FileManager();
private DefaultTableModel Model;
    
    /**
     * Creates new form Score (Puntaje de los jugadores)
     */
    public Puntaje(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        if(new File("score.sog").exists()){
        getScores();
        }else{
            JOptionPane.showMessageDialog(this,"No hay Registros!.");
            this.dispose();
        }
    }

    
    private void getScores(){
        Tabla.removeAll();
        Model=new DefaultTableModel();
        Model.addColumn("Nombre del Jugador");
        Model.addColumn("Puntos");
        Model.addColumn("Tiempo");
        Model.addColumn("Fecha");
        
        Gamer Jugadores[]=FM.getGamers();
        
        String Fila[]=new String[Model.getColumnCount()];
        Date Fecha=null;
        for(int i=0;i<Jugadores.length;i++){
            if(Jugadores[i]!=null){
            Fila[0]=""+Jugadores[i].getName();
            Fila[1]=""+Jugadores[i].getScore();
            Fila[2]=""+new Timer().getTime(Jugadores[i].getDuration());
            Fecha=new Date();
            Fecha.setTime(Jugadores[i].getDateTime());
            Fila[3]=""+Fecha.toLocaleString();
            Model.addRow(Fila);
            }
        }
        Tabla.setModel(Model);
    
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Puntajes");
        setAlwaysOnTop(true);

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Nombre del Jugador", "Puntos", "Tiempo", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tabla);
        if (Tabla.getColumnModel().getColumnCount() > 0) {
            Tabla.getColumnModel().getColumn(0).setResizable(false);
            Tabla.getColumnModel().getColumn(1).setResizable(false);
            Tabla.getColumnModel().getColumn(2).setResizable(false);
            Tabla.getColumnModel().getColumn(3).setResizable(false);
        }

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(14, 14, 14)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
