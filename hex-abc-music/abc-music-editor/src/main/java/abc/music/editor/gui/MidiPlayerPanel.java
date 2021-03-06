package abc.music.editor.gui;

import abc.music.core.domain.Tune;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.help.Metronome;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import nu.hex.abc.music.service.Service;
import nu.hex.abc.music.service.properties.AbcMusicProperties;

/**
 *
 * @author hl
 */
public class MidiPlayerPanel extends AmeSidePanel {

    private Sequencer sequencer;
    private File midiFile;
    private File tempFile;
    private boolean running = false;
    private boolean metronomeRunning = false;
    private static final String START = ">";
    private static final String STOP = "·";

    public MidiPlayerPanel(AbcMusicEditor editor) {
        super(editor);
    }

    @Override
    protected void init() {
        try {
            initComponents();
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(MidiPlayerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setSequence(boolean isTune) {
        InputStream midiSource = null;
        try {
            Tune tune = editor.getTuneEditor().getTune();
            Service service = new Service(editor.getProject());
            if (isTune) {
                tempFile = new File(service.getPropertyService().getProperty(AbcMusicProperties.MIDI_FOLDER) + "/temp-" + tune.getName().replaceAll("\\s", "_") + ".mid");
                midiFile = service.getIoService().createMidiFile(editor.getTuneEditor().getTune(), tempFile);
            } else {
                midiFile = new Metronome(editor, tune.getMeter(), tune.getTempo()).generateFile();
            }
            midiSource = new FileInputStream(midiFile);
            sequencer.setSequence(midiSource);
        } catch (InvalidMidiDataException | IOException ex) {
            Logger.getLogger(MidiPlayerPanel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (midiSource != null) {
                    midiSource.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(MidiPlayerPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (midiFile != null) {
                midiFile.delete();
            }
            if (tempFile != null) {
                tempFile.delete();
            }
        }
    }

    public void enablePlayer(boolean enabled) {
        playButton.setEnabled(enabled);
        metronomeButton.setEnabled(enabled);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        metronomeButton = new javax.swing.JButton();

        setOpaque(false);

        playButton.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        playButton.setText(">");
        playButton.setEnabled(false);
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Play Tune:");

        jLabel2.setText("Metronome:");

        metronomeButton.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        metronomeButton.setText(">");
        metronomeButton.setEnabled(false);
        metronomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                metronomeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(metronomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(metronomeButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(playButton))
                .addContainerGap(210, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        setSequence(true);
        if (!running) {
            sequencer.start();
            playButton.setText(STOP);
        } else {
            sequencer.stop();
            playButton.setText(START);
        }
        running = !running;
    }//GEN-LAST:event_playButtonActionPerformed

    private void metronomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_metronomeButtonActionPerformed
        setSequence(false);
        if (!metronomeRunning) {
            sequencer.start();
            metronomeButton.setText(STOP);
        } else {
            sequencer.stop();
            metronomeButton.setText(START);
        }
        metronomeRunning = !metronomeRunning;
    }//GEN-LAST:event_metronomeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton metronomeButton;
    private javax.swing.JButton playButton;
    // End of variables declaration//GEN-END:variables
}
