package abc.music.editor.components;

import abc.music.core.domain.Project;
import abc.music.core.domain.Tune;
import abc.music.core.domain.Voice;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.action.SaveTuneAction;
import abc.music.editor.action.UpdateSvgFileAction;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import java.util.List;

/**
 *
 * @author hl
 */
public class VoicesPanel extends AmePanel {

    private SvgViewPanel svgPanel;

    public VoicesPanel(AbcMusicEditor parent, Project project) {
        super(parent, project, "Voices");
    }

    @Override
    protected void init() {
        initComponents();
        voicesTabbedPane.addChangeListener((ChangeEvent e) -> {
            JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
            int index = sourceTabbedPane.getSelectedIndex();
            if (index + 1 == sourceTabbedPane.getComponentCount()) {
                new SaveTuneAction(editor).actionPerformed(null);
                new UpdateSvgFileAction(editor, svgPanel.getTune()).actionPerformed(null);
                svgPanel.updateFile();
            }
        });
    }

    public void clearVoices() {
        voicesTabbedPane.removeAll();
        voicesTabbedPane.repaint();
        voicesTabbedPane.revalidate();
        getParent().repaint();
        getParent().revalidate();
        super.repaint();
        super.revalidate();
    }

    public void setVoices(List<Voice> voices) {
        clearVoices();
        if (!voices.isEmpty() && voices.get(0) != null && voices.get(0).getTune() != null) {
            addSvgPanel(voices.get(0).getTune());
        }
        voices.stream().forEach(this::addVoice);
    }

    public void addVoice(Voice voice) {
        if (voice != null) {
            int newIndex = voicesTabbedPane.getComponentCount() - 1;
            voicesTabbedPane.add(new VoicePanel(editor, project, voice), newIndex);
            voicesTabbedPane.setTitleAt(newIndex, voice.getName());
            repaint();
            revalidate();
            voicesTabbedPane.repaint();
            voicesTabbedPane.revalidate();
        }
    }

    private void addSvgPanel(Tune tune) {
        svgPanel = new SvgViewPanel(editor, tune);
        voicesTabbedPane.add("View", svgPanel);
        repaint();
        revalidate();
        voicesTabbedPane.repaint();
        voicesTabbedPane.revalidate();
    }

    public void updateVoices() {
        for (int i = 0; i < voicesTabbedPane.getComponentCount(); i++) {
            if (voicesTabbedPane.getComponent(i) instanceof VoicePanel) {
                ((VoicePanel) voicesTabbedPane.getComponent(i)).updateVoice();
            }
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

        voicesTabbedPane = new javax.swing.JTabbedPane();

        setOpaque(false);
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        voicesTabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        add(voicesTabbedPane);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane voicesTabbedPane;
    // End of variables declaration//GEN-END:variables

}
