package nu.hex.abc.music.editor;

import nu.hex.abc.music.editor.action.SaveProjectAction;
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
import nu.hex.abc.music.editor.action.CreateProjectAction;
import nu.hex.abc.music.editor.action.ExitAction;
import nu.hex.abc.music.editor.action.OpenProjectAction;

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
    private AmeMenu helpMenu = new AmeMenu();
    private AmeMenuItem openMenuItem = new AmeMenuItem();
    private AmeMenuItem newProjectMenuItem = new AmeMenuItem();
    private AmeMenuItem saveMenuItem = new AmeMenuItem();
    private AmeMenuItem saveAsMenuItem = new AmeMenuItem();
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
        fileMenu.setText("file");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("open");
        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        openMenuItem.addActionListener((ActionEvent e) -> {
            OpenProjectAction action = new OpenProjectAction(parent);
            action.actionPerformed(e);
            if (action.get() != null) {
                parent.setProject(action.get());
            }
        });
        fileMenu.add(openMenuItem);

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
        saveMenuItem.setText("save");
        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        saveMenuItem.addActionListener((ActionEvent e) -> {
            new SaveProjectAction(parent).actionPerformed(e);
        });
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("save as ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('q');
        exitMenuItem.setText("quit");
        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        exitMenuItem.addActionListener((ActionEvent evt) -> {
            new ExitAction(parent).actionPerformed(evt);
        });
        fileMenu.add(exitMenuItem);

        add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("edit");

        add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("about");
        helpMenu.add(aboutMenuItem);

        super.add(helpMenu);
    }

    public static class AmeMenu extends JMenu {

        public AmeMenu() {
            this(null);
        }

        public AmeMenu(String text) {
            super(text);
            super.setFont(MENU_FONT);
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
            super(text, icon);
            super.setFont(MENU_FONT);
        }
    }
}
