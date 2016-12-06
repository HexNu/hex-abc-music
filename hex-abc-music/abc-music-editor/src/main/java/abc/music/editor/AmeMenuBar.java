package abc.music.editor;

import abc.music.core.domain.Person;
import abc.music.core.domain.Project;
import abc.music.editor.action.BackupProjectAction;
import abc.music.editor.action.SaveProjectAction;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import abc.music.editor.action.CloseProjectAction;
import abc.music.editor.action.CreatePersonAction;
import abc.music.editor.action.CreateProjectAction;
import abc.music.editor.action.EditPersonAction;
import abc.music.editor.action.ExitAction;
import abc.music.editor.action.OpenLatestProjectAction;
import abc.music.editor.action.OpenProjectAction;
import abc.music.editor.action.ShowAboutAction;
import abc.music.editor.action.ShowSettingsAction;
import abc.music.editor.help.HelpDialog;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
public class AmeMenuBar extends JMenuBar {

    private static final Font MENU_FONT = new Font("Ringbearer", 0, 13);
    private static final Color BACKGROUND = new Color(255, 205, 0, 105);

    private AmeMenu fileMenu = new AmeMenu();
    private AmeMenu editMenu = new AmeMenu();
    private AmeMenu projectMenu = new AmeMenu();
    private AmeMenu personsMenu;
    private AmeMenu helpMenu = new AmeMenu();
    private AmeMenuItem openMenuItem = new AmeMenuItem();
    private AmeMenuItem openLatestMenuItem = new AmeMenuItem();
    private AmeMenuItem newProjectMenuItem = new AmeMenuItem();
    private AmeMenuItem saveMenuItem = new AmeMenuItem();
    private AmeMenuItem backupMenuItem = new AmeMenuItem();
    private AmeMenuItem closeProjectMenuItem = new AmeMenuItem();
    private AmeMenuItem exitMenuItem = new AmeMenuItem();
    private AmeMenuItem contentsMenuItem = new AmeMenuItem();
    private AmeMenuItem aboutMenuItem = new AmeMenuItem();

    private final AbcMusicEditor parent;

    public AmeMenuBar(AbcMusicEditor parent) {
        super();
        super.setBackground(BACKGROUND);
        init();
        this.parent = parent;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(BACKGROUND);
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

    }

    private void init() {
        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        openMenuItem.addActionListener((ActionEvent e) -> {
            OpenProjectAction action = new OpenProjectAction(parent);
            action.actionPerformed(e);
            if (action.get() != null) {
                parent.clearProject();
                parent.setProject(action.get());
            }
        });
        fileMenu.add(openMenuItem);

        openLatestMenuItem.setMnemonic('l');
        openLatestMenuItem.setText("Open Latest Project");
        openLatestMenuItem.setEnabled(OpenLatestProjectAction.isEnabled());
        openLatestMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
        openLatestMenuItem.addActionListener((ActionEvent e) -> {
            OpenLatestProjectAction action = new OpenLatestProjectAction(parent);
            action.actionPerformed(e);
            if (action.get() != null) {
                parent.clearProject();
                parent.setProject(action.get());
            }
        });
        fileMenu.add(openLatestMenuItem);

        newProjectMenuItem.setMnemonic('e');
        newProjectMenuItem.setText("Create Project");
        newProjectMenuItem.addActionListener((ActionEvent e) -> {
            CreateProjectAction action = new CreateProjectAction(parent);
            action.actionPerformed(e);
            if (action.get() != null) {
                parent.setProject(action.get());
            }
        });
        fileMenu.add(newProjectMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveMenuItem.addActionListener((ActionEvent e) -> {
            new SaveProjectAction(parent).actionPerformed(e);
        });
        saveMenuItem.setEnabled(false);
        fileMenu.add(saveMenuItem);

        backupMenuItem.setMnemonic('b');
        backupMenuItem.setText("Save a Backup");
        backupMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
        backupMenuItem.addActionListener((ActionEvent e) -> {
            new BackupProjectAction(parent).actionPerformed(e);
        });
        backupMenuItem.setEnabled(false);
        fileMenu.add(backupMenuItem);

        closeProjectMenuItem.setText("Close Project");
        closeProjectMenuItem.setMnemonic('c');
        closeProjectMenuItem.addActionListener((ActionEvent e) -> {
            new CloseProjectAction(parent).actionPerformed(e);
        });
        closeProjectMenuItem.setEnabled(false);
        fileMenu.add(closeProjectMenuItem);

        exitMenuItem.setMnemonic('q');
        exitMenuItem.setText("Quit");
        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        exitMenuItem.addActionListener((ActionEvent evt) -> {
            new ExitAction(parent).actionPerformed(evt);
        });
        fileMenu.add(exitMenuItem);
        add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");
        editMenu.setEnabled(true);

        editMenu.addSeparator();
        AmeMenuItem settingsItem = new AmeMenuItem("Settings");
        settingsItem.setMnemonic('s');
        settingsItem.addActionListener((ActionEvent e) -> {
            new ShowSettingsAction(parent).actionPerformed(e);
        });
        editMenu.add(settingsItem);

        add(editMenu);

        projectMenu.setText("Project");
        projectMenu.setMnemonic('p');
        projectMenu.setEnabled(false);
        add(projectMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        contentsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        contentsMenuItem.addActionListener((ActionEvent e) -> {
            new HelpDialog(parent).setVisible(true);
        });
        
        helpMenu.add(contentsMenuItem);
        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener((ActionEvent e) -> {
            new ShowAboutAction(parent).actionPerformed(e);
        });
        helpMenu.add(aboutMenuItem);

        super.add(helpMenu);

    }

    public void updateMenu() {
        boolean enable = parent.getProject() != null;
        saveMenuItem.setEnabled(enable);
        openLatestMenuItem.setEnabled(OpenLatestProjectAction.isEnabled());
        backupMenuItem.setEnabled(enable);
        closeProjectMenuItem.setEnabled(enable);
        projectMenu.setEnabled(enable);
        projectMenu.removeAll();
        if (enable) {
            populateProjectMenu();
        }
    }

    private void populateProjectMenu() {
        Project p = parent.getProject();
        personsMenu = new AmeMenu("Persons");
        projectMenu.add(personsMenu);
        AmeMenuItem addPersonItem = new AmeMenuItem("Add Person");
        addPersonItem.addActionListener((ActionEvent e) -> {
            new CreatePersonAction(parent).actionPerformed(e);
        });
        personsMenu.add(addPersonItem);
        personsMenu.addSeparator();
        p.getPersons().stream().forEach(this::addPersonItem);
    }

    private void addPersonItem(Person person) {
        AmeMenuItem personItem = new AmeMenuItem(person.getName());
        personItem.addActionListener((ActionEvent e) -> {
            new EditPersonAction(parent, person).actionPerformed(e);
        });
        personsMenu.add(personItem);
    }

    public static class AmeMenu extends JMenu {

        public AmeMenu() {
            this(null);
        }

        public AmeMenu(String text) {
            super(text != null ? text.toLowerCase() : null);
            super.setFont(MENU_FONT);
        }

        @Override
        public void setText(String text) {
            super.setText(text != null ? text.toLowerCase() : null);
        }
    }

    public static class AmeMenuItem extends JMenuItem {

        public AmeMenuItem() {
            this(null, null);
        }

        public AmeMenuItem(String text) {
            this(text, null);
        }

        public AmeMenuItem(Icon icon) {
            this(null, icon);
        }

        public AmeMenuItem(String text, Icon icon) {
            super(text != null ? text.toLowerCase() : null, icon);
            super.setFont(MENU_FONT);
        }

        @Override
        public void setText(String text) {
            super.setText(text != null ? text.toLowerCase() : null);
        }
    }
}
