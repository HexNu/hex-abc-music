package abc.music.editor.gui;

import abc.music.core.domain.Lyrics;
import abc.music.core.domain.Tune;
import abc.music.core.domain.Voice;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.action.SaveTuneAction;
import abc.music.editor.action.UpdateSvgFileAction;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author hl
 */
public class VoicesPanel extends AmePanel {

    private SvgViewPanel svgPanel;

    public VoicesPanel(AbcMusicEditor parent) {
        super(parent, "Voices");
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

    public void clearVoicesPanel() {
        voicesTabbedPane.removeAll();
        voicesTabbedPane.repaint();
        voicesTabbedPane.revalidate();
        getParent().repaint();
        getParent().revalidate();
        super.repaint();
        super.revalidate();
    }

    public void setVoices(List<Voice> voices) {
        clearVoicesPanel();
        if (!voices.isEmpty() && voices.get(0) != null && voices.get(0).getTune() != null) {
            addSvgPanel(voices.get(0).getTune());
        }
        voices.stream().forEach(this::addVoice);
    }

    public List<VoicePanel> getVoicePanels() {
        List<VoicePanel> result = new ArrayList<>();
        for (Component c : voicesTabbedPane.getComponents()) {
            if (c instanceof VoicePanel) {
                result.add((VoicePanel) c);
            }
        }
        return result;
    }

    private boolean hasSvgPanel() {
        for (Component c : voicesTabbedPane.getComponents()) {
            if (c instanceof SvgViewPanel) {
                return true;
            }
        }
        return false;
    }

    public void addVoice(Voice voice) {
        if (voice != null) {
            if (!hasSvgPanel()) {
                addSvgPanel(voice.getTune());
            }
            int newIndex = voicesTabbedPane.getComponentCount() - 1;
            if (hasLyricsPanel()) {
                newIndex = newIndex - 1;
            }
            voicesTabbedPane.add(new VoicePanel(editor, voice, this), newIndex);
            voicesTabbedPane.setTitleAt(newIndex, voice.getName());
            repaint();
            revalidate();
            voicesTabbedPane.repaint();
            voicesTabbedPane.revalidate();
        }
    }

    public boolean hasLyricsPanel() {
        for (Component c : voicesTabbedPane.getComponents()) {
            if (c instanceof LyricsPanel) {
                return true;
            }
        }
        return false;
    }

    public void addLyrics(Lyrics lyrics) {
        if (!hasSvgPanel()) {
            addSvgPanel(lyrics.getTune());
        }
        if (!hasLyricsPanel()) {
            LyricsPanel lyricsPanel = new LyricsPanel(editor, lyrics);
            int newIndex = voicesTabbedPane.getComponentCount() - 1;
            voicesTabbedPane.add(lyricsPanel, newIndex);
            voicesTabbedPane.setTitleAt(newIndex, "Lyrics");
            repaint();
            revalidate();
            voicesTabbedPane.repaint();
            voicesTabbedPane.revalidate();
        } else {
            setFocus(voicesTabbedPane.getComponentCount() - 2);
        }
    }

    public void setFocus(int index) {
        voicesTabbedPane.setSelectedIndex(index);
    }

    public void reattachVoicePanel(VoicePanel voicePanel, int index) {
        voicesTabbedPane.add(voicePanel, index);
        voicesTabbedPane.setTitleAt(index, voicePanel.getVoice().getName());
        setFocus(index);
    }

    private void addSvgPanel(Tune tune) {
        svgPanel = new SvgViewPanel(editor, tune);
        voicesTabbedPane.add("View", svgPanel);
        repaint();
        revalidate();
        voicesTabbedPane.repaint();
        voicesTabbedPane.revalidate();
    }

    public void updateVoicePanels() {
        for (int i = 0; i < voicesTabbedPane.getComponentCount(); i++) {
            if (voicesTabbedPane.getComponent(i) instanceof VoicePanel) {
                ((VoicePanel) voicesTabbedPane.getComponent(i)).updatePanel();
            }
        }
        refreshSvgPanel();
    }

    private void refreshSvgPanel() {
        if (hasSvgPanel()) {
            voicesTabbedPane.remove(svgPanel);
            addSvgPanel(getVoicePanels().get(0).getVoice().getTune());
        }
    }

    public void updateVoices() {
        for (int i = 0; i < voicesTabbedPane.getComponentCount(); i++) {
            if (voicesTabbedPane.getComponent(i) instanceof VoicePanel) {
                ((VoicePanel) voicesTabbedPane.getComponent(i)).updateVoice();
            }
        }
    }
    
    public void updateLyrics() {
        for (int i = 0; i < voicesTabbedPane.getComponentCount(); i++) {
            if (voicesTabbedPane.getComponent(i) instanceof LyricsPanel) {
                ((LyricsPanel) voicesTabbedPane.getComponent(i)).updateLyrics();
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
