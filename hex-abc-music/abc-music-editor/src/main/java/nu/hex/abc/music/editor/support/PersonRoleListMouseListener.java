package nu.hex.abc.music.editor.support;

import abc.music.core.domain.Person;
import abc.music.core.domain.PersonRole;
import abc.music.core.domain.Tune;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import nu.hex.abc.music.editor.AbcMusicEditor;
import nu.hex.abc.music.editor.action.AddPersonRoleAction;
import nu.hex.abc.music.editor.action.RemovePersonRoleAction;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
public class PersonRoleListMouseListener implements MouseListener {

    private final AbcMusicEditor parent;
    private final JList list;
    private final JPopupMenu menu;
    private final Person.Role role;
    private int selectedIndex;

    public PersonRoleListMouseListener(AbcMusicEditor parent, JList list, Person.Role role, Tune tune) {
        this.parent = parent;
        this.list = list;
        this.role = role;
        this.menu = new JPopupMenu();

        JMenuItem add = new JMenuItem("Select " + role.toString() + " to Add");
        add.addActionListener((ActionEvent e) -> {
            new AddPersonRoleAction(parent, role).actionPerformed(e);
        });

        JMenuItem remove = new JMenuItem("Remove " + role.toString());
        remove.addActionListener((ActionEvent e) -> {
            new RemovePersonRoleAction(parent, (PersonRole) list.getModel().getElementAt(selectedIndex)).actionPerformed(e);
            list.requestFocus();
        });
        menu.add(add);
        menu.add(remove);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        list.setSelectedIndex(list.locationToIndex(e.getPoint()));
        selectedIndex = list.locationToIndex(e.getPoint());
        System.out.println(selectedIndex);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
