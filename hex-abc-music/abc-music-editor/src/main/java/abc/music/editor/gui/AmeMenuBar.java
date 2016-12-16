package abc.music.editor.gui;

import abc.music.core.domain.Book;
import abc.music.core.domain.Collection;
import abc.music.core.domain.Person;
import abc.music.core.domain.Project;
import abc.music.editor.AbcMusicEditor;
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
import abc.music.editor.action.CreateBookAction;
import abc.music.editor.action.CreatePersonAction;
import abc.music.editor.action.CreateProjectAction;
import abc.music.editor.action.EditBookAction;
import abc.music.editor.action.EditFormatTemplateAction;
import abc.music.editor.action.EditPersonAction;
import abc.music.editor.action.EditProjectExportSettingsAction;
import abc.music.editor.action.ExitAction;
import abc.music.editor.action.ExportCollectionAction;
import abc.music.editor.action.ImportFromOtherProjectAction;
import abc.music.editor.action.OpenLatestProjectAction;
import abc.music.editor.action.OpenProjectAction;
import abc.music.editor.action.PrintAction;
import abc.music.editor.action.ShowAboutAction;
import abc.music.editor.action.ShowSettingsAction;
import abc.music.editor.help.HelpDialog;
import nu.hex.mediatype.CommonMediaType;

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
    private AmeMenu exportMenu;
    private AmeMenu importMenu;
    private AmeMenu printMenu;
    private AmeMenu personsMenu;
    private AmeMenu booksMenu;
    private AmeMenu formatTemplatesMenu;
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

    private final AbcMusicEditor editor;

    public AmeMenuBar(AbcMusicEditor parent) {
        super();
        super.setBackground(BACKGROUND);
        init();
        this.editor = parent;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(BACKGROUND);
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

    }

    private void init() {
        fileMenu.setMnemonic('i');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        openMenuItem.addActionListener((ActionEvent e) -> {
            OpenProjectAction action = new OpenProjectAction(editor);
            action.actionPerformed(e);
            if (action.get() != null) {
                editor.clearProject();
                editor.setProject(action.get());
            }
        });
        fileMenu.add(openMenuItem);

        openLatestMenuItem.setMnemonic('l');
        openLatestMenuItem.setText("Open Latest Project");
        openLatestMenuItem.setEnabled(OpenLatestProjectAction.isEnabled());
        openLatestMenuItem.addActionListener((ActionEvent e) -> {
            OpenLatestProjectAction action = new OpenLatestProjectAction(editor);
            action.actionPerformed(e);
            if (action.get() != null) {
                editor.clearProject();
                editor.setProject(action.get());
            }
        });
        fileMenu.add(openLatestMenuItem);

        newProjectMenuItem.setMnemonic('e');
        newProjectMenuItem.setText("Create Project");
        newProjectMenuItem.addActionListener((ActionEvent e) -> {
            CreateProjectAction action = new CreateProjectAction(editor);
            action.actionPerformed(e);
            if (action.get() != null) {
                editor.setProject(action.get());
            }
        });
        fileMenu.add(newProjectMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveMenuItem.addActionListener((ActionEvent e) -> {
            new SaveProjectAction(editor).actionPerformed(e);
        });
        saveMenuItem.setEnabled(false);
        fileMenu.add(saveMenuItem);

        backupMenuItem.setMnemonic('b');
        backupMenuItem.setText("Save a Backup");
        backupMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
        backupMenuItem.addActionListener((ActionEvent e) -> {
            new BackupProjectAction(editor).actionPerformed(e);
        });
        backupMenuItem.setEnabled(false);
        fileMenu.add(backupMenuItem);

        closeProjectMenuItem.setText("Close Project");
        closeProjectMenuItem.setMnemonic('c');
        closeProjectMenuItem.addActionListener((ActionEvent e) -> {
            new CloseProjectAction(editor).actionPerformed(e);
        });
        closeProjectMenuItem.setEnabled(false);
        fileMenu.add(closeProjectMenuItem);
        fileMenu.addSeparator();
        exportMenu = new AmeMenu("Export");
        exportMenu.setEnabled(false);
        fileMenu.add(exportMenu);
        importMenu = new AmeMenu("Import");
        importMenu.setEnabled(false);
        fileMenu.add(importMenu);
        fileMenu.addSeparator();

        printMenu = new AmeMenu("Print");
        printMenu.setEnabled(false);
        fileMenu.add(printMenu);

        fileMenu.addSeparator();

        exitMenuItem.setMnemonic('q');
        exitMenuItem.setText("Quit");
        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        exitMenuItem.addActionListener((ActionEvent evt) -> {
            new ExitAction(editor).actionPerformed(evt);
        });
        fileMenu.add(exitMenuItem);
        add(fileMenu);

        editMenu.setMnemonic('t');
        editMenu.setText("Edit");
        editMenu.setEnabled(true);

        editMenu.addSeparator();
        AmeMenuItem settingsItem = new AmeMenuItem("Settings");
        settingsItem.setMnemonic('s');
        settingsItem.addActionListener((ActionEvent e) -> {
            new ShowSettingsAction(editor).actionPerformed(e);
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
            new HelpDialog(editor).setVisible(true);
        });

        helpMenu.add(contentsMenuItem);
        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener((ActionEvent e) -> {
            new ShowAboutAction(editor).actionPerformed(e);
        });
        helpMenu.add(aboutMenuItem);

        super.add(helpMenu);

    }

    public void updateMenu() {
        boolean enable = editor.getProject() != null;
        saveMenuItem.setEnabled(enable);
        openLatestMenuItem.setEnabled(OpenLatestProjectAction.isEnabled());
        backupMenuItem.setEnabled(enable);
        closeProjectMenuItem.setEnabled(enable);
        exportMenu.setEnabled(enable);
        importMenu.setEnabled(enable);
        importMenu.removeAll();
        exportMenu.removeAll();
        if (enable) {
            populateImportExportMenu();
            populatePrintMenu();
        }
        projectMenu.setEnabled(enable);
        projectMenu.removeAll();
        if (enable) {
            populateProjectMenu();
        }
        printMenu.setEnabled(enable);
    }

    private void populateImportExportMenu() {
        Project p = editor.getProject();
        AmeMenu exportProjectMenu = new AmeMenu("Project");
        exportProjectMenu.add(createExportItem(p, CommonMediaType.TEXT_VND_ABC));
        exportProjectMenu.add(createExportItem(p, CommonMediaType.APPLICATION_POSTSCRIPT));
        exportProjectMenu.add(createExportItem(p, CommonMediaType.APPLICATION_PDF));
        exportMenu.add(exportProjectMenu);
        exportMenu.addSeparator();
        p.getBooks().stream().forEach((book) -> {
            AmeMenu bookMenu = new AmeMenu(book.getName());
            bookMenu.add(createExportItem(book, CommonMediaType.TEXT_VND_ABC));
            bookMenu.add(createExportItem(book, CommonMediaType.APPLICATION_POSTSCRIPT));
            bookMenu.add(createExportItem(book, CommonMediaType.APPLICATION_PDF));
            exportMenu.add(bookMenu);
        });
        AmeMenuItem importTunesFromProjectItem = new AmeMenuItem("Import tunes from other project...");
        importTunesFromProjectItem.addActionListener((ActionEvent e) -> {
            new ImportFromOtherProjectAction(editor).actionPerformed(e);
        });
        importMenu.add(importTunesFromProjectItem);
    }

    private void populatePrintMenu() {
        Project p = editor.getProject();
        printMenu.add(createPrintItem(p));
        AmeMenu printBooksMenu = new AmeMenu("Books");
        p.getBooks().stream().forEach((book) -> {
            printBooksMenu.add(createPrintItem(book));
        });
        printMenu.add(printBooksMenu);
    }

    private AmeMenuItem createExportItem(Collection collection, String mediaType) {
        String label;
        switch (mediaType) {
            case CommonMediaType.TEXT_VND_ABC:
                label = "ABC";
                break;
            case CommonMediaType.APPLICATION_POSTSCRIPT:
                label = "PostScript";
                break;
            case CommonMediaType.APPLICATION_PDF:
                label = "PDF";
                break;
            default:
                label = "TXT";
                break;
        }
        AmeMenuItem exportListItem = new AmeMenuItem("As " + label);
        exportListItem.addActionListener((ActionEvent e) -> {
            ExportCollectionAction action = new ExportCollectionAction(editor, collection, mediaType);
            action.actionPerformed(e);
        });
        return exportListItem;
    }

    private AmeMenuItem createPrintItem(Collection collection) {
        AmeMenuItem printItem = new AmeMenuItem(collection.getName());
        printItem.addActionListener((ActionEvent e) -> {
            PrintAction action = new PrintAction(editor, collection);
            action.actionPerformed(e);
        });
        return printItem;
    }

    private void populateProjectMenu() {
        Project p = editor.getProject();
        AmeMenuItem editProjectItem = new AmeMenuItem(p.getName());
        editProjectItem.addActionListener((ActionEvent e) -> {
            new EditProjectExportSettingsAction(editor).actionPerformed(e);
        });
        projectMenu.add(editProjectItem);
        projectMenu.addSeparator();

        personsMenu = new AmeMenu("Persons");
        projectMenu.add(personsMenu);
        AmeMenuItem addPersonItem = new AmeMenuItem("Add Person");
        addPersonItem.addActionListener((ActionEvent e) -> {
            new CreatePersonAction(editor).actionPerformed(e);
        });
        personsMenu.add(addPersonItem);
        personsMenu.addSeparator();
        p.getPersons().stream().forEach(this::addPersonItem);

        booksMenu = new AmeMenu("Books");
        projectMenu.add(booksMenu);
        AmeMenuItem addBookItem = new AmeMenuItem("Add Book");
        addBookItem.addActionListener((ActionEvent e) -> {
            new CreateBookAction(editor).actionPerformed(e);
        });
        booksMenu.add(addBookItem);
        booksMenu.addSeparator();
        p.getBooks().stream().forEach(this::addBookItem);
        projectMenu.addSeparator();
        formatTemplatesMenu = new AmeMenu("Format Templates");
        AmeMenuItem newFormatTemplateItem = new AmeMenuItem("New Format Template");
        newFormatTemplateItem.addActionListener((ActionEvent e) -> {
            new EditFormatTemplateAction(editor).actionPerformed(e);
        });
        formatTemplatesMenu.add(newFormatTemplateItem);
        formatTemplatesMenu.addSeparator();
        p.getFormatTemplates().keySet().stream().map((key) -> {
            AmeMenuItem templateItem = new AmeMenuItem(key);
            templateItem.addActionListener((ActionEvent e) -> {
                new EditFormatTemplateAction(editor, p.getFormatTemplate(key)).actionPerformed(e);
            });
            return templateItem;
        }).forEach((templateItem) -> {
            formatTemplatesMenu.add(templateItem);
        });
        projectMenu.add(formatTemplatesMenu);
    }

    private void addPersonItem(Person person) {
        AmeMenuItem personItem = new AmeMenuItem(person.getName());
        personItem.addActionListener((ActionEvent e) -> {
            new EditPersonAction(editor, person).actionPerformed(e);
        });
        personsMenu.add(personItem);
    }

    private void addBookItem(Book book) {
        AmeMenuItem bookItem = new AmeMenuItem(book.getName());
        bookItem.addActionListener((ActionEvent e) -> {
            new EditBookAction(editor, book).actionPerformed(e);
        });
        booksMenu.add(bookItem);
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
