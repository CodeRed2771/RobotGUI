/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robotgui;

import communications.DSMListener;
import communications.DataStreamingModule;
import communications.SRAListener;
import communications.SynchronizedRegisterArray;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author laptop
 */
public class SRAandDSM extends javax.swing.JPanel implements SRAListener,DSMListener{

    DataStreamingModule dataStreamingModule;
    SynchronizedRegisterArray synchronizedRegisterArray;
    ArrayList<String> listItems = new ArrayList();
    private long lastSRAListRefresh = System.currentTimeMillis();

    /**
     * Creates new form SRA
     */
    public SRAandDSM(){
        initComponents();
    }

    public void init(SynchronizedRegisterArray synchronizedRegisterArray,
            DataStreamingModule dataStreamingModule) {
        this.dataStreamingModule = dataStreamingModule;
        this.synchronizedRegisterArray = synchronizedRegisterArray;
        dataStreamingModule.addDSMListener(this);
        synchronizedRegisterArray.addSRAListener(this);
        jList1.setListData(new String[0]);
    }

    private void refeshComboBox() {
        String[] names = dataStreamingModule.getStreamNames();
        listItems = new ArrayList<String>(names.length);
        Arrays.sort(names);
        listItems.addAll(Arrays.asList(names));
        if (listItems.size() > 0) {
            int index = jList2.getSelectedIndex();
            jList2.setListData(listItems.toArray());
            if (index != -1) {
                jList2.setSelectedIndex(index);
            }
        } else {
            jList2.setListData(new Object[0]);
        }
    }
    
    private void refreshGraph() {
        if (jList2.getSelectedIndex() != -1) {
            graph1.removeAllStreams();
            graph1.addStream(
                    dataStreamingModule.getStream((String)listItems.get(jList2.getSelectedIndex())),
                    Color.BLACK, 0.5, 1.0 / Double.parseDouble(jTextField2.getText()), true);
        }
    }

    private void refreshSRAList() {
        if (lastSRAListRefresh + 2000 < System.currentTimeMillis()) {
            lastSRAListRefresh = System.currentTimeMillis();
            String[] names = synchronizedRegisterArray.getRegisterNames();
            for (int i = 0; i < names.length; i++) {
                names[i] = names[i] + ": " + synchronizedRegisterArray.get(names[i]);
            }
            jList1.setListData(names);
        }
    }
    
    @Override
    public void alertToSRAUpdates() {
        refreshSRAList();
    }

    @Override
    public void alertToDSMUpdates() {
    }

    @Override
    public void alertToNewStreams() {
        refeshComboBox();
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
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        graph1 = new robotgui.Graph();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel1.setText("SRAs");

        jLabel2.setText("Data Streams");

        javax.swing.GroupLayout graph1Layout = new javax.swing.GroupLayout(graph1);
        graph1.setLayout(graph1Layout);
        graph1Layout.setHorizontalGroup(
            graph1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 154, Short.MAX_VALUE)
        );
        graph1Layout.setVerticalGroup(
            graph1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton1.setText("Add");

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList2ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList2);

        jLabel3.setText("scale:");

        jTextField2.setText("100");
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField1)))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(graph1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(graph1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(jLabel3)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList2ValueChanged
        refreshGraph();
    }//GEN-LAST:event_jList2ValueChanged

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        refreshGraph();
    }//GEN-LAST:event_jTextField2KeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private robotgui.Graph graph1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}