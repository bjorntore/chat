/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ChatLogic.User;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author BjørnTore
 */
public class UserPanel extends javax.swing.JPanel {

    /**
     * Creates new form UserPanel
     */
    private boolean contactOptionsVisible = false;
    private User user;
    private JPanel test2 = new JPanel();
    private JPanel test = new JPanel();

    public UserPanel(User user) {
        this.user = user;
        initComponents();
        jLabel1.setText(user.getName());
        initContactOptionPanels();

        add(test);
        add(test2);
        setVisible(true);
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public User getUser(){
        return user;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setBackground(new java.awt.Color(221, 221, 221));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(212, 212, 212)));
        setMaximumSize(new java.awt.Dimension(192, 44));
        setMinimumSize(new java.awt.Dimension(192, 44));
        setPreferredSize(new java.awt.Dimension(196, 44));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(216, 216, 216));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(68, 68, 68));
        jLabel1.setText("Unnamed");
        jLabel1.setMaximumSize(new java.awt.Dimension(190, 28));
        jLabel1.setMinimumSize(new java.awt.Dimension(190, 28));
        jLabel1.setPreferredSize(new java.awt.Dimension(190, 28));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        jLabel1.setForeground(Color.WHITE);
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        jLabel1.setForeground(new Color(68, 68, 68, 255));
        setSize(194, 44);
        setBackground(new Color(221, 221, 221, 255));
        revalidate();
        contactOptionsVisible = false;

    }//GEN-LAST:event_formMouseExited

    private void initContactOptionPanels() {
        test.setBounds(3, 44, 186, 41);
        test.setBackground(new Color(248, 248, 248, 255));
        test.setLayout(null);
        JLabel test1Label = new JLabel("New Message");
        test1Label.setBounds(12, 11, 186, 22);
        test1Label.setFont(new java.awt.Font("Segoe UI", 0, 14));
        test.setCursor(new Cursor(Cursor.HAND_CURSOR));
        test.add(test1Label);
        test.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230, 255)));

        test2.setBounds(3, 88, 186, 41);
        test2.setBackground(new Color(248, 248, 248, 255));
        test2.setLayout(null);
        JLabel test2Label = new JLabel("Send File");
        test2Label.setBounds(12, 11, 186, 22);
        test2Label.setFont(new java.awt.Font("Segoe UI", 0, 14));
        test2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        test2.add(test2Label);
        test2.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230, 255)));
    }

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (getComponentAt(evt.getPoint()).equals(test)) {
            JOptionPane.showMessageDialog(this, "Sending message to " + user.getName());
        } else if (getComponentAt(evt.getPoint()).equals(test2)) {
            JOptionPane.showMessageDialog(this, "Sending file to " + user.getName());
        }
        if (!contactOptionsVisible) {
            setSize(194, 133);
            setBackground(new Color(166, 166, 166, 255));
            contactOptionsVisible = true;
        } else {
            setSize(194, 44);
            setBackground(new Color(221, 221, 221, 255));
            revalidate();
            contactOptionsVisible = false;

        }

    }//GEN-LAST:event_formMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
