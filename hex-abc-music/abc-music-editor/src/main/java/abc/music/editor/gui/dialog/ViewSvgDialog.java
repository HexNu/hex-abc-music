package abc.music.editor.gui.dialog;

import abc.music.editor.AbcMusicEditor;
import java.awt.BorderLayout;
import java.io.File;
import org.apache.batik.swing.JSVGCanvas;
import se.digitman.lightxml.XmlDocument;

/**
 *
 * @author hl
 */
public class ViewSvgDialog extends AmeDialog<XmlDocument> {

    private JSVGCanvas canvas;
    private File file;

    public ViewSvgDialog(AbcMusicEditor parent, XmlDocument doc) {
        super(parent, "SVG Viewer - " + doc.getRoot().getXmlNodeByPath("g/text").getText());
    }

    @Override
    protected void init() {
        initComponents();
        this.canvas = new JSVGCanvas();
        canvasPanel.setLayout(new BorderLayout());
        canvasPanel.add(canvas, BorderLayout.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvasPanel = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        canvasPanel.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout canvasPanelLayout = new javax.swing.GroupLayout(canvasPanel);
        canvasPanel.setLayout(canvasPanelLayout);
        canvasPanelLayout.setHorizontalGroup(
            canvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        canvasPanelLayout.setVerticalGroup(
            canvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(canvasPanel, java.awt.BorderLayout.CENTER);

        jButton2.setText("    Save    ");
        buttonPanel.add(jButton2);

        jButton1.setText("   Close   ");
        buttonPanel.add(jButton1);

        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel canvasPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    // End of variables declaration//GEN-END:variables

    @Override
    protected void accept() {
    }
}
