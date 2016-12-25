package abc.music.editor.gui.dialog;

import abc.music.core.domain.FormatTemplate;
import abc.music.core.domain.PostScriptFont;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.action.ShowPostScriptFontsAction;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author hl
 */
public class FormatTemplateDialog extends AmeDialog<FormatTemplate> {

    private FormatTemplate template;
    private List<FontPanel> fontPanels;
    private List<SpacePanel> spacePanels;

    public FormatTemplateDialog(AbcMusicEditor editor) {
        super(editor, "Format Template Editor");
    }

    @Override
    protected void init() {
        initComponents();
        initFields();
    }

    public void setFormatTemplate(FormatTemplate template) {
        this.template = template;
        updateFields();
    }

    private void updateFields() {
        if (template != null) {
            templateNameTextField.setText(template.getName());
            shortDescriptionTextArea.setText(template.getShortDescription());
            if (template.hasIndent()) {
                indentTextField.setText(template.getIndent().toString());
            }
            if (template.hasScale()) {
                scaleTextField.setText(template.getScale().toString());
            }
            if (template.hasMaxSrhinking()) {
                maxShrinkingTextField.setText(template.getMaxShrinking().toString());
            }
            if (template.hasStretchLastStaff()) {
                stretchLastStaffCheckBox.setSelected(template.getStretchLastStaff().equals(Boolean.TRUE));
            }
            if (template.hasLandscape()) {
                landscapeCheckBox.setSelected(template.getLandscape().equals(Boolean.TRUE));
            }
            if (template.hasHeaderLeft()) {
                headerLeftTextField.setText(template.getHeaderLeft());
            }
            if (template.hasHeaderCenter()) {
                headerCenterTextField.setText(template.getHeaderCenter());
            }
            if (template.hasHeaderRight()) {
                headerRightTextField.setText(template.getHeaderRight());
            }
            if (template.hasFooterLeft()) {
                footerLeftTextField.setText(template.getFooterLeft());
            }
            if (template.hasFooterCenter()) {
                footerCenterTextField.setText(template.getFooterCenter());
            }
            if (template.hasFooterRight()) {
                footerRightTextField.setText(template.getFooterRight());
            }
            if (template.hasBarsPerStaff()) {
                barsPerStaffTextField.setText(template.getBarsPerStaff().toString());
            }
            if (template.hasLineLength()) {
                lineLengthTextField.setText(template.getLineLength().toString());
            }
            for (FormatTemplate.Margin m : template.getMargins().keySet()) {
                if (template.getMargin(m) != null) {
                    switch (m) {
                        case TOP:
                            topMarginTextField.setText(template.getMargin(m).toString());
                            break;
                        case RIGHT:
                            rightMarginTextField.setText(template.getMargin(m).toString());
                            break;
                        case BOTTOM:
                            bottomMarginTextField.setText(template.getMargin(m).toString());
                            break;
                        case LEFT:
                            leftMarginTextField.setText(template.getMargin(m).toString());
                            break;
                    }
                }
            }
            for (SpacePanel p : spacePanels) {
                if (template.hasSpaceValue(p.getSpace())) {
                    p.setValue(template.getSpaceValue(p.getSpace()).toString());
                }
            }
            for (FontPanel p : fontPanels) {
                if (template.hasFontValue(p.getAmeFont())) {
                    p.setFontValue(template.getFontValue(p.getAmeFont()));
                } else {
                    FormatTemplate.FontValue value = new FormatTemplate.FontValue(PostScriptFont.EMPTY, 14);
                    p.setFontValue(value);
                }
            }
        }
    }

    private void initFields() {
        spacePanels = new ArrayList<>();
        initSpacesPanel();
        fontPanels = new ArrayList<>();
        List<FormatTemplate.Font> fonts = new ArrayList<>();
        fonts.addAll(FormatTemplate.getTitleFonts());
        fonts.addAll(FormatTemplate.getTextFonts());
        initFontsPanel(tntFontsPanel, fonts, true);
        initFontsPanel(otherFontsPanel, FormatTemplate.getOtherFonts(), false);
    }

    private void initFontsPanel(JPanel fontsPanel, List<FormatTemplate.Font> fonts, boolean setAll) {
        GridLayout layout = (GridLayout) fontsPanel.getLayout();
        int cells = layout.getRows() * layout.getColumns();
        fonts.stream().forEach((font) -> {
            FontPanel panel = new FontPanel(font);
            fontsPanel.add(panel);
            fontPanels.add(panel);
        });
        int cellIndex = fontsPanel.getComponentCount();
        while (cellIndex < cells - 1) {
            fontsPanel.add(new JLabel());
            cellIndex++;
        }
        if (setAll) {
            FontPanel allFontsPanel = new FontPanel(null);
            fontsPanel.add(allFontsPanel);
        } else {
            fontsPanel.add(new JLabel());
        }
    }

    private void initSpacesPanel() {
        GridLayout layout = (GridLayout) spacesPanel.getLayout();
        int cells = layout.getRows() * layout.getColumns();
        for (FormatTemplate.Space space : FormatTemplate.Space.values()) {
            SpacePanel panel = new SpacePanel(space);
            spacesPanel.add(panel);
            spacePanels.add(panel);
        }
        int cellIndex = spacesPanel.getComponentCount();
        while (cellIndex < cells) {
            spacesPanel.add(new JLabel());
            cellIndex++;
        }
        JLabel spacingInfoLabel = (JLabel) spacesPanel.getComponent(cells - 1);
        spacingInfoLabel.setHorizontalAlignment(JLabel.TRAILING);
        spacingInfoLabel.setText("<html>All values are in cm <i>(decimal)</i>  ");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        templateNameTextField = new javax.swing.JTextField();
        formatTemplateTabbedPane = new javax.swing.JTabbedPane();
        generalPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        indentTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        scaleTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        maxShrinkingTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        stretchLastStaffCheckBox = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        landscapeCheckBox = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        barsPerStaffTextField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        shortDescriptionTextArea = new javax.swing.JTextArea();
        jLabel20 = new javax.swing.JLabel();
        lineLengthTextField = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        headerLeftTextField = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        headerCenterTextField = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        headerRightTextField = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        footerLeftTextField = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        footerCenterTextField = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        footerRightTextField = new javax.swing.JTextField();
        marginAndSpacePanel = new javax.swing.JPanel();
        marginsPanel = new javax.swing.JPanel();
        topMarginTextField = new javax.swing.JTextField();
        rightMarginTextField = new javax.swing.JTextField();
        bottomMarginTextField = new javax.swing.JTextField();
        leftMarginTextField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        spacesPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        tntFontsPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        otherFontsPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        doneButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Template Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(templateNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(666, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(templateNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        formatTemplateTabbedPane.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("Indentation of first note line:");

        indentTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        indentTextField.setText("0,0");

        jLabel3.setText("cm");

        jLabel4.setText("Scaling of notes:");

        scaleTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel5.setFont(new java.awt.Font("Ubuntu", 2, 12)); // NOI18N
        jLabel5.setText("(default = 0.7)");

        jLabel6.setText("Maximum shrinking of notes:");

        maxShrinkingTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel7.setText("decimal");

        jLabel8.setText("decimal");

        jLabel9.setText("Stretch last staff:");

        jLabel10.setText("Landscape output:");

        jLabel11.setText("Number of bars per staff:");

        barsPerStaffTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel12.setFont(new java.awt.Font("Ubuntu", 2, 12)); // NOI18N
        jLabel12.setText("(Leave empty if you want to controll this in the notes)");

        jLabel19.setText("Template Description:");

        shortDescriptionTextArea.setColumns(20);
        shortDescriptionTextArea.setRows(3);
        jScrollPane1.setViewportView(shortDescriptionTextArea);

        jLabel20.setText("Line length:");

        lineLengthTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel21.setFont(new java.awt.Font("Ubuntu", 2, 12)); // NOI18N
        jLabel21.setText("(approximate number of letters per free text line.)");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(maxShrinkingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(indentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(scaleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel5))
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel8)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(landscapeCheckBox)
                                            .addComponent(stretchLastStaffCheckBox)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(barsPerStaffTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel12))))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(lineLengthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel21)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(indentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(scaleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(maxShrinkingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(stretchLastStaffCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(landscapeCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(barsPerStaffTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(lineLengthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Page"));

        jLabel22.setText("Header");

        jLabel23.setText("Left:");

        jLabel24.setText("Center:");

        jLabel25.setText("Right:");

        jLabel26.setText("Footer");

        jLabel27.setText("Left:");

        jLabel28.setText("Center:");

        jLabel29.setText("Right:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                            .addComponent(jLabel25))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(headerLeftTextField)
                            .addComponent(headerCenterTextField)
                            .addComponent(headerRightTextField)))
                    .addComponent(jLabel22)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel29))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(footerLeftTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                            .addComponent(footerCenterTextField)
                            .addComponent(footerRightTextField)))
                    .addComponent(jLabel26))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(headerLeftTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(headerCenterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(headerRightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(footerLeftTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(footerCenterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(footerRightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout generalPanelLayout = new javax.swing.GroupLayout(generalPanel);
        generalPanel.setLayout(generalPanelLayout);
        generalPanelLayout.setHorizontalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        generalPanelLayout.setVerticalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        formatTemplateTabbedPane.addTab("General", generalPanel);

        marginsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Margin"));

        topMarginTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        rightMarginTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        bottomMarginTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        leftMarginTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Top");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Right");

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Bottom");

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Left");

        jLabel17.setText("cm");

        jLabel18.setFont(new java.awt.Font("Ubuntu", 2, 12)); // NOI18N
        jLabel18.setText("(decimal)");

        javax.swing.GroupLayout marginsPanelLayout = new javax.swing.GroupLayout(marginsPanel);
        marginsPanel.setLayout(marginsPanelLayout);
        marginsPanelLayout.setHorizontalGroup(
            marginsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marginsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(marginsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(marginsPanelLayout.createSequentialGroup()
                        .addGroup(marginsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(leftMarginTextField)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addGap(27, 27, 27)
                        .addGroup(marginsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rightMarginTextField)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)))
                    .addGroup(marginsPanelLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(marginsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(topMarginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bottomMarginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        marginsPanelLayout.setVerticalGroup(
            marginsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marginsPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topMarginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(marginsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(marginsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rightMarginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leftMarginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addGap(18, 48, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bottomMarginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        spacesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Spaces"));
        spacesPanel.setLayout(new java.awt.GridLayout(12, 2, 5, 0));

        javax.swing.GroupLayout marginAndSpacePanelLayout = new javax.swing.GroupLayout(marginAndSpacePanel);
        marginAndSpacePanel.setLayout(marginAndSpacePanelLayout);
        marginAndSpacePanelLayout.setHorizontalGroup(
            marginAndSpacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marginAndSpacePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(marginsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(spacesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                .addContainerGap())
        );
        marginAndSpacePanelLayout.setVerticalGroup(
            marginAndSpacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marginAndSpacePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(marginAndSpacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spacesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(marginsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        formatTemplateTabbedPane.addTab("Margins & Spaces", marginAndSpacePanel);

        tntFontsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        tntFontsPanel.setLayout(new java.awt.GridLayout(10, 2, 5, 5));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tntFontsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 975, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tntFontsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        formatTemplateTabbedPane.addTab("Titles & Text Fonts", jPanel2);

        otherFontsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        otherFontsPanel.setLayout(new java.awt.GridLayout(10, 2, 5, 5));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(otherFontsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 975, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(otherFontsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        formatTemplateTabbedPane.addTab("Other Fonts", jPanel5);

        getContentPane().add(formatTemplateTabbedPane, java.awt.BorderLayout.CENTER);

        doneButton.setText("Done");
        doneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Show PS-Fonts");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 638, Short.MAX_VALUE)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doneButton)
                    .addComponent(cancelButton)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed
        ok();
    }//GEN-LAST:event_doneButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        cancel();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        showPsFonts();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barsPerStaffTextField;
    private javax.swing.JTextField bottomMarginTextField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton doneButton;
    private javax.swing.JTextField footerCenterTextField;
    private javax.swing.JTextField footerLeftTextField;
    private javax.swing.JTextField footerRightTextField;
    private javax.swing.JTabbedPane formatTemplateTabbedPane;
    private javax.swing.JPanel generalPanel;
    private javax.swing.JTextField headerCenterTextField;
    private javax.swing.JTextField headerLeftTextField;
    private javax.swing.JTextField headerRightTextField;
    private javax.swing.JTextField indentTextField;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox landscapeCheckBox;
    private javax.swing.JTextField leftMarginTextField;
    private javax.swing.JTextField lineLengthTextField;
    private javax.swing.JPanel marginAndSpacePanel;
    private javax.swing.JPanel marginsPanel;
    private javax.swing.JTextField maxShrinkingTextField;
    private javax.swing.JPanel otherFontsPanel;
    private javax.swing.JTextField rightMarginTextField;
    private javax.swing.JTextField scaleTextField;
    private javax.swing.JTextArea shortDescriptionTextArea;
    private javax.swing.JPanel spacesPanel;
    private javax.swing.JCheckBox stretchLastStaffCheckBox;
    private javax.swing.JTextField templateNameTextField;
    private javax.swing.JPanel tntFontsPanel;
    private javax.swing.JTextField topMarginTextField;
    // End of variables declaration//GEN-END:variables

    private void showPsFonts() {
        new ShowPostScriptFontsAction(editor).actionPerformed(null);
    }

    @Override
    protected void accept() {
        updateTemplate();
    }

    private void updateTemplate() {
        if (template == null) {
            template = new FormatTemplate();
        }
        template.setName(templateNameTextField.getText());
        template.setShortDescription(shortDescriptionTextArea.getText());
        template.setIndent(getAsDouble(indentTextField));
        template.setScale(getAsDouble(scaleTextField));
        template.setMaxShrinking(getAsDouble(maxShrinkingTextField));
        template.setStretchLastStaff(stretchLastStaffCheckBox.isSelected());
        template.setLandscape(landscapeCheckBox.isSelected());
        template.setHeaderLeft(headerLeftTextField.getText());
        template.setHeaderCenter(headerCenterTextField.getText());
        template.setHeaderRight(headerRightTextField.getText());
        template.setFooterLeft(footerLeftTextField.getText());
        template.setFooterCenter(footerCenterTextField.getText());
        template.setFooterRight(footerRightTextField.getText());
        template.setBarsPerStaff(getAsInteger(barsPerStaffTextField));
        template.setLineLength(getAsInteger(lineLengthTextField));
        template.setMargin(FormatTemplate.Margin.TOP, getAsDouble(topMarginTextField));
        template.setMargin(FormatTemplate.Margin.RIGHT, getAsDouble(rightMarginTextField));
        template.setMargin(FormatTemplate.Margin.BOTTOM, getAsDouble(bottomMarginTextField));
        template.setMargin(FormatTemplate.Margin.LEFT, getAsDouble(leftMarginTextField));
        for (SpacePanel sp : spacePanels) {
            template.setSpace(sp.getSpace(), sp.getValue());
        }
        for (FontPanel fp : fontPanels) {
            template.setFont(fp.getAmeFont(), fp.getFontValue());
        }
        set(template);
    }

    private Integer getAsInteger(JTextField field) {
        try {
            return Integer.valueOf(field.getText().replaceAll("\\D", ""));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Double getAsDouble(JTextField field) {
        try {
            return Double.valueOf(field.getText().replaceAll(",", "."));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void setAllFonts(PostScriptFont psFont) {
        fontPanels.stream().forEach((p) -> {
            p.setPsFont(psFont);
        });
    }

    private class SpacePanel extends JPanel {

        private final JTextField field;
        private final FormatTemplate.Space space;
        private final Dimension dimension = new Dimension(42, 27);

        public SpacePanel(FormatTemplate.Space space) {
            super(new BorderLayout());
            super.add(new JLabel(space.getName() + ":"), BorderLayout.CENTER);
            this.space = space;
            field = new JTextField();
            field.setSize(dimension);
            field.setPreferredSize(dimension);
            field.setMinimumSize(dimension);
            field.setMaximumSize(dimension);
            field.setHorizontalAlignment(JTextField.TRAILING);
            super.add(field, BorderLayout.EAST);
        }

        public FormatTemplate.Space getSpace() {
            return space;
        }

        public void setValue(String value) {
            if (value != null && !value.isEmpty()) {
                try {
                    setValue(Double.valueOf(value.replaceAll(",", ".")));
                } catch (NumberFormatException e) {
                    Logger.getLogger(FormatTemplateDialog.class.getName()).log(Level.WARNING, "Could not convert value {0} to a Double.", value);
                }
            }
        }

        public void setValue(Double value) {
            field.setText(value.toString().replaceAll("\\.", ","));
        }

        public Double getValue() {
            if (field.getText() != null && !field.getText().isEmpty()) {
                try {
                    return Double.valueOf(field.getText().replaceAll(",", "."));
                } catch (NumberFormatException e) {
                    Logger.getLogger(FormatTemplateDialog.class.getName()).log(Level.WARNING, "Could not convert value {0} to a Double.", field.getText());
                }
            }
            return null;
        }
    }

    private class FontPanel extends JPanel {

        private final FormatTemplate.Font ameFont;
        private final JComboBox postScriptFontComboBox;
        private final Dimension labelDimension = new Dimension(170, 27);
        private final Dimension sizeDimension = new Dimension(72, 27);
        private final int DEFAULT_SIZE = 14;
        private JSpinner sizeSpinner;

        public FontPanel(FormatTemplate.Font font) {
            super(new BorderLayout());
            this.ameFont = font;
            JLabel label;
            if (ameFont != null) {
                label = new JLabel(ameFont.getName() + ":");
            } else {
                label = new JLabel("Select for All Fonts:");
            }
            label.setSize(labelDimension);
            label.setPreferredSize(labelDimension);
            label.setMinimumSize(labelDimension);
            label.setMaximumSize(labelDimension);
            super.add(label, BorderLayout.WEST);
            this.postScriptFontComboBox = new JComboBox(new DefaultComboBoxModel(PostScriptFont.values()));
            this.postScriptFontComboBox.setSelectedItem(PostScriptFont.DEFAULT_FONT);
            if (ameFont == null) {
                this.postScriptFontComboBox.addItemListener((ItemEvent e) -> {
                    if (e.getSource() instanceof JComboBox) {
                        JComboBox box = (JComboBox) e.getSource();
                        PostScriptFont allFont = (PostScriptFont) box.getSelectedItem();
                        setAllFonts(allFont);
                    }
                });
            }
            super.add(this.postScriptFontComboBox, BorderLayout.CENTER);
            SpinnerNumberModel model = new SpinnerNumberModel(DEFAULT_SIZE, 4, 72, 1);
            if (ameFont == null) {
                JLabel sizePlaceHolderLabel = new JLabel();
                sizePlaceHolderLabel.setSize(sizeDimension);
                sizePlaceHolderLabel.setPreferredSize(sizeDimension);
                sizePlaceHolderLabel.setMinimumSize(sizeDimension);
                sizePlaceHolderLabel.setMaximumSize(sizeDimension);
                super.add(sizePlaceHolderLabel, BorderLayout.EAST);
            } else {
                this.sizeSpinner = new JSpinner(model);
                this.sizeSpinner.setSize(sizeDimension);
                this.sizeSpinner.setPreferredSize(sizeDimension);
                this.sizeSpinner.setMinimumSize(sizeDimension);
                this.sizeSpinner.setMaximumSize(sizeDimension);
                super.add(this.sizeSpinner, BorderLayout.EAST);
            }
        }

        public FormatTemplate.Font getAmeFont() {
            return ameFont;
        }

        public void setPsFont(PostScriptFont psFont) {
            this.postScriptFontComboBox.setSelectedItem(psFont);
        }

        public void setFontValue(FormatTemplate.FontValue value) {
            if (value != null) {
                if (value.getPsFont() != null) {
                    this.postScriptFontComboBox.setSelectedItem(value.getPsFont());
                }
                if (this.sizeSpinner != null && value.getSize() != null) {
                    this.sizeSpinner.setValue(value.getSize());
                }
            }
        }

        public FormatTemplate.FontValue getFontValue() {
            PostScriptFont psFont = (PostScriptFont) postScriptFontComboBox.getSelectedItem();
            if (psFont.equals(PostScriptFont.EMPTY)) {
                return null;
            }
            Integer size = DEFAULT_SIZE;
            if (this.sizeSpinner != null) {
                size = (Integer) this.sizeSpinner.getValue();
            }
            return new FormatTemplate.FontValue(psFont, size);
        }
    }
}
