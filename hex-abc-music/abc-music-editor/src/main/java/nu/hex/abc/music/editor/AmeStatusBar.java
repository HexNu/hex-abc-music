package nu.hex.abc.music.editor;

import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class AmeStatusBar extends javax.swing.JPanel {

    private static final String DEFAULT_STATUS_MESSAGE = "\u00A0";

    public AmeStatusBar() {
        initComponents();
    }

    public void setLeftStatus(String message) {
        setLeftStatus(message, 3000);
    }

    public void setLeftStatus(String message, int time) {
        leftStatusMessage.setText(message);
        if (time > 0) {
            Timer timer = new Timer(time, (ActionEvent e) -> {
                clearLeftStatus();
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    public void clearLeftStatus() {
        leftStatusMessage.setText(DEFAULT_STATUS_MESSAGE);
    }

    public void setCenterStatus(String message) {
        setCenterStatus(message, 0);
    }

    public void setCenterStatus(String message, int time) {
        centerStatusMessage.setText(message);
        if (time > 0) {
            Timer timer = new Timer(time, (ActionEvent e) -> {
                clearCenterStatus();
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    public void clearCenterStatus() {
        centerStatusMessage.setText(DEFAULT_STATUS_MESSAGE);
    }

    public void setRightStatus(String message) {
        setRightStatus(message, 0);
    }

    public void setRightStatus(String message, int time) {
        rightStatusMessage.setText(message);
        if (time > 0) {
            Timer timer = new Timer(time, (ActionEvent e) -> {
                clearRightStatus();
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    public void clearRightStatus() {
        rightStatusMessage.setText(DEFAULT_STATUS_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leftStatusMessage = new javax.swing.JLabel();
        rightStatusMessage = new javax.swing.JLabel();
        centerStatusMessage = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        leftStatusMessage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/empty-icon.png"))); // NOI18N
        add(leftStatusMessage, java.awt.BorderLayout.LINE_START);

        rightStatusMessage.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        rightStatusMessage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/empty-icon.png"))); // NOI18N
        rightStatusMessage.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        add(rightStatusMessage, java.awt.BorderLayout.LINE_END);

        centerStatusMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(centerStatusMessage, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel centerStatusMessage;
    private javax.swing.JLabel leftStatusMessage;
    private javax.swing.JLabel rightStatusMessage;
    // End of variables declaration//GEN-END:variables
}
