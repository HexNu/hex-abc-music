package abc.music.editor.gui.dialog.tune;

import abc.music.editor.AbcMusicEditor;
import abc.music.editor.gui.AmeSidePanel;
import abc.music.editor.gui.dialog.AmeDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import se.digitman.lightxml.DocumentToXmlNodeParser;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-07
 *
 * @author hl
 */
public class OrnamentChooser extends AmeDialog<String> {

    private final Dimension dimension = new Dimension(1200, 500);
    private AmeSidePanel contentPanel;
    private AmeSidePanel displayPanel;
    private JTabbedPane tabPane;
    private JPanel displayContentPanel;
    private List<String> ornaments;

    public OrnamentChooser(AbcMusicEditor editor) {
        super(editor, "Choose ornament");
        super.setSize(dimension);
        super.setPreferredSize(dimension);
        super.setLocationRelativeTo(editor);
        super.setVisible(true);
    }

    @Override
    protected void init() {
        setLayout(new BorderLayout(5, 5));
        ornaments = new ArrayList<>();
        contentPanel = new AmeSidePanel(editor) {
            @Override
            protected void init() {
                setLayout(new BorderLayout());
                tabPane = new JTabbedPane(JTabbedPane.LEFT);
                add(tabPane, BorderLayout.CENTER);
            }
        };
        add(contentPanel, BorderLayout.CENTER);
        displayPanel = new AmeSidePanel(editor) {
            @Override
            protected void init() {
                setLayout(new BorderLayout());
                displayContentPanel = new JPanel(new BorderLayout());
                displayContentPanel.setBackground(Color.WHITE);
                displayContentPanel.setPreferredSize(new Dimension(240, 300));
                add(displayContentPanel, BorderLayout.CENTER);
                JPanel buttonPanel = new JPanel();
                buttonPanel.setPreferredSize(new Dimension(240, 48));
                Dimension buttonDimension = new Dimension(90, 28);
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setPreferredSize(buttonDimension);
                cancelButton.addActionListener((ActionEvent e) -> {
                    cancel();
                });
                buttonPanel.add(cancelButton);
                JButton doneButton = new JButton("Done");
                doneButton.setPreferredSize(buttonDimension);
                doneButton.addActionListener((ActionEvent e) -> {
                    ok();
                });
                buttonPanel.add(doneButton);
                add(buttonPanel, BorderLayout.SOUTH);
            }
        };
        add(displayPanel, BorderLayout.EAST);

        setupContent();
    }

    @Override
    protected void accept() {
        String result = "";
        result = ornaments.stream().filter((ornament) -> (ornament != null)).map((ornament) -> ornament).reduce(result, String::concat);
        set(result);
    }

    private void setupContent() {
        XmlNode list = new DocumentToXmlNodeParser(getClass().getClassLoader().getResourceAsStream("ornaments/ornaments.xml")).parse();
        list.getChildren("group").stream().forEach((groupNode) -> {
            int rows = 7;
            int cols = 3;
            int panelIndex = 0;
            JPanel groupPanel = new JPanel(new GridLayout(rows, cols, 5, 5));
            groupPanel.setBackground(Color.WHITE);
            tabPane.add(groupNode.getAttribute("label"), groupPanel);
            for (XmlNode itemNode : groupNode.getChildren("item")) {
                JPanel itemPanel = new JPanel(new BorderLayout());
                itemPanel.setBackground(Color.WHITE);
                itemPanel.setBorder(BorderFactory.createTitledBorder(""));
                OrnamentCheckBox itemBox = new OrnamentCheckBox(itemNode.getText(), itemNode.getAttribute("value"));
                itemPanel.add(itemBox, BorderLayout.WEST);
                JLabel itemLabel = new JLabel(itemNode.getAttribute("value"),
                        getItemIcon("icons/ornaments/" + itemNode.getAttribute("value").replaceAll("!", "") + "-icon.png"), JLabel.LEADING);
                itemLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 1) {
                            displayExplanatoryImageAndText(itemNode.getText(), itemNode.getAttribute("value"));
                        } else if (e.getClickCount() == 2) {
                            JLabel label = (JLabel) e.getSource();
                            OrnamentCheckBox box = (OrnamentCheckBox) label.getParent().getComponent(0);
                            box.setSelected(!box.isSelected());
                        }
                    }
                });
                itemLabel.setPreferredSize(new Dimension(120, 28));
                itemLabel.setToolTipText(itemNode.getText());
                itemPanel.add(itemLabel, BorderLayout.CENTER);
                groupPanel.add(itemPanel);
                panelIndex++;
            }
            while (panelIndex++ < rows * cols - 1) {
                JPanel emptyPanel = new JPanel();
                emptyPanel.setOpaque(false);
                emptyPanel.setPreferredSize(new Dimension(148, 28));
                groupPanel.add(emptyPanel);
            }
        });
    }

    private void displayExplanatoryImageAndText(String description, String value) {
        displayContentPanel.removeAll();
        JLabel iconImageLabel = new JLabel(getItemIcon("ornaments/" + value.replaceAll("!", "") + ".png"));
        iconImageLabel.setPreferredSize(new Dimension(250, 180));
        displayContentPanel.add(iconImageLabel, BorderLayout.NORTH);
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setText(description);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setPreferredSize(new Dimension(250, 300));
        area.setRows(5);
        area.setColumns(20);
        area.setBorder(new EmptyBorder(5, 5, 5, 5));
        displayContentPanel.add(area, BorderLayout.CENTER);
        displayContentPanel.repaint();
        displayContentPanel.revalidate();
    }

    private ImageIcon getItemIcon(String iconName) {
        try {
            InputStream iconStream = getClass().getClassLoader()
                    .getResourceAsStream("images/" + iconName);
            return new ImageIcon(ImageIO.read(iconStream));

        } catch (IllegalArgumentException | IOException ex) {
            Logger.getLogger(OrnamentChooser.class
                    .getName()).log(Level.SEVERE, "Missing image: " + iconName, ex);
        }
        return null;

    }

    class OrnamentCheckBox extends JCheckBox {

        private final String value;

        public OrnamentCheckBox(String toolTipText, String value) {
            this.value = value;
            super.setToolTipText(toolTipText);
            super.setPreferredSize(new Dimension(28, 28));
            super.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        ornaments.add(value);
                    } else if (ornaments.contains(value)) {
                        ornaments.remove(value);
                    }
                    displayExplanatoryImageAndText(toolTipText, value);
                }
            });
        }

        public String getValue() {
            return isSelected() ? value : null;
        }
    }
}
