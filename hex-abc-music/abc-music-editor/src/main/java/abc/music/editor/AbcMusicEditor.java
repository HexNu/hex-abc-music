package abc.music.editor;

import abc.music.editor.gui.AmeStatusBar;
import abc.music.editor.gui.AmeMenuBar;
import abc.music.core.domain.Project;
import abc.music.editor.action.OpenLatestProjectAction;
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
import abc.music.editor.action.SetEditingEnabledAction;
import abc.music.editor.gui.LatestTunesPane;
import abc.music.editor.gui.LinksPanel;
import abc.music.editor.gui.BooksPanel;
import abc.music.editor.gui.TuneHeadersPanel;
import abc.music.editor.gui.TuneSearchPanel;
import abc.music.editor.gui.VoicesPanel;
import javax.swing.JRootPane;

/**
 * Created 2016-nov-30
 *
 * @author hl
 */
public class AbcMusicEditor extends JFrame {

    private static final Dimension sidePanelDimension = new Dimension(275, 300);

    private final JPanel bottomPanel = new JPanel();
    private final JPanel centerPanel = new JPanel();
    private final JPanel editorPanel = new JPanel();
    private final JPanel leftPanel = new JPanel();
    private final JPanel linksPanel = new JPanel();
    private final JPanel booksPanel = new JPanel();
    private final JPanel recentTunesPanel = new JPanel();
    private final JPanel rightPanel = new JPanel();
    private final JPanel searchPanel = new JPanel();
    private final JPanel topPanel = new JPanel();
    private LinksPanel linkListPanel;
    private BooksPanel bookListPanel;
    private TuneHeadersPanel headersPanel;
    private VoicesPanel voicesPanel;
    private AmeMenuBar menuBar;
    private Project project;
    private AmeStatusBar statusPanel;
    private LatestTunesPane latestTunesPane;
    private TuneSearchPanel tuneSearchPanel;

    public AbcMusicEditor() {
        init();
    }

    private void init() {
        Dimension dimension = new Dimension(1800, 1000);
        setContentPane(new BackgroundImagePanel("Background.png"));
        setIconImages(new LogoImages().get());
        setTitle(AmeConstants.APP_TITLE);
        setSize(dimension);
        setPreferredSize(dimension);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setComponents();
        createMenu();
        new SetEditingEnabledAction(this, false).actionPerformed(null);
        pack();
    }

    @Override
    protected JRootPane createRootPane() {
        JRootPane customRootPane = new JRootPane();
//        ActionListener f1ActionListener = (ActionEvent e) -> {
//            new ShowHelpDialogAction(this).actionPerformed(e);
//        };
//        customRootPane.registerKeyboardAction(f1ActionListener,
//                KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        return customRootPane;
    }

    private void setComponents() {
        createCenterPanel();
        createLeftPanel();
        createRightPanel();
        setJMenuBar(menuBar);
    }

    private void createRightPanel() {
        rightPanel.setLayout(new GridLayout(3, 1, 0, 12));
        rightPanel.setBackground(AmeConstants.BACKGROUND_COLOR);

        linksPanel.setBorder(getTitleBorder("Resource Links"));
        linksPanel.setLayout(new BorderLayout());
        linksPanel.setPreferredSize(sidePanelDimension);
        linksPanel.setOpaque(false);
        linkListPanel = new LinksPanel(this);
        linksPanel.add(linkListPanel, BorderLayout.CENTER);
        rightPanel.add(linksPanel);

        booksPanel.setBorder(getTitleBorder("Books"));
        booksPanel.setLayout(new BorderLayout());
        booksPanel.setPreferredSize(sidePanelDimension);
        booksPanel.setOpaque(false);
        bookListPanel = new BooksPanel(this);
        booksPanel.add(bookListPanel, BorderLayout.CENTER);
        rightPanel.add(booksPanel);

        add(rightPanel, BorderLayout.EAST);
    }

    private void createLeftPanel() {
        leftPanel.setLayout(new GridLayout(2, 1, 6, 12));
        leftPanel.setBackground(AmeConstants.BACKGROUND_COLOR);
        recentTunesPanel.setBorder(getTitleBorder("Recent Tunes"));
        recentTunesPanel.setLayout(new BorderLayout());
        recentTunesPanel.setPreferredSize(sidePanelDimension);
        recentTunesPanel.setOpaque(false);
        latestTunesPane = new LatestTunesPane(this);
        recentTunesPanel.add(latestTunesPane, BorderLayout.CENTER);
        searchPanel.setBorder(getTitleBorder("Search Tunes"));
        searchPanel.setLayout(new BorderLayout());
        searchPanel.setPreferredSize(sidePanelDimension);
        searchPanel.setOpaque(false);
        tuneSearchPanel = new TuneSearchPanel(this);
        searchPanel.add(tuneSearchPanel, BorderLayout.CENTER);

        leftPanel.add(searchPanel);
        leftPanel.add(recentTunesPanel);
        add(leftPanel, BorderLayout.WEST);
    }

    private void createCenterPanel() {
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(AmeConstants.BACKGROUND_COLOR);
        editorPanel.setOpaque(false);
        editorPanel.setLayout(new BorderLayout(0, 12));
        centerPanel.add(editorPanel, BorderLayout.CENTER);
        JLabel editorLabel = new JLabel("Music Editor");
        editorLabel.setBorder(new EmptyBorder(0, 18, 0, 0));
        editorLabel.setFont(AmeConstants.MEDIUM_TITLE_FONT);
        editorLabel.setForeground(AmeConstants.TITLE_COLOR);
        editorPanel.add(editorLabel, BorderLayout.NORTH);
        JPanel editorContentPanel = new JPanel(new BorderLayout(0, 12));
        editorContentPanel.setOpaque(false);
        headersPanel = new TuneHeadersPanel(this);
        editorContentPanel.add(headersPanel, BorderLayout.NORTH);
        voicesPanel = new VoicesPanel(this);
        editorContentPanel.add(voicesPanel, BorderLayout.CENTER);
        editorPanel.add(editorContentPanel, BorderLayout.CENTER);

        bottomPanel.setOpaque(false);
        centerPanel.add(bottomPanel, BorderLayout.SOUTH);
        topPanel.setOpaque(false);
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createTitledBorder(null, AmeConstants.APP_TITLE, TitledBorder.CENTER, TitledBorder.TOP, AmeConstants.BIG_TITLE_FONT, AmeConstants.TITLE_COLOR));
        JLabel subHeaderLabel = new JLabel("Edit  and  Organize  Your  Abc  Music  Tunes");
        subHeaderLabel.setFont(AmeConstants.SMALL_TITLE_FONT);
        subHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(subHeaderLabel, BorderLayout.CENTER);
        centerPanel.add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        statusPanel = new AmeStatusBar();
        centerPanel.add(statusPanel, BorderLayout.SOUTH);
    }

    public AmeStatusBar getStatusBar() {
        return statusPanel;
    }

    public LatestTunesPane getLatestTunesPane() {
        return latestTunesPane;
    }

    public BooksPanel getBooksPanel() {
        return bookListPanel;
    }

    private void createMenu() {
        menuBar = new AmeMenuBar(this);
        setJMenuBar(menuBar);
    }

    public TuneHeadersPanel getTuneEditor() {
        return headersPanel;
    }

    public VoicesPanel getVoicesPanel() {
        return voicesPanel;
    }

    public Project getProject() {
        return project;
    }

    public void updateMenuBar() {
        menuBar.updateMenu();
    }

    public void clearProject() {
        this.project = null;
        setTitle(AmeConstants.APP_TITLE);
        updateMenuBar();
        tuneSearchPanel.checkSearchEnabled();
        bookListPanel.updateBooks();
        new SetEditingEnabledAction(this, false).actionPerformed(null);
    }

    public void setProject(Project project) {
        this.project = project;
        if (project != null && project.getName() != null) {
            setTitle(AmeConstants.APP_TITLE + " - " + project.getName());
        }
        updateMenuBar();
        tuneSearchPanel.checkSearchEnabled();
        bookListPanel.updateBooks();
        new SetEditingEnabledAction(this, false).actionPerformed(null);
        getTuneEditor().refresh();
    }

    public Border getTitleBorder(String title) {
        return BorderFactory.createTitledBorder(null, title, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, AmeConstants.MEDIUM_TITLE_FONT, AmeConstants.TITLE_COLOR);
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
                Logger.getLogger(AbcMusicEditor.class.getName()).log(Level.SEVERE, "Background Image {0} could not be found", backgroundImage);
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
            AbcMusicEditor abcMusicEditor = new AbcMusicEditor();
            abcMusicEditor.setVisible(true);
            OpenLatestProjectAction action = new OpenLatestProjectAction(abcMusicEditor);
            action.actionPerformed(null);
            abcMusicEditor.setProject(action.get());
        });
    }
}
