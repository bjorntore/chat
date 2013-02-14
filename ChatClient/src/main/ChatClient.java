/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import GUI.ChatMainFrame;
import Users.User;
import java.util.ArrayList;

/**
 *
 * @author BjørnTore
 */
public class ChatClient {

    private static ChatMainFrame chatMainFrame = new ChatMainFrame();

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        User stian = new User("Stian");
        User bt = new User("Bjørn Tore");
        User iver = new User("Iver");
        User rasmus = new User("Rasmus");

        ArrayList<User> users = new ArrayList<>();
        users.add(stian);
        users.add(bt);
        users.add(iver);
        users.add(rasmus);

        chatMainFrame.refreshUserList(users);
        chatMainFrame.setVisible(true);
        /* Create and display the form */
    }
}
