package nu.hex.abc.music.editor.components;

import abc.music.core.domain.Key;
import abc.music.core.domain.Modifier;
import abc.music.core.domain.Person;
import abc.music.core.domain.Project;
import abc.music.core.domain.Tempo;
import abc.music.core.domain.Tune;
import java.awt.Dimension;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.SpinnerListModel;
import nu.hex.abc.music.editor.AbcMusicEditor;
import nu.hex.abc.music.editor.Constants;
import nu.hex.abc.music.editor.action.CreateVoiceActon;
import nu.hex.abc.music.editor.action.CreateTuneAction;
import nu.hex.abc.music.editor.action.OpenScoreLayoutAction;
import nu.hex.abc.music.editor.support.PersonRoleListMouseListener;
import nu.hex.abc.music.editor.util.TuneHelper;

/**
 *
 * @author hl
 */
public class TuneHeadersPanel extends AmePanel {

    private Tune tune;

    public TuneHeadersPanel(AbcMusicEditor parent, Project project) {
        this(parent, null, project);
    }

    public TuneHeadersPanel(AbcMusicEditor parent, Tune tune, Project project) {
        super(parent, project, "Tune Headers", Constants.SMALL_TITLE_FONT);
        this.tune = tune;
    }

    @Override
    protected void init() {
        initComponents();
        List<Tempo.Marking> tempi = Tempo.getTempi();
        tempi.add(0, null);
        tempoLabelComboBox.setModel(new DefaultComboBoxModel(tempi.toArray()));
        tempoUnitComboBox.setModel(new DefaultComboBoxModel(Tempo.Unit.values()));
        unitsPerMinuteComboBox.setModel(new DefaultComboBoxModel(Tempo.getMM().toArray()));
        meterDenominatorSpinner.setModel(new SpinnerListModel(new Integer[]{1, 2, 4, 8, 16, 32}));
        defaultTimeValueComboBox.setModel(new DefaultComboBoxModel(Tune.TimeValue.values()));
        pitchComboBox.setModel(new DefaultComboBoxModel(Key.Pitch.values()));
        Dimension keyFieldsDimension = new Dimension(60, 27);
        pitchComboBox.setSize(keyFieldsDimension);
        pitchComboBox.setPreferredSize(keyFieldsDimension);
        signatureComboBox.setModel(new DefaultComboBoxModel(Key.Signature.values()));
        signatureComboBox.setSize(keyFieldsDimension);
        signatureComboBox.setPreferredSize(keyFieldsDimension);
        modeComboBox.setModel(new DefaultComboBoxModel(Key.Mode.values()));
        modeComboBox.setSize(keyFieldsDimension);
        modeComboBox.setPreferredSize(keyFieldsDimension);
        clefComboBox.setModel(new DefaultComboBoxModel(Modifier.Clef.values()));
        clefComboBox.setSize(keyFieldsDimension);
        clefComboBox.setPreferredSize(keyFieldsDimension);
        octaveComboBox.setModel(new DefaultComboBoxModel(Modifier.OctaveClef.values()));
        octaveComboBox.setSize(keyFieldsDimension);
        octaveComboBox.setPreferredSize(keyFieldsDimension);
        transposeSpinner.setSize(keyFieldsDimension);
        transposeSpinner.setPreferredSize(keyFieldsDimension);
        setFields();
    }

    public void refresh() {
        setFields();
    }

    private void setFields() {
        resetFields();
        if (tune != null) {
            populateFields();
        }
    }

    private void populateFields() {
        openScoreLayoutButton.setEnabled(true);
        addVoiceButton.setEnabled(true);
        TuneHelper tuneHelper = new TuneHelper(tune);
        titlesTextArea.setText(tuneHelper.getTitlesAsString());
        rythmTextField.setText(tune.getRythm());
        historyTextArea.setText(tuneHelper.getHistoryAsString());
        originTextArea.setText(tuneHelper.getOriginAsString());
        commentsTextArea.setText(tuneHelper.getCommentsAsString());
        copyrightTextArea.setText(tuneHelper.getCopyrightAsString());
        tempoUnitComboBox.setSelectedItem(tune.getTempo().getUnit());
        unitsPerMinuteComboBox.setSelectedItem(tune.getTempo().getUnitsPerMinute());
        defaultTimeValueComboBox.setSelectedItem(tune.getTimeValue());
        meterNumeratorSpinner.setValue(tune.getMeter().getNumerator());
        meterDenominatorSpinner.setValue(tune.getMeter().getDenominator());
        updateLists();
        pitchComboBox.setSelectedItem(tune.getKey().getPitch());
        signatureComboBox.setSelectedItem(tune.getKey().getSignature());
        modeComboBox.setSelectedItem(tune.getKey().getMode());
        clefComboBox.setSelectedItem(tune.getKey().getModifier().getClef());
        transposeSpinner.setValue(tune.getKey().getModifier().getTranspose());
        octaveComboBox.setSelectedItem(tune.getKey().getModifier().getOctave());
        parent.getVoicesPanel().setVoices(tune.getVoices());
    }

    private void resetFields() {
        newTuneButton.setEnabled(parent.getProject() != null);
        openScoreLayoutButton.setEnabled(false);
        addVoiceButton.setEnabled(false);
        titlesTextArea.setText("");
        rythmTextField.setText("");
        historyTextArea.setText("");
        originTextArea.setText("");
        commentsTextArea.setText("");
        copyrightTextArea.setText("");
        tempoUnitComboBox.setSelectedItem(Tempo.Unit.ONE_QUARTER);
        unitsPerMinuteComboBox.setSelectedItem(96);
        defaultTimeValueComboBox.setSelectedIndex(2);
        meterDenominatorSpinner.setValue(4);
        composerList.addMouseListener(new PersonRoleListMouseListener(this, composerList, Person.Role.COMPOSER, tune));
        authorList.addMouseListener(new PersonRoleListMouseListener(this, authorList, Person.Role.AUTHOR, tune));
        traditionalList.addMouseListener(new PersonRoleListMouseListener(this, authorList, Person.Role.TRAD, tune));
        transcriberList.addMouseListener(new PersonRoleListMouseListener(this, authorList, Person.Role.TRANSCRIBER, tune));
        pitchComboBox.setSelectedItem(Key.Pitch.DEFAULT_PITCH);
        signatureComboBox.setSelectedItem(Key.Signature.DEFAULT_SIGNATURE);
        modeComboBox.setSelectedItem(Key.Mode.DEFAULT_MODE);
        clefComboBox.setSelectedItem(Modifier.Clef.DEFAULT_CLEF);
        transposeSpinner.setValue(0);
        octaveComboBox.setSelectedItem(Modifier.OctaveClef.DEFAULT_OCTAVE);
    }

    public void setEditingEnabled(boolean enabled) {
        openScoreLayoutButton.setEnabled(enabled);
        addVoiceButton.setEnabled(enabled);
        titlesTextArea.setEnabled(enabled);
        rythmTextField.setEnabled(enabled);
        historyTextArea.setEnabled(enabled);
        originTextArea.setEnabled(enabled);
        commentsTextArea.setEnabled(enabled);
        copyrightTextArea.setEnabled(enabled);
        tempoLabelComboBox.setEnabled(enabled);
        tempoUnitComboBox.setEnabled(enabled);
        unitsPerMinuteComboBox.setEnabled(enabled);
        defaultTimeValueComboBox.setEnabled(enabled);
        meterDenominatorSpinner.setEnabled(enabled);
        meterNumeratorSpinner.setEnabled(enabled);
        composerList.setEnabled(enabled);
        authorList.setEnabled(enabled);
        traditionalList.setEnabled(enabled);
        transcriberList.setEnabled(enabled);
        pitchComboBox.setEnabled(enabled);
        signatureComboBox.setEnabled(enabled);
        modeComboBox.setEnabled(enabled);
        clefComboBox.setEnabled(enabled);
        transposeSpinner.setEnabled(enabled);
        octaveComboBox.setEnabled(enabled);
    }

    public void updateLists() {
        DefaultListModel composerListModel = (DefaultListModel) composerList.getModel();
        DefaultListModel authorListModel = (DefaultListModel) authorList.getModel();
        DefaultListModel tradListModel = (DefaultListModel) traditionalList.getModel();
        DefaultListModel transcriberListModel = (DefaultListModel) transcriberList.getModel();
        composerListModel.clear();
        authorListModel.clear();
        tradListModel.clear();
        transcriberListModel.clear();
        tune.getCreators().stream().forEach((personRole) -> {
            switch (personRole.getRole()) {
                case COMPOSER:
                    composerListModel.addElement(personRole);
                    break;
                case AUTHOR:
                    authorListModel.addElement(personRole);
                    break;
                case TRAD:
                    tradListModel.addElement(personRole);
                    break;
                case TRANSCRIBER:
                    transcriberListModel.addElement(personRole);
                    break;
                default:
                    break;
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        titlesTextArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        rythmTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tempoLabelComboBox = new javax.swing.JComboBox<>();
        tempoUnitComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        unitsPerMinuteComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        meterNumeratorSpinner = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        meterDenominatorSpinner = new javax.swing.JSpinner();
        useLetterCheckBox = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        defaultTimeValueComboBox = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        composerList = new javax.swing.JList<>();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        authorList = new javax.swing.JList<>();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        traditionalList = new javax.swing.JList<>();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        transcriberList = new javax.swing.JList<>();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        historyTextArea = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        originTextArea = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        commentsTextArea = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        copyrightTextArea = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        addVoiceButton = new javax.swing.JButton();
        openScoreLayoutButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        pitchComboBox = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        signatureComboBox = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        modeComboBox = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        clefComboBox = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        transposeSpinner = new javax.swing.JSpinner();
        jLabel24 = new javax.swing.JLabel();
        octaveComboBox = new javax.swing.JComboBox<>();
        newTuneButton = new javax.swing.JButton();

        setOpaque(false);

        jLabel1.setText("Titles:");

        titlesTextArea.setColumns(18);
        titlesTextArea.setRows(2);
        titlesTextArea.setTabSize(4);
        titlesTextArea.setNextFocusableComponent(rythmTextField);
        jScrollPane1.setViewportView(titlesTextArea);

        jLabel2.setText("Rythm:");

        rythmTextField.setNextFocusableComponent(tempoLabelComboBox);

        jLabel3.setText("Tempo:");

        tempoLabelComboBox.setEditable(true);
        tempoLabelComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        tempoLabelComboBox.setToolTipText("<html>Tempo name.<br>\nCan be left empty.<br>\nEditable."); // NOI18N
        tempoLabelComboBox.setNextFocusableComponent(tempoUnitComboBox);
        tempoLabelComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tempoLabelComboBoxActionPerformed(evt);
            }
        });

        tempoUnitComboBox.setEditable(true);
        tempoUnitComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        tempoUnitComboBox.setToolTipText("<html>Tempo Unit<br>\nEditable, but has to be of the format n/m where<br>\nn = positive number (preferably between 1 and 15)<br>\nm = 1 or the power of 2\n"); // NOI18N
        tempoUnitComboBox.setNextFocusableComponent(unitsPerMinuteComboBox);
        tempoUnitComboBox.setRequestFocusEnabled(true);

        jLabel4.setText("=");

        unitsPerMinuteComboBox.setEditable(true);
        unitsPerMinuteComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        unitsPerMinuteComboBox.setToolTipText("<html>Number of beats per minute<br>\nEditable");
        unitsPerMinuteComboBox.setNextFocusableComponent(meterNumeratorSpinner);
        unitsPerMinuteComboBox.setRequestFocusEnabled(true);

        jLabel5.setText("MM");

        jLabel7.setText("Meter:");

        meterNumeratorSpinner.setModel(new javax.swing.SpinnerNumberModel(3, 1, 15, 1));
        meterNumeratorSpinner.setNextFocusableComponent(meterDenominatorSpinner);
        meterNumeratorSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                meterNumeratorSpinnerStateChanged(evt);
            }
        });

        jLabel8.setText("/");

        meterDenominatorSpinner.setNextFocusableComponent(useLetterCheckBox);
        meterDenominatorSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                meterDenominatorSpinnerStateChanged(evt);
            }
        });

        useLetterCheckBox.setEnabled(false);
        useLetterCheckBox.setNextFocusableComponent(defaultTimeValueComboBox);

        jLabel6.setText("Time value:");

        defaultTimeValueComboBox.setToolTipText("<html>The default time value or note length used in the tune.\n"); // NOI18N
        defaultTimeValueComboBox.setNextFocusableComponent(pitchComboBox);

        jLabel9.setText("Composers:");

        composerList.setModel(new DefaultListModel());
        composerList.setNextFocusableComponent(authorList);
        jScrollPane5.setViewportView(composerList);

        jLabel10.setText("Authors:");

        authorList.setModel(new DefaultListModel());
        authorList.setNextFocusableComponent(traditionalList);
        jScrollPane6.setViewportView(authorList);

        jLabel11.setText("Traditional:");

        traditionalList.setModel(new DefaultListModel());
        traditionalList.setNextFocusableComponent(transcriberList);
        jScrollPane7.setViewportView(traditionalList);

        jLabel12.setText("Transcriber:");

        transcriberList.setModel(new DefaultListModel());
        transcriberList.setNextFocusableComponent(historyTextArea);
        jScrollPane8.setViewportView(transcriberList);

        jLabel13.setText("History:");

        historyTextArea.setColumns(12);
        historyTextArea.setRows(3);
        historyTextArea.setNextFocusableComponent(originTextArea);
        jScrollPane2.setViewportView(historyTextArea);

        jLabel16.setText("Origin:");

        originTextArea.setColumns(12);
        originTextArea.setRows(2);
        originTextArea.setNextFocusableComponent(commentsTextArea);
        jScrollPane3.setViewportView(originTextArea);

        jLabel17.setText("Comments:");

        commentsTextArea.setColumns(12);
        commentsTextArea.setRows(2);
        commentsTextArea.setNextFocusableComponent(copyrightTextArea);
        jScrollPane4.setViewportView(commentsTextArea);

        copyrightTextArea.setColumns(12);
        copyrightTextArea.setRows(2);
        copyrightTextArea.setNextFocusableComponent(addVoiceButton);
        jScrollPane9.setViewportView(copyrightTextArea);

        jLabel14.setText("Copyright:");

        addVoiceButton.setText("Add Voice");
        addVoiceButton.setNextFocusableComponent(openScoreLayoutButton);
        addVoiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVoiceButtonActionPerformed(evt);
            }
        });

        openScoreLayoutButton.setText("Score Layout");
        openScoreLayoutButton.setNextFocusableComponent(titlesTextArea);
        openScoreLayoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openScoreLayoutButtonActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Ubuntu", 2, 15)); // NOI18N
        jLabel15.setText("Key:");

        pitchComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pitchComboBox.setNextFocusableComponent(signatureComboBox);

        jLabel18.setText("Pitch:");

        signatureComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        signatureComboBox.setNextFocusableComponent(modeComboBox);

        jLabel19.setText("Signature:");

        jLabel20.setText("Modus:");

        modeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        modeComboBox.setNextFocusableComponent(clefComboBox);

        jLabel21.setFont(new java.awt.Font("Ubuntu", 2, 15)); // NOI18N
        jLabel21.setText("Modifiers:");

        jLabel22.setText("Clef:");

        clefComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        clefComboBox.setToolTipText("<html>Select clef.<br>\nThe number on some choices indecates which line the clef is drawn on.<br>\nThe defaults are:\n<dl>\n<dt>G = 2 (Treble Clef)</dt>\n<dt>F = 4 (Bass Clef)</dt>\n<dt>C = 3 (Alto Clef)</dt>\n</dl>\nThe others are:\n<dl>\n<dt>G1 = French Violin Clef</dt>\n<dt>F3 = Baritone Clef</dt>\n<dt>C4 = Tenor Clef</dt>\n<dt>C2 = Mezzosoprano Clef</dt>\n<dt>C1 = Soprano Clef</dt>\n</dl>");
        clefComboBox.setNextFocusableComponent(transposeSpinner);

        jLabel23.setText("Transpose:");

        transposeSpinner.setModel(new javax.swing.SpinnerNumberModel(0, -24, 24, 1));
        transposeSpinner.setNextFocusableComponent(octaveComboBox);

        jLabel24.setText("Octave:");

        octaveComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        octaveComboBox.setNextFocusableComponent(composerList);

        newTuneButton.setText("New Tune");
        newTuneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newTuneButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addVoiceButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(rythmTextField)
                    .addComponent(tempoLabelComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tempoUnitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unitsPerMinuteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(defaultTimeValueComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(meterNumeratorSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(meterDenominatorSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(useLetterCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(openScoreLayoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(newTuneButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(modeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(signatureComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pitchComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clefComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(transposeSpinner, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(octaveComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                                .addComponent(jLabel15))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(pitchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18)))
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rythmTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel10)
                                    .addComponent(signatureComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(tempoLabelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20)
                                    .addComponent(modeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(5, 5, 5)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(49, 49, 49)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tempoUnitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(unitsPerMinuteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(meterNumeratorSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8)
                                        .addComponent(meterDenominatorSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel22)
                                            .addComponent(clefComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(useLetterCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(defaultTimeValueComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel12)
                                        .addComponent(transposeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel23)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addVoiceButton)
                                        .addComponent(openScoreLayoutButton)
                                        .addComponent(jLabel24)
                                        .addComponent(newTuneButton))
                                    .addComponent(octaveComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 1, Short.MAX_VALUE))
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel14)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tempoLabelComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tempoLabelComboBoxActionPerformed
        updateTempo();
    }//GEN-LAST:event_tempoLabelComboBoxActionPerformed

    private void meterNumeratorSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_meterNumeratorSpinnerStateChanged
        checkMeter();
    }//GEN-LAST:event_meterNumeratorSpinnerStateChanged

    private void meterDenominatorSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_meterDenominatorSpinnerStateChanged
        checkMeter();
    }//GEN-LAST:event_meterDenominatorSpinnerStateChanged

    private void addVoiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVoiceButtonActionPerformed
        createNewVoice();
    }//GEN-LAST:event_addVoiceButtonActionPerformed

    private void openScoreLayoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openScoreLayoutButtonActionPerformed
        openScoreLayout();
    }//GEN-LAST:event_openScoreLayoutButtonActionPerformed

    private void newTuneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newTuneButtonActionPerformed
        createTune();
    }//GEN-LAST:event_newTuneButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addVoiceButton;
    private javax.swing.JList<String> authorList;
    private javax.swing.JComboBox<String> clefComboBox;
    private javax.swing.JTextArea commentsTextArea;
    private javax.swing.JList<String> composerList;
    private javax.swing.JTextArea copyrightTextArea;
    private javax.swing.JComboBox<String> defaultTimeValueComboBox;
    private javax.swing.JTextArea historyTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSpinner meterDenominatorSpinner;
    private javax.swing.JSpinner meterNumeratorSpinner;
    private javax.swing.JComboBox<String> modeComboBox;
    private javax.swing.JButton newTuneButton;
    private javax.swing.JComboBox<String> octaveComboBox;
    private javax.swing.JButton openScoreLayoutButton;
    private javax.swing.JTextArea originTextArea;
    private javax.swing.JComboBox<String> pitchComboBox;
    private javax.swing.JTextField rythmTextField;
    private javax.swing.JComboBox<String> signatureComboBox;
    private javax.swing.JComboBox<String> tempoLabelComboBox;
    private javax.swing.JComboBox<String> tempoUnitComboBox;
    private javax.swing.JTextArea titlesTextArea;
    private javax.swing.JList<String> traditionalList;
    private javax.swing.JList<String> transcriberList;
    private javax.swing.JSpinner transposeSpinner;
    private javax.swing.JComboBox<String> unitsPerMinuteComboBox;
    private javax.swing.JCheckBox useLetterCheckBox;
    // End of variables declaration//GEN-END:variables

    private void updateTempo() {
        if (tune == null) {
            Object selectedItem = tempoLabelComboBox.getSelectedItem();
            if (selectedItem instanceof Tempo.Marking) {
                unitsPerMinuteComboBox.getModel().setSelectedItem(((Tempo.Marking) selectedItem).getSuggested());
            }
        }
    }

    private void checkMeter() {
        Integer numerator = (Integer) meterNumeratorSpinner.getValue();
        Integer denominator = (Integer) meterDenominatorSpinner.getValue();
        if (null != denominator) {
            switch (denominator) {
                case 4:
                    if (null != numerator) {
                        switch (numerator) {
                            case 2:
                                useLetterCheckBox.setText("Use ₵ ?");
                                useLetterCheckBox.setToolTipText("<html>Check this if you whant to use<br>"
                                        + " <b>₵</b> instead of 3/4");
                                useLetterCheckBox.setEnabled(true);
                                break;
                            case 4:
                                useLetterCheckBox.setText("Use C ?");
                                useLetterCheckBox.setToolTipText("<html>Check this if you whant to use<br>"
                                        + "<b>C</b> instead of 4/4");
                                useLetterCheckBox.setEnabled(true);
                                break;
                            default:
                                useLetterCheckBox.setText("");
                                useLetterCheckBox.setToolTipText("");
                                useLetterCheckBox.setEnabled(false);
                                break;
                        }
                    }
                    break;
                case 2:
                    switch (numerator) {
                        case 1:
                            useLetterCheckBox.setText("Use ₵ ?");
                            useLetterCheckBox.setToolTipText("<html>Check this if you whant to use <br>"
                                    + "<b>₵</b> instead of 1/2");
                            useLetterCheckBox.setEnabled(true);
                            break;
                        case 2:
                            useLetterCheckBox.setText("Use C ?");
                            useLetterCheckBox.setToolTipText("<html>Check this if you whant to use<br>"
                                    + "<b>C</b> instead of 2/2");
                            useLetterCheckBox.setEnabled(true);
                            break;
                        default:
                            useLetterCheckBox.setText("");
                            useLetterCheckBox.setToolTipText("");
                            useLetterCheckBox.setEnabled(false);
                            break;
                    }
                    break;
                default:
                    useLetterCheckBox.setText("");
                    useLetterCheckBox.setToolTipText("");
                    useLetterCheckBox.setEnabled(false);
                    break;
            }
        }
    }

    private void createNewVoice() {
        CreateVoiceActon action = new CreateVoiceActon(parent, tune);
        action.actionPerformed(null);
        parent.getVoicesPanel().addVoice(action.get());
    }

    private void openScoreLayout() {
        if (tune != null) {
            new OpenScoreLayoutAction(parent, tune).actionPerformed(null);
        }
    }

    private void createTune() {
        CreateTuneAction action = new CreateTuneAction(parent);
        action.actionPerformed(null);
        setTune(action.get());
    }

    private void setTune(Tune tune) {
        this.tune = tune;
        setEditingEnabled(tune != null);
        refresh();
        titlesTextArea.requestFocus();
    }
}
