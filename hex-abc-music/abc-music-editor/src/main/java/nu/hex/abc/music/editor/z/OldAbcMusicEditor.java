package nu.hex.abc.music.editor.z;

import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author hl
 */
public class OldAbcMusicEditor extends javax.swing.JFrame {

    public OldAbcMusicEditor() {
        initComponents();
        init();
    }

    public final void init() {
        setContentPane(new BackgroundImagePanel("JSBach-Facsimile.jpg"));
        initComponents();
    }
//
//    @Override
//    protected JRootPane createRootPane() {
//        KeyStroke left = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, InputEvent.ALT_MASK);
//        JRootPane customRootPane = new JRootPane();
//        ActionListener leftActionListener = (ActionEvent e) -> {
//        };
//        KeyStroke right = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, InputEvent.ALT_MASK);
//        ActionListener rightActionListener = (ActionEvent e) -> {
//        };
//        customRootPane.registerKeyboardAction(leftActionListener, left, JComponent.WHEN_IN_FOCUSED_WINDOW);
//        customRootPane.registerKeyboardAction(rightActionListener, right, JComponent.WHEN_IN_FOCUSED_WINDOW);
//        return customRootPane;
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leftPanel = new javax.swing.JPanel();
        searchPanel = new javax.swing.JPanel();
        rightPanel = new javax.swing.JPanel();
        recentTunesPanel = new javax.swing.JPanel();
        linksPanel = new javax.swing.JPanel();
        centerPanel = new javax.swing.JPanel();
        editorPanel = new javax.swing.JPanel();
        topPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bottomPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        leftPanel.setLayout(new java.awt.GridLayout(2, 1, 6, 12));

        searchPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Ringbearer", 0, 18))); // NOI18N
        searchPanel.setOpaque(false);

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 333, Short.MAX_VALUE)
        );

        leftPanel.add(searchPanel);

        getContentPane().add(leftPanel, java.awt.BorderLayout.LINE_START);

        rightPanel.setLayout(new java.awt.GridLayout(3, 1, 0, 12));

        recentTunesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Recent", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Ringbearer", 0, 18))); // NOI18N
        recentTunesPanel.setOpaque(false);

        javax.swing.GroupLayout recentTunesPanelLayout = new javax.swing.GroupLayout(recentTunesPanel);
        recentTunesPanel.setLayout(recentTunesPanelLayout);
        recentTunesPanelLayout.setHorizontalGroup(
            recentTunesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 112, Short.MAX_VALUE)
        );
        recentTunesPanelLayout.setVerticalGroup(
            recentTunesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
        );

        rightPanel.add(recentTunesPanel);

        linksPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Links", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Ringbearer", 0, 18))); // NOI18N
        linksPanel.setOpaque(false);

        javax.swing.GroupLayout linksPanelLayout = new javax.swing.GroupLayout(linksPanel);
        linksPanel.setLayout(linksPanelLayout);
        linksPanelLayout.setHorizontalGroup(
            linksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 112, Short.MAX_VALUE)
        );
        linksPanelLayout.setVerticalGroup(
            linksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
        );

        rightPanel.add(linksPanel);

        getContentPane().add(rightPanel, java.awt.BorderLayout.LINE_END);

        centerPanel.setLayout(new java.awt.BorderLayout());

        editorPanel.setOpaque(false);

        javax.swing.GroupLayout editorPanelLayout = new javax.swing.GroupLayout(editorPanel);
        editorPanel.setLayout(editorPanelLayout);
        editorPanelLayout.setHorizontalGroup(
            editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 848, Short.MAX_VALUE)
        );
        editorPanelLayout.setVerticalGroup(
            editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
        );

        centerPanel.add(editorPanel, java.awt.BorderLayout.CENTER);

        topPanel.setBackground(java.awt.Color.white);
        topPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(184, 36, 90)));
        topPanel.setOpaque(false);
        topPanel.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Ringbearer", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(184, 36, 90));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hex ABC Music Editor");
        topPanel.add(jLabel1, java.awt.BorderLayout.CENTER);

        centerPanel.add(topPanel, java.awt.BorderLayout.PAGE_START);

        bottomPanel.setOpaque(false);

        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 848, Short.MAX_VALUE)
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        centerPanel.add(bottomPanel, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(OldAbcMusicEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OldAbcMusicEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OldAbcMusicEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OldAbcMusicEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OldAbcMusicEditor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JPanel editorPanel;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel linksPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JPanel recentTunesPanel;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

    private class BackgroundImagePanel extends JComponent {

        private final String backgroundImage;

        public BackgroundImagePanel(String backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(getResourceAsImage(), 0, 0, this);
        }

        private java.awt.Image getResourceAsImage() {
            InputStream imageStream = getClass().getClassLoader().getResourceAsStream("images/JSBach-Facsimile.png");
            try {
                return ImageIO.read(imageStream);
            } catch (IOException e) {
                Logger.getLogger(OldAbcMusicEditor.class.getName()).log(Level.SEVERE, "Background Image {} could not be found", backgroundImage);
                return null;
            }
        }
    }

}
