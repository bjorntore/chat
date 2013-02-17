/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ChatLogic.User;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
//import com.explodingpixels.macwidgets.IAppWidgetFactory;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author BjørnTore
 */
public class ChatMainFrame extends javax.swing.JFrame implements PropertyChangeListener {

    private PropertyChangeSupport chatMainFramePCS;
    private ArrayList<UserPanel> userPanels = new ArrayList<>();

    /**
     * Creates new form ChatMainFrame
     */
    public ChatMainFrame() {
        chatMainFramePCS = new PropertyChangeSupport(this);
        initComponents();
        jPanel2.setLayout(new GridLayout(10, 1, 0, 2));
    }

    public void refreshUserList(ArrayList<User> refreshedListOfUsers) {
        //Add UserPanel for new users
        boolean somethingChanged = false;
        for (User user : refreshedListOfUsers) {
            boolean wasFound = false;
            for (UserPanel userPanel : userPanels) {
                if (userPanel.getUser().getName().equalsIgnoreCase(user.getName())) {
                    wasFound = true;
                }
            }
            if (!wasFound) {
                jPanel2.add(new UserPanel(user));
                userPanels.add(new UserPanel(user));
                somethingChanged = true;
            }
        }

        //Remove UserPanels for disconnected users
        for (UserPanel userPanel : userPanels) {
            boolean wasFound = false;
            for (User user : refreshedListOfUsers) {
                if (user.getName().equalsIgnoreCase(userPanel.getUser().getName())) {
                    wasFound = true;
                }
            }
            if (!wasFound) {
                for (int i = 0; i < userPanels.size(); i++) {
                    if (((UserPanel) jPanel2.getComponent(i)).getUser().getName().equalsIgnoreCase(userPanel.getUser().getName())) {
                        jPanel2.remove(i);
                        userPanels.remove(userPanel);
                        userPanel = null;
                        somethingChanged = true;
                    }
                }
            }
        }
        if (somethingChanged) {
            jPanel2.validate();
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        //IAppWidgetFactory.makeIAppScrollPane(jScrollPane1);
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(13);
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        //IAppWidgetFactory.makeIAppScrollPane(jScrollPane2);
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(13);
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ChatClient");
        setForeground(java.awt.Color.black);
        setMinimumSize(new java.awt.Dimension(400, 200));

        jPanel1.setBackground(new java.awt.Color(244, 244, 244));
        jPanel1.setMinimumSize(new java.awt.Dimension(390, 160));

        jScrollPane1.setBorder(null);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(233, 231, 231)));
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(233, 231, 231)));
        jTextField1.setNextFocusableComponent(jButton1);
        jTextField1.setPreferredSize(new java.awt.Dimension(59, 16));
        jTextField1.setSelectionColor(new java.awt.Color(204, 204, 204));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jButton1.setText("Send");
        jButton1.setFocusable(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });

        jScrollPane2.setBackground(new java.awt.Color(244, 244, 244));
        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setAutoscrolls(true);
        jScrollPane2.setHorizontalScrollBar(null);
        jScrollPane2.setMinimumSize(new java.awt.Dimension(50, 22));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(198, 439));

        jPanel2.setBackground(new java.awt.Color(244, 244, 244));
        jPanel2.setAutoscrolls(true);
        jPanel2.setMaximumSize(new java.awt.Dimension(200, 200));
        jPanel2.setMinimumSize(new java.awt.Dimension(50, 20));
        jPanel2.setPreferredSize(new java.awt.Dimension(292, 432));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 437, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanel2);

        jPanel3.setBackground(new java.awt.Color(244, 244, 244));

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 51, 51));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chatroom_normal.png"))); // NOI18N
        jButton2.setToolTipText("Conversations");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusable(false);
        jButton2.setName("conversationsButton"); // NOI18N
        jButton2.setRequestFocusEnabled(false);
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chatroom_rollover.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void writeOutput(String output, User user) {
        if (user == null) {
            user = new User("Client");
        }

        if (!output.trim().equals("")) {
            if (jTextArea1.getText().equals("")) {
                jTextArea1.setText(getTimeStamp() + user.getName() + ":  " + output);
            } else {
                jTextArea1.setText(jTextArea1.getText() + "\n" + getTimeStamp() + user.getName() + ":  " + output);
            }
        }
    }

    private String getTimeStamp() {
        String hours;
        String minutes;
        if (Calendar.getInstance().getTime().getHours() < 10) {
            hours = "0" + Calendar.getInstance().getTime().getHours();
        } else {
            hours = "" + Calendar.getInstance().getTime().getHours();
        }
        if (Calendar.getInstance().getTime().getMinutes() < 10) {
            minutes = "0" + Calendar.getInstance().getTime().getMinutes();
        } else {
            minutes = "" + Calendar.getInstance().getTime().getMinutes();
        }
        return "  [" + hours + ":" + minutes + "]  ";
    }

    private void sendMessage(String signal, String messageText, String chatroomOrUser) {
        switch (signal) {
            case "SEND_TO_SERVER":
                chatMainFramePCS.firePropertyChange("SEND_TO_SERVER", chatroomOrUser, messageText);
                break;
        }
    }

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        sendMessage("SEND_TO_SERVER", jTextField1.getText(), "DEFAULT");
        jTextField1.setText("");
    }//GEN-LAST:event_jButton1MouseReleased

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sendMessage("SEND_TO_SERVER", jTextField1.getText(), "DEFAULT");
            jTextField1.setText("");
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    public void addPropertyChangeListener(PropertyChangeListener PCL) {
        chatMainFramePCS.addPropertyChangeListener(PCL);
    }
}
