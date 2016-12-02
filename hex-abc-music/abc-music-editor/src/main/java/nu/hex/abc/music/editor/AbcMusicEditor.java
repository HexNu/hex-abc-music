package nu.hex.abc.music.editor;

import abc.music.core.domain.Project;
import nu.hex.abc.music.editor.z.OldAbcMusicEditor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import nu.hex.abc.music.editor.components.TuneHeadersPanel;
import nu.hex.abc.music.editor.components.VoicesPanel;

/**
 * Created 2016-nov-30
 *
 * @author hl
 */
public class AbcMusicEditor extends JFrame {

    public static final String APP_TITLE = "Hex ABC Music Editor";
    private static final Dimension sidePanelDimension = new Dimension(275, 300);

    private final JPanel bottomPanel = new JPanel();
    private final JPanel centerPanel = new JPanel();
    private final JPanel editorPanel = new JPanel();
    private final JPanel leftPanel = new JPanel();
    private final JPanel linksPanel = new JPanel();
    private final JPanel recentTunesPanel = new JPanel();
    private final JPanel rightPanel = new JPanel();
    private final JPanel searchPanel = new JPanel();
    private final JPanel topPanel = new JPanel();
    private TuneHeadersPanel headersPanel;
    private VoicesPanel voicesPanel;
    private AmeMenuBar menuBar;
    private Project project;

    public AbcMusicEditor() {
        init();
    }

    private void init() {
        Dimension dimension = new Dimension(1800, 1000);
        setContentPane(new BackgroundImagePanel("Background.png"));
        setIconImages(new LogoImages().get());
        setTitle(APP_TITLE);
        setSize(dimension);
        setPreferredSize(dimension);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setComponents();
        createMenu();
        pack();
    }

    private void setComponents() {
        createCenterPanel();
        createLeftPanel();
        createRightPanel();
        setJMenuBar(menuBar);
    }

    private void createRightPanel() {
        rightPanel.setLayout(new GridLayout(3, 1, 0, 12));
        rightPanel.setBackground(Constants.BACKGROUND_COLOR);
        linksPanel.setBorder(getTitleBorder("Resource Links"));
        linksPanel.setPreferredSize(sidePanelDimension);
        linksPanel.setOpaque(false);
        rightPanel.add(linksPanel);
        add(rightPanel, BorderLayout.EAST);
    }

    private void createLeftPanel() {
        leftPanel.setLayout(new GridLayout(2, 1, 6, 12));
        leftPanel.setBackground(Constants.BACKGROUND_COLOR);
        searchPanel.setBorder(getTitleBorder("Search Tunes"));
        searchPanel.setPreferredSize(sidePanelDimension);
        searchPanel.setOpaque(false);
        leftPanel.add(searchPanel);
        recentTunesPanel.setBorder(getTitleBorder("Recent Tunes"));
        recentTunesPanel.setPreferredSize(sidePanelDimension);
        recentTunesPanel.setOpaque(false);
        leftPanel.add(recentTunesPanel);
        add(leftPanel, BorderLayout.WEST);
    }

    private void createCenterPanel() {
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Constants.BACKGROUND_COLOR);
        editorPanel.setOpaque(false);
        editorPanel.setLayout(new BorderLayout(0, 12));
        centerPanel.add(editorPanel, BorderLayout.CENTER);
        JLabel editorLabel = new JLabel("Music Editor");
        editorLabel.setBorder(new EmptyBorder(0, 18, 0, 0));
        editorLabel.setFont(Constants.MEDIUM_TITLE_FONT);
        editorLabel.setForeground(Constants.TITLE_COLOR);
        editorPanel.add(editorLabel, BorderLayout.NORTH);
        JPanel editorContentPanel = new JPanel(new BorderLayout(0, 12));
        editorContentPanel.setOpaque(false);
        headersPanel = new TuneHeadersPanel(this, project);
        editorContentPanel.add(headersPanel, BorderLayout.NORTH);
        voicesPanel = new VoicesPanel(this, project);
        editorContentPanel.add(voicesPanel, BorderLayout.CENTER);
        editorPanel.add(editorContentPanel, BorderLayout.CENTER);

        bottomPanel.setOpaque(false);
        centerPanel.add(bottomPanel, BorderLayout.SOUTH);
        topPanel.setOpaque(false);
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createTitledBorder(null, APP_TITLE, TitledBorder.CENTER, TitledBorder.TOP, Constants.BIG_TITLE_FONT, Constants.TITLE_COLOR));
        JLabel subHeaderLabel = new JLabel("Edit  and  organize  your  abc  music  tunes");
        subHeaderLabel.setFont(Constants.SMALL_TITLE_FONT);
        subHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(subHeaderLabel, BorderLayout.CENTER);
        centerPanel.add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void createMenu() {
        menuBar = new AmeMenuBar(this);
        setJMenuBar(menuBar);
    }

    public TuneHeadersPanel getHeadersPanel() {
        return headersPanel;
    }

    public VoicesPanel getVoicesPanel() {
        return voicesPanel;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
        setTitle(APP_TITLE + " - " + project.getName());
    }

    public Border getTitleBorder(String title) {
        return BorderFactory.createTitledBorder(null, title, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, Constants.MEDIUM_TITLE_FONT, Constants.TITLE_COLOR);
    }

    private class LogoImages {

        List<java.awt.Image> get() {
            List<java.awt.Image> result = new ArrayList<>();
            result.add(getResourceAsImage(32));
            result.add(getResourceAsImage(64));
            result.add(getResourceAsImage(128));
            result.add(getResourceAsImage(256));
            return result;
        }

        private java.awt.Image getResourceAsImage(int size) {
            InputStream imageStream = getClass().getClassLoader()
                    .getResourceAsStream("images/Logo" + size + ".png");
            try {
                return ImageIO.read(imageStream);
            } catch (IOException e) {
                return null;
            }
        }
    }

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

        private Image getResourceAsImage() {
            InputStream imageStream = getClass().getClassLoader().getResourceAsStream("images/" + backgroundImage);
            try {
                return ImageIO.read(imageStream);
            } catch (IOException e) {
                Logger.getLogger(OldAbcMusicEditor.class.getName()).log(Level.SEVERE, "Background Image {0} could not be found", backgroundImage);
                return null;
            }
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(AbcMusicEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(() -> {
            new AbcMusicEditor().setVisible(true);
        });
    }
}
