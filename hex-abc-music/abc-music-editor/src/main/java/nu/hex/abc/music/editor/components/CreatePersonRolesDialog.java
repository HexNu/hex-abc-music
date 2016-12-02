package nu.hex.abc.music.editor.components;

import abc.music.core.domain.Person;
import abc.music.core.domain.PersonRole;
import abc.music.core.domain.Project;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import nu.hex.abc.music.editor.AbcMusicEditor;
import nu.hex.abc.music.editor.action.CreatePersonAction;

/**
 *
 * @author hl
 */
public class CreatePersonRolesDialog extends AmeDialog<List<PersonRole>> {

    private final Person.Role role;
    private final List<JCheckBox> roleCheckBoxes = new ArrayList<>();

    /**
     * Creates new form CreatePersonRoleDialog
     *
     * @param parent
     * @param role
     */
    public CreatePersonRolesDialog(AbcMusicEditor parent, Person.Role role) {
        super(parent, "Create Person Role Connection");
        this.role = role;
        setFields();
    }

    @Override
    protected void init() {
        initComponents();
    }

    private void setFields() {
        updatePersonsComboBox();
        Arrays.asList(Person.Role.values()).stream().forEach(this::addCheckBox);
    }

    private void updatePersonsComboBox() {
        Project project = application.getProject();
        personComboBox.setModel(new DefaultComboBoxModel(project.getPersons().toArray()));
    }

    private void addCheckBox(Person.Role pr) {
        roleCheckBoxes.clear();
        JCheckBox result = new JCheckBox(pr.getName());
        result.setName(pr.name().toLowerCase() + "CheckBox");
        if (pr.equals(role)) {
            result.setSelected(true);
        }
        roleCheckBoxes.add(result);
        rolesPanel.add(result);
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
        personComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        rolesPanel = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        addPersonButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Person:");

        personComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Role:");

        rolesPanel.setLayout(new javax.swing.BoxLayout(rolesPanel, javax.swing.BoxLayout.PAGE_AXIS));

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        addPersonButton.setText("Add person");
        addPersonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPersonButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 52, Short.MAX_VALUE)
                        .addComponent(addPersonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(personComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rolesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(personComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(rolesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton)
                    .addComponent(addPersonButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        ok();
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        cancel();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void addPersonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPersonButtonActionPerformed
        addPerson(evt);
    }//GEN-LAST:event_addPersonButtonActionPerformed

    @Override
    protected void accept() {
        set(new ArrayList<>());
        Person p = (Person) personComboBox.getSelectedItem();
        for (Component c : rolesPanel.getComponents()) {
            if (c instanceof JCheckBox) {
                JCheckBox box = (JCheckBox) c;
                if (box.isSelected()) {
                    String roleString = box.getName().replace("CheckBox", "");
                    Person.Role r = Person.Role.find(roleString);
                    PersonRole pr = new PersonRole(r);
                    pr.setPerson(p);
                    get().add(pr);
                }
            }
        }
    }

    private void addPerson(ActionEvent evt) {
        CreatePersonAction action = new CreatePersonAction(application);
        action.actionPerformed(evt);
        Person person = action.get();
        updatePersonsComboBox();
        int index = -1;
        for (Person p : application.getProject().getPersons()) {
            index++;
            if (p.equals(person)) {
                break;
            }
        }
        if (index >= 0) {
            personComboBox.setSelectedIndex(index);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPersonButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox<String> personComboBox;
    private javax.swing.JPanel rolesPanel;
    // End of variables declaration//GEN-END:variables
}
