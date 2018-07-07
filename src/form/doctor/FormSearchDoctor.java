/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.doctor;

import form.patient.*;
import controller.Controller;
import domain.Doctor;
import domain.GenericDomainObject;
import domain.Patient;
import form.doctor.model.DoctorTableModel;
import form.patient.model.PatientTableModel;
import java.awt.BorderLayout;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;
import session.Session;

/**
 *
 * @author Mira
 */
public class FormSearchDoctor extends javax.swing.JPanel {

    /**
     * Creates new form FormSearchPatient
     */
    public FormSearchDoctor() {
        initComponents();
        makeTable(new ArrayList<>());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCalendar1 = new com.toedter.calendar.JCalendar();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoctors = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCriteria = new javax.swing.JTextField();
        btnDetails = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        tblDoctors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblDoctors);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Criteria for search doctor (enter name, surname, gender, date birth, date employment, mobile number or amount of salary of doctor) :");

        btnDetails.setText("Show details");
        btnDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel2.setText("Pick the row of doctor you want to see detailed");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 947, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCriteria, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(btnDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCriteria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(btnDetails)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            String criteria = txtCriteria.getText();
            List<GenericDomainObject> listDoctors = Controller.getInstanca().getDoctorsWithCriteria(criteria);

            if (!listDoctors.isEmpty()) {
                makeTable(listDoctors);
                if (evt.getSource().equals(btnSearch)) {
                    JOptionPane.showMessageDialog(this, "Sistem found doctors");
                }

            } else {
                
                if (evt.getSource().equals(btnSearch)) {
                    JOptionPane.showMessageDialog(this, "There is no dostors with that criteria");
                }
            }

        } catch (Exception ex) {
            if (evt.getSource().equals(btnSearch)) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed
         int row = tblDoctors.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Choose row!");
        } else {
            DoctorTableModel model = (DoctorTableModel) tblDoctors.getModel();
            Doctor d = (Doctor) model.getDoctor(row);
            Session.getInstance().put("d", d);
            FormAddDoctor f = new FormAddDoctor();
            Window w = SwingUtilities.getWindowAncestor(FormSearchDoctor.this);
            JDialog jd = new JDialog((JDialog) w, "Showing the doctor", true);
            jd.setLayout(new BorderLayout());
            jd.add(f, BorderLayout.CENTER);
            jd.pack();
            jd.setLocationRelativeTo(null);
            jd.setVisible(true);
            //while(jd.isVisible()){
                
            //}
            //btnSearchActionPerformed(evt);

        }
    }//GEN-LAST:event_btnDetailsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetails;
    private javax.swing.JButton btnSearch;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDoctors;
    private javax.swing.JTextField txtCriteria;
    // End of variables declaration//GEN-END:variables

    private void makeTable(List<GenericDomainObject> listDoctors) {
         TableModel tm = new DoctorTableModel(listDoctors);
        tblDoctors.setModel(tm);
    }
}
