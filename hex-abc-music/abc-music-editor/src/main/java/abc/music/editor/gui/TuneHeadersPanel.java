package abc.music.editor.gui;

import abc.music.core.domain.Comment;
import abc.music.core.domain.Copyright;
import abc.music.core.domain.History;
import abc.music.core.domain.Key;
import abc.music.core.domain.Modifier;
import abc.music.core.domain.Origin;
import abc.music.core.domain.Person;
import abc.music.core.domain.PersonRole;
import abc.music.core.domain.Tempo;
import abc.music.core.domain.Tune;
import abc.music.core.util.CircleOfFifths;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.AmeConstants;
import abc.music.editor.action.CreateFileAction;
import abc.music.editor.action.CreateVoiceActon;
import abc.music.editor.action.CreateTuneAction;
import abc.music.editor.action.HandleTuneInBooksAction;
import abc.music.editor.action.OpenScoreLayoutAction;
import abc.music.editor.action.PrintAction;
import abc.music.editor.gui.support.PersonRoleListMouseListener;
import abc.music.editor.gui.support.SharpFlatNaturalKeyListener;
import abc.music.editor.gui.support.TransposeComboBoxModel;
import abc.music.editor.gui.support.TransposeMap;
import java.awt.Dimension;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.SpinnerListModel;
import nu.hex.abc.music.service.util.TuneHelper;
import nu.hex.mediatype.CommonMediaType;

/**
 *
 * @author hl
 */
public class TuneHeadersPanel extends AmePanel {

    private Tune tune;
    private Key originalKey;

    public TuneHeadersPanel(AbcMusicEditor parent) {
        this(parent, null);
    }

    public TuneHeadersPanel(AbcMusicEditor parent, Tune tune) {
        super(parent, "Tune Headers", AmeConstants.SMALL_TITLE_FONT);
        this.tune = tune;
        if (tune != null) {
            originalKey = tune.getKey();
        }
    }

    @Override
    protected void init() {
        initComponents();
        List<Tempo.Marking> tempi = Tempo.getTempi();
        tempi.add(0, null);
        titlesTextArea.addKeyListener(new SharpFlatNaturalKeyListener());
        historyTextArea.addKeyListener(new SharpFlatNaturalKeyListener());
        originTextArea.addKeyListener(new SharpFlatNaturalKeyListener());
        copyrightTextArea.addKeyListener(new SharpFlatNaturalKeyListener());
        commentsTextArea.addKeyListener(new SharpFlatNaturalKeyListener());
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
        transposeComboBox.setModel(new TransposeComboBoxModel());
        transposeComboBox.setSize(keyFieldsDimension);
        transposeComboBox.setPreferredSize(keyFieldsDimension);
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
        lyricsButton.setEnabled(true);
        booksButton.setEnabled(true);
        TuneHelper tuneHelper = new TuneHelper(tune);
        titlesTextArea.setText(tuneHelper.getTitlesAsString());
        rythmTextField.setText(tune.getRythm());
        historyTextArea.setText(tuneHelper.getHistoryAsString());
        originTextArea.setText(tuneHelper.getOriginAsString());
        commentsTextArea.setText(tuneHelper.getCommentsAsString());
        copyrightTextArea.setText(tuneHelper.getCopyrightAsString());
        tempoUnitComboBox.setSelectedItem(tune.getTempo().getUnit());
        unitsPerMinuteComboBox.setSelectedItem(tune.getTempo().getUnitsPerMinute());
        System.out.println(tune.getTimeValue());
        defaultTimeValueComboBox.getModel().setSelectedItem(tune.getTimeValue());
        meterNumeratorSpinner.setValue(tune.getMeter().getNumerator());
        meterDenominatorSpinner.setValue(tune.getMeter().getDenominator());
        useSymbolCheckBox.setSelected(tune.getMeter().useSymbol());
        updateLists();
        pitchComboBox.setSelectedItem(tune.getKey().getPitch());
        signatureComboBox.setSelectedItem(tune.getKey().getSignature());
        modeComboBox.setSelectedItem(tune.getKey().getMode());
        clefComboBox.setSelectedItem(tune.getKey().getModifier().getClef());
        transposeComboBox.setSelectedItem(TransposeMap.getItem(tune.getKey().getModifier().getTranspose()));
        octaveComboBox.setSelectedItem(tune.getKey().getModifier().getOctave());
        if (tune.getVoices() != null && !tune.getVoices().isEmpty()) {
            editor.getVoicesPanel().setVoices(tune.getVoices());
        }
        if (tune.getLyrics() != null && !tune.getLyrics().isEmpty()) {
            editor.getVoicesPanel().addLyrics(tune.getLyrics());
        }
    }

    private void resetFields() {
        newTuneButton.setEnabled(editor.getProject() != null);
        openScoreLayoutButton.setEnabled(false);
        addVoiceButton.setEnabled(false);
        lyricsButton.setEnabled(false);
        booksButton.setEnabled(false);
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
        traditionalList.addMouseListener(new PersonRoleListMouseListener(this, traditionalList, Person.Role.TRAD, tune));
        transcriberList.addMouseListener(new PersonRoleListMouseListener(this, transcriberList, Person.Role.TRANSCRIBER, tune));
        pitchComboBox.setSelectedItem(Key.Pitch.DEFAULT_PITCH);
        signatureComboBox.setSelectedItem(Key.Signature.DEFAULT_SIGNATURE);
        modeComboBox.setSelectedItem(Key.Mode.DEFAULT_MODE);
        clefComboBox.setSelectedItem(Modifier.Clef.DEFAULT_CLEF);
        transposeComboBox.setSelectedItem(TransposeMap.getDefaultItem());
        octaveComboBox.setSelectedItem(Modifier.OctaveClef.DEFAULT_OCTAVE);
        if (editor.getVoicesPanel() != null) {
            editor.getVoicesPanel().clearVoicesPanel();
        }
    }

    public void setEditingEnabled(boolean enabled) {
        openScoreLayoutButton.setEnabled(enabled);
        printButton.setEnabled(enabled);
        addVoiceButton.setEnabled(enabled);
        lyricsButton.setEnabled(enabled);
        booksButton.setEnabled(enabled);
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
        transposeComboBox.setEnabled(enabled);
        octaveComboBox.setEnabled(enabled);
        abcButton.setEnabled(enabled);
        postScriptButton.setEnabled(enabled);
        pdfButton.setEnabled(enabled);
        applyKeyChangesButton.setEnabled(enabled);
    }

    public void updateLists() {
        DefaultListModel<PersonRole> composerListModel = (DefaultListModel) composerList.getModel();
        DefaultListModel<PersonRole> authorListModel = (DefaultListModel) authorList.getModel();
        DefaultListModel<PersonRole> tradListModel = (DefaultListModel) traditionalList.getModel();
        DefaultListModel<PersonRole> transcriberListModel = (DefaultListModel) transcriberList.getModel();
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

    public Tune getTune() {
        return tune;
    }

    public void updateTune() {
        if (tune != null) {
            tune.setTitles(Collections.EMPTY_LIST);
            for (String s : titlesTextArea.getText().split("\n")) {
                tune.addTitle(s.trim());
            }
            tune.setRythm(rythmTextField.getText().trim());
            if (tempoLabelComboBox.getSelectedItem() != null) {
                tune.getTempo().setLabel(tempoLabelComboBox.getSelectedItem().toString());
            }
            if (tempoUnitComboBox.getSelectedItem() != null) {
                tune.getTempo().setUnit((Tempo.Unit) tempoUnitComboBox.getSelectedItem());
            }
            if (unitsPerMinuteComboBox.getSelectedItem() != null) {
                tune.getTempo().setUnitsPerMinute((Integer) unitsPerMinuteComboBox.getSelectedItem());
            }
            if (modeComboBox.getSelectedItem() != null) {
                tune.getKey().setMode((Key.Mode) modeComboBox.getSelectedItem());
            }
            if (pitchComboBox.getSelectedItem() != null) {
                tune.getKey().setPitch((Key.Pitch) pitchComboBox.getSelectedItem());
            }
            if (signatureComboBox.getSelectedItem() != null) {
                tune.getKey().setSignature((Key.Signature) signatureComboBox.getSelectedItem());
            }
            if (clefComboBox.getSelectedItem() != null) {
                tune.getKey().getModifier().setClef((Modifier.Clef) clefComboBox.getSelectedItem());
            }
            if (octaveComboBox.getSelectedItem() != null) {
                tune.getKey().getModifier().setOctave((Modifier.OctaveClef) octaveComboBox.getSelectedItem());
            }
            if (transposeComboBox.getSelectedItem() != null) {
                tune.getKey().getModifier().setTranspose(((TransposeMap.Item) transposeComboBox.getSelectedItem()).getInterval());
            }
            if (meterNumeratorSpinner.getValue() != null) {
                tune.getMeter().setNumerator((Integer) meterNumeratorSpinner.getValue());
            }
            if (meterDenominatorSpinner.getValue() != null) {
                tune.getMeter().setDenominator((Integer) meterDenominatorSpinner.getValue());
            }
            tune.getMeter().setUseSymbol(useSymbolCheckBox.isSelected());
            if (defaultTimeValueComboBox.getSelectedItem() != null) {
                tune.setTimeValue((Tune.TimeValue) defaultTimeValueComboBox.getSelectedItem());
            }
            tune.setCreators(Collections.EMPTY_LIST);
            addCreators((DefaultListModel) composerList.getModel(), Person.Role.COMPOSER);
            addCreators((DefaultListModel) authorList.getModel(), Person.Role.AUTHOR);
            addCreators((DefaultListModel) traditionalList.getModel(), Person.Role.TRAD);
            addCreators((DefaultListModel) transcriberList.getModel(), Person.Role.TRANSCRIBER);
            tune.setHistory(Collections.EMPTY_LIST);
            for (String s : historyTextArea.getText().split("\n")) {
                if (!s.isEmpty()) {
                    tune.addHistory(new History(s));
                }
            }
            tune.setOrigin(Collections.EMPTY_LIST);
            for (String s : originTextArea.getText().split("\n")) {
                if (!s.isEmpty()) {
                    tune.addOrigin(new Origin(s));
                }
            }
            tune.setComments(Collections.EMPTY_LIST);
            for (String s : commentsTextArea.getText().split("\n")) {
                if (!s.isEmpty()) {
                    tune.addComment(new Comment(s));
                }
            }
            tune.setCopyright(Collections.EMPTY_LIST);
            for (String s : copyrightTextArea.getText().split("\n")) {
                if (!s.isEmpty()) {
                    tune.addCopyright(new Copyright(s));
                }
            }
            if (!tune.getVoices().isEmpty()) {
                getEditor().getVoicesPanel().updateVoices();
            }
            if (getEditor().getVoicesPanel().hasLyricsPanel()) {
                getEditor().getVoicesPanel().updateLyrics();
            }
        }
    }

    private void addCreators(DefaultListModel listModel, Person.Role role) {
        for (int i = 0; i < listModel.getSize(); i++) {
            DefaultListModel<PersonRole> model = (DefaultListModel<PersonRole>) listModel;
            tune.addCreator(model.getElementAt(i));
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
        useSymbolCheckBox = new javax.swing.JCheckBox();
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
        jLabel24 = new javax.swing.JLabel();
        octaveComboBox = new javax.swing.JComboBox<>();
        transposeComboBox = new javax.swing.JComboBox<>();
        applyKeyChangesButton = new javax.swing.JButton();
        buttonsPanel = new javax.swing.JPanel();
        addVoiceButton = new javax.swing.JButton();
        openScoreLayoutButton = new javax.swing.JButton();
        newTuneButton = new javax.swing.JButton();
        lyricsButton = new javax.swing.JButton();
        pdfButton = new javax.swing.JButton();
        postScriptButton = new javax.swing.JButton();
        abcButton = new javax.swing.JButton();
        booksButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();

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

        meterDenominatorSpinner.setNextFocusableComponent(useSymbolCheckBox);
        meterDenominatorSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                meterDenominatorSpinnerStateChanged(evt);
            }
        });

        useSymbolCheckBox.setEnabled(false);
        useSymbolCheckBox.setNextFocusableComponent(defaultTimeValueComboBox);

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
        historyTextArea.setLineWrap(true);
        historyTextArea.setRows(3);
        historyTextArea.setWrapStyleWord(true);
        historyTextArea.setNextFocusableComponent(originTextArea);
        jScrollPane2.setViewportView(historyTextArea);

        jLabel16.setText("Origin:");

        originTextArea.setColumns(12);
        originTextArea.setLineWrap(true);
        originTextArea.setRows(2);
        originTextArea.setWrapStyleWord(true);
        originTextArea.setNextFocusableComponent(commentsTextArea);
        jScrollPane3.setViewportView(originTextArea);

        jLabel17.setText("Comments:");

        commentsTextArea.setColumns(12);
        commentsTextArea.setLineWrap(true);
        commentsTextArea.setRows(2);
        commentsTextArea.setWrapStyleWord(true);
        commentsTextArea.setNextFocusableComponent(copyrightTextArea);
        jScrollPane4.setViewportView(commentsTextArea);

        copyrightTextArea.setColumns(12);
        copyrightTextArea.setLineWrap(true);
        copyrightTextArea.setRows(2);
        copyrightTextArea.setWrapStyleWord(true);
        copyrightTextArea.setNextFocusableComponent(addVoiceButton);
        jScrollPane9.setViewportView(copyrightTextArea);

        jLabel14.setText("Copyright:");

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

        jLabel23.setText("Transpose:");

        jLabel24.setText("Octave:");

        octaveComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        octaveComboBox.setNextFocusableComponent(composerList);

        transposeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        applyKeyChangesButton.setText("Apply");
        applyKeyChangesButton.setToolTipText("Apply changes to all voices");
        applyKeyChangesButton.setEnabled(false);
        applyKeyChangesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyKeyChangesButtonActionPerformed(evt);
            }
        });

        buttonsPanel.setOpaque(false);

        addVoiceButton.setText("Add Voice");
        addVoiceButton.setNextFocusableComponent(openScoreLayoutButton);
        addVoiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVoiceButtonActionPerformed(evt);
            }
        });

        openScoreLayoutButton.setText("Score");
        openScoreLayoutButton.setNextFocusableComponent(titlesTextArea);
        openScoreLayoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openScoreLayoutButtonActionPerformed(evt);
            }
        });

        newTuneButton.setText("New Tune");
        newTuneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newTuneButtonActionPerformed(evt);
            }
        });

        lyricsButton.setText("Lyrics");
        lyricsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lyricsButtonActionPerformed(evt);
            }
        });

        pdfButton.setBackground(java.awt.Color.white);
        pdfButton.setFont(AmeConstants.TAB_LABEL_FONT);
        pdfButton.setForeground(AmeConstants.TITLE_COLOR);
        pdfButton.setText("PDF");
        pdfButton.setEnabled(false);
        pdfButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfButtonActionPerformed(evt);
            }
        });

        postScriptButton.setBackground(java.awt.Color.white);
        postScriptButton.setFont(AmeConstants.TAB_LABEL_FONT);
        postScriptButton.setForeground(AmeConstants.TITLE_COLOR);
        postScriptButton.setText("PS");
        postScriptButton.setEnabled(false);
        postScriptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postScriptButtonActionPerformed(evt);
            }
        });

        abcButton.setBackground(java.awt.Color.white);
        abcButton.setFont(AmeConstants.TAB_LABEL_FONT);
        abcButton.setForeground(AmeConstants.TITLE_COLOR);
        abcButton.setText("ABC");
        abcButton.setEnabled(false);
        abcButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abcButtonActionPerformed(evt);
            }
        });

        booksButton.setText("Books");
        booksButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                booksButtonActionPerformed(evt);
            }
        });

        printButton.setText("Print");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newTuneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addVoiceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lyricsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(openScoreLayoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(booksButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(pdfButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(postScriptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(abcButton)
                .addContainerGap(410, Short.MAX_VALUE))
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addVoiceButton)
                    .addComponent(openScoreLayoutButton)
                    .addComponent(newTuneButton)
                    .addComponent(lyricsButton)
                    .addComponent(pdfButton)
                    .addComponent(postScriptButton)
                    .addComponent(abcButton)
                    .addComponent(booksButton)
                    .addComponent(printButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                .addComponent(useSymbolCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(applyKeyChangesButton)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(modeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(signatureComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pitchComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(clefComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(octaveComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(transposeComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2))))
                .addContainerGap())
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
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15)
                                .addComponent(applyKeyChangesButton))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                                        .addComponent(useSymbolCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(defaultTimeValueComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel12))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(transposeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel23))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(octaveComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void pdfButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfButtonActionPerformed
        createPdfFile();
    }//GEN-LAST:event_pdfButtonActionPerformed

    private void abcButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abcButtonActionPerformed
        createAbcFile();
    }//GEN-LAST:event_abcButtonActionPerformed

    private void postScriptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postScriptButtonActionPerformed
        createPostScriptFile();
    }//GEN-LAST:event_postScriptButtonActionPerformed

    private void applyKeyChangesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyKeyChangesButtonActionPerformed
        handleKeyChange();
    }//GEN-LAST:event_applyKeyChangesButtonActionPerformed

    private void lyricsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lyricsButtonActionPerformed
        openLyricsPanel();
    }//GEN-LAST:event_lyricsButtonActionPerformed

    private void booksButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_booksButtonActionPerformed
        openBooks();
    }//GEN-LAST:event_booksButtonActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        printTune();
    }//GEN-LAST:event_printButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abcButton;
    private javax.swing.JButton addVoiceButton;
    private javax.swing.JButton applyKeyChangesButton;
    private javax.swing.JList<String> authorList;
    private javax.swing.JButton booksButton;
    private javax.swing.JPanel buttonsPanel;
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
    private javax.swing.JButton lyricsButton;
    private javax.swing.JSpinner meterDenominatorSpinner;
    private javax.swing.JSpinner meterNumeratorSpinner;
    private javax.swing.JComboBox<String> modeComboBox;
    private javax.swing.JButton newTuneButton;
    private javax.swing.JComboBox<String> octaveComboBox;
    private javax.swing.JButton openScoreLayoutButton;
    private javax.swing.JTextArea originTextArea;
    private javax.swing.JButton pdfButton;
    private javax.swing.JComboBox<String> pitchComboBox;
    private javax.swing.JButton postScriptButton;
    private javax.swing.JButton printButton;
    private javax.swing.JTextField rythmTextField;
    private javax.swing.JComboBox<String> signatureComboBox;
    private javax.swing.JComboBox<String> tempoLabelComboBox;
    private javax.swing.JComboBox<String> tempoUnitComboBox;
    private javax.swing.JTextArea titlesTextArea;
    private javax.swing.JList<String> traditionalList;
    private javax.swing.JList<String> transcriberList;
    private javax.swing.JComboBox<String> transposeComboBox;
    private javax.swing.JComboBox<String> unitsPerMinuteComboBox;
    private javax.swing.JCheckBox useSymbolCheckBox;
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
        if (denominator == 4 && numerator == 4) {
            useSymbolCheckBox.setText("Use C ?");
            useSymbolCheckBox.setToolTipText("<html>Check this if you want to use<br>"
                    + " <b>C</b> instead of 4/4");
            useSymbolCheckBox.setEnabled(true);
        } else if (denominator == 2 && numerator == 2) {
            useSymbolCheckBox.setText("Use ₵ ?");
            useSymbolCheckBox.setToolTipText("<html>Check this if you want to use<br>"
                    + " <b>₵</b> instead of 2/2");
            useSymbolCheckBox.setEnabled(true);
        } else {
            useSymbolCheckBox.setText("");
            useSymbolCheckBox.setToolTipText("");
            useSymbolCheckBox.setEnabled(false);
        }
    }

    private void createNewVoice() {
        CreateVoiceActon action = new CreateVoiceActon(editor, tune);
        action.actionPerformed(null);
        editor.getVoicesPanel().addVoice(action.get());
    }

    private void openScoreLayout() {
        if (tune != null) {
            new OpenScoreLayoutAction(editor, tune).actionPerformed(null);
        }
    }

    private void createTune() {
        CreateTuneAction action = new CreateTuneAction(editor);
        action.actionPerformed(null);
        setTune(action.get());
    }

    public void setTune(Tune tune) {
        updateTune();
        if (this.tune != null && !this.tune.getTitles().get(0).isEmpty()) {
            editor.getLatestTunesPane().add(this.tune);
        }
        this.tune = tune;
        setEditingEnabled(tune != null);
        refresh();
        titlesTextArea.requestFocus();
    }

    private void createSvgFile() {
        if (tune != null) {
//            CreateSvgDocumentAction action = new CreateSvgDocumentAction(editor, tune);
//            action.actionPerformed(null);
//            XmlDocument result = action.get();
//            if (result != null) {
//
//            }
        }
    }

    private void createAbcFile() {
        new CreateFileAction(editor, tune, CommonMediaType.TEXT_VND_ABC).actionPerformed(null);
    }

    private void createPostScriptFile() {
        new CreateFileAction(editor, tune, CommonMediaType.APPLICATION_POSTSCRIPT).actionPerformed(null);
    }

    private void createPdfFile() {
        new CreateFileAction(editor, tune, CommonMediaType.APPLICATION_PDF).actionPerformed(null);
    }

    private void handleKeyChange() {
        if (tune != null) {
            Key.Pitch oldPitch = tune.getKey().getPitch();
            Key.Pitch newPitch = (Key.Pitch) pitchComboBox.getSelectedItem();
            tune.getKey().setPitch(newPitch);
            Key.Signature oldSignature = tune.getKey().getSignature();
            Key.Signature newSignature = (Key.Signature) signatureComboBox.getSelectedItem();
            tune.getKey().setSignature(newSignature);
            Key.Mode mode = (Key.Mode) modeComboBox.getSelectedItem();
            tune.getKey().setMode(mode);
            Integer steps = CircleOfFifths.getSteps(oldPitch, oldSignature, newPitch, newSignature);
            tune.getVoices().stream().forEach((voice) -> {
                String newKeyString = CircleOfFifths.getNew(voice.getKey().getPitch(), voice.getKey().getSignature(), steps);
                voice.getKey().setPitch(Key.getPitchFromString(newKeyString));
                voice.getKey().setSignature(Key.getSignatureFromString(newKeyString));
                voice.getKey().setMode(mode);
            });
            getEditor().getVoicesPanel().updateVoicePanels();
        }
    }

    private void openLyricsPanel() {
        editor.getVoicesPanel().addLyrics(tune.getLyrics());
    }

    private void openBooks() {
        new HandleTuneInBooksAction(editor, tune).actionPerformed(null);
    }

    private void printTune() {
        new PrintAction(editor, tune).actionPerformed(null);
    }
}
