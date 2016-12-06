package abc.music.editor.gui;

import abc.music.core.domain.Key;
import abc.music.core.domain.Modifier;
import abc.music.core.domain.Voice;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.AmeConstants;
import abc.music.editor.gui.support.SharpFlatNaturalKeyListener;
import abc.music.editor.gui.support.TransposeComboBoxModel;
import abc.music.editor.gui.support.TransposeMap;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author hl
 */
public class VoicePanel extends AmePanel {

    private final Voice voice;

    public VoicePanel(AbcMusicEditor parent, Voice voice) {
        super(parent, null);
        this.voice = voice;
        setFields();
    }

    @Override
    protected void init() {
        initComponents();
        idTextField.addKeyListener(new SharpFlatNaturalKeyListener());
        shortNameTextField.addKeyListener(new SharpFlatNaturalKeyListener());
        nameTextField.addKeyListener(new SharpFlatNaturalKeyListener());
    }

    private void setFields() {
        idTextField.setText(voice.getVoiceId());
        shortNameTextField.setText(voice.getShortName());
        nameTextField.setText(voice.getName());
        useKeyCheckBox.setSelected(voice.getUseVoiceKey());
        useModifiersCheckBox.setSelected(voice.getUseVoiceModifiers());
        pitchComboBox.setModel(new DefaultComboBoxModel(Key.Pitch.values()));
        signatureComboBox.setModel(new DefaultComboBoxModel(Key.Signature.values()));
        modeComboBox.setModel(new DefaultComboBoxModel(Key.Mode.values()));
        clefComboBox.setModel(new DefaultComboBoxModel(Modifier.Clef.values()));
        octaveComboBox.setModel(new DefaultComboBoxModel(Modifier.OctaveClef.values()));
        transposeComboBox.setModel(new TransposeComboBoxModel());
        if (voice.getNotes() != null) {
            bodyEditorPane.setText(voice.getNotes());
        }
        pitchComboBox.setSelectedItem(voice.getKey().getPitch());
        signatureComboBox.setSelectedItem(voice.getKey().getSignature());
        modeComboBox.setSelectedItem(voice.getKey().getMode());
        Modifier modifier = voice.getKey().getModifier();
        clefComboBox.setSelectedItem(modifier.getClef());
        octaveComboBox.setSelectedItem(modifier.getOctave());
        transposeComboBox.setSelectedItem(TransposeMap.getItem(modifier.getTranspose()));
//        transposeSpinner.setValue(modifier.getTranspose());
        bodyEditorPane.setText(voice.getNotes());
    }

    public void updatePanel() {
        setFields();
    }
    
    public Voice getVoice() {
        return voice;
    }

    public void updateVoice() {
        if (idTextField.getText() != null && !idTextField.getText().isEmpty()) {
            voice.setVoiceId(idTextField.getText());
        }
        voice.setShortName(shortNameTextField.getText());
        voice.setName(nameTextField.getText());
        if (useKeyCheckBox.isSelected()) {
            if (modeComboBox.getSelectedItem() != null) {
                voice.getKey().setMode((Key.Mode) modeComboBox.getSelectedItem());
            }
            if (pitchComboBox.getSelectedItem() != null) {
                voice.getKey().setPitch((Key.Pitch) pitchComboBox.getSelectedItem());
            }
            if (signatureComboBox.getSelectedItem() != null) {
                voice.getKey().setSignature((Key.Signature) signatureComboBox.getSelectedItem());
            }
            voice.setUseVoiceKey(true);
        } else {
            Key key = voice.getTune().getKey();
            voice.getKey().setMode(key.getMode());
            voice.getKey().setPitch(key.getPitch());
            voice.getKey().setSignature(key.getSignature());
            voice.setUseVoiceKey(false);
        }
        if (useModifiersCheckBox.isSelected()) {
            if (clefComboBox.getSelectedItem() != null) {
                voice.getKey().getModifier().setClef((Modifier.Clef) clefComboBox.getSelectedItem());
            }
            if (octaveComboBox.getSelectedItem() != null) {
                voice.getKey().getModifier().setOctave((Modifier.OctaveClef) octaveComboBox.getSelectedItem());
            }
            if (transposeComboBox.getSelectedItem() != null) {
                voice.getKey().getModifier().setTranspose(((TransposeMap.Item) transposeComboBox.getSelectedItem()).getInterval());
            }
//            if (transposeSpinner.getValue() != null) {
//                voice.getKey().getModifier().setTranspose((Integer) transposeSpinner.getValue());
//            }
            voice.setUseVoiceModifiers(true);
        } else {
            Modifier modifier = voice.getTune().getKey().getModifier();
            voice.getKey().getModifier().setClef(modifier.getClef());
            voice.getKey().getModifier().setOctave(modifier.getOctave());
            voice.getKey().getModifier().setTranspose(modifier.getTranspose());
            voice.setUseVoiceModifiers(false);
        }
        voice.setNotes(bodyEditorPane.getText());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        voicePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bodyEditorPane = new javax.swing.JEditorPane();
        jPanel2 = new javax.swing.JPanel();
        modeComboBox = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        signatureComboBox = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        clefComboBox = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        octaveComboBox = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        pitchComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        idTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        shortNameTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        otherPropertiesButton = new javax.swing.JButton();
        useKeyCheckBox = new javax.swing.JCheckBox();
        useModifiersCheckBox = new javax.swing.JCheckBox();
        transposeComboBox = new javax.swing.JComboBox<>();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        voicePanel.setOpaque(false);
        voicePanel.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setViewportView(bodyEditorPane);

        voicePanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Voice Properties", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, AmeConstants.SMALL_TITLE_FONT, AmeConstants.TITLE_COLOR));
        jPanel2.setOpaque(false);

        modeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        modeComboBox.setEnabled(false);

        jLabel22.setText("Clef:");

        jLabel20.setText("Modus:");

        jLabel21.setFont(new java.awt.Font("Ubuntu", 2, 15)); // NOI18N
        jLabel21.setText("Modifiers:");

        signatureComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        signatureComboBox.setEnabled(false);

        jLabel18.setText("Pitch:");

        jLabel24.setText("Octave:");

        clefComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        clefComboBox.setToolTipText("<html>Select clef.<br>\nThe number on some choices indecates which line the clef is drawn on.<br>\nThe defaults are:\n<dl>\n<dt>G = 2 (Treble Clef)</dt>\n<dt>F = 4 (Bass Clef)</dt>\n<dt>C = 3 (Alto Clef)</dt>\n</dl>\nThe others are:\n<dl>\n<dt>G1 = French Violin Clef</dt>\n<dt>F3 = Baritone Clef</dt>\n<dt>C4 = Tenor Clef</dt>\n<dt>C2 = Mezzosoprano Clef</dt>\n<dt>C1 = Soprano Clef</dt>\n</dl>");
        clefComboBox.setEnabled(false);

        jLabel19.setText("Signature:");

        octaveComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        octaveComboBox.setEnabled(false);

        jLabel23.setText("Transpose:");

        jLabel15.setFont(new java.awt.Font("Ubuntu", 2, 15)); // NOI18N
        jLabel15.setText("Key:");

        pitchComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pitchComboBox.setEnabled(false);

        jLabel1.setText("ID:");

        jLabel2.setText("Short Name:");

        jLabel3.setText("Name:");

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.BorderLayout());

        otherPropertiesButton.setText("Other Properties");
        otherPropertiesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherPropertiesButtonActionPerformed(evt);
            }
        });
        jPanel1.add(otherPropertiesButton, java.awt.BorderLayout.CENTER);

        useKeyCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                useKeyCheckBoxItemStateChanged(evt);
            }
        });

        useModifiersCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                useModifiersCheckBoxItemStateChanged(evt);
            }
        });

        transposeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        transposeComboBox.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(shortNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                                    .addComponent(idTextField)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(clefComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(octaveComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(transposeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(useModifiersCheckBox))
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)))
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pitchComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(signatureComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(modeComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(useKeyCheckBox)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(shortNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15))
                            .addComponent(useKeyCheckBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pitchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(signatureComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(modeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21))
                    .addComponent(useModifiersCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(clefComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(transposeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(octaveComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        voicePanel.add(jPanel2, java.awt.BorderLayout.LINE_END);

        add(voicePanel);
    }// </editor-fold>//GEN-END:initComponents

    private void otherPropertiesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otherPropertiesButtonActionPerformed
        showOtherProperties();
    }//GEN-LAST:event_otherPropertiesButtonActionPerformed

    private void useKeyCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_useKeyCheckBoxItemStateChanged
        toggleKeyEnabled();
    }//GEN-LAST:event_useKeyCheckBoxItemStateChanged

    private void useModifiersCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_useModifiersCheckBoxItemStateChanged
        toggleModifiersEnabled();
    }//GEN-LAST:event_useModifiersCheckBoxItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane bodyEditorPane;
    private javax.swing.JComboBox<String> clefComboBox;
    private javax.swing.JTextField idTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> modeComboBox;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JComboBox<String> octaveComboBox;
    private javax.swing.JButton otherPropertiesButton;
    private javax.swing.JComboBox<String> pitchComboBox;
    private javax.swing.JTextField shortNameTextField;
    private javax.swing.JComboBox<String> signatureComboBox;
    private javax.swing.JComboBox<String> transposeComboBox;
    private javax.swing.JCheckBox useKeyCheckBox;
    private javax.swing.JCheckBox useModifiersCheckBox;
    private javax.swing.JPanel voicePanel;
    // End of variables declaration//GEN-END:variables

    private void showOtherProperties() {
        System.out.println("TODO: Create dialog for other voice properties (Midi etc)");
    }

    private void toggleKeyEnabled() {
        boolean enabled = useKeyCheckBox.isSelected();
        pitchComboBox.setEnabled(enabled);
        signatureComboBox.setEnabled(enabled);
        modeComboBox.setEnabled(enabled);
    }

    private void toggleModifiersEnabled() {
        boolean enabled = useModifiersCheckBox.isSelected();
        clefComboBox.setEnabled(enabled);
        transposeComboBox.setEnabled(enabled);

//        transposeSpinner.setEnabled(enabled);
        octaveComboBox.setEnabled(enabled);
    }

}
