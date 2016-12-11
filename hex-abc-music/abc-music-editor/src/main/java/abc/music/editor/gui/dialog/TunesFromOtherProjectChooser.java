package abc.music.editor.gui.dialog;

import abc.music.core.domain.Person;
import abc.music.core.domain.PersonRole;
import abc.music.core.domain.Project;
import abc.music.core.domain.Tune;
import abc.music.editor.AbcMusicEditor;
import java.util.List;

/**
 * Created 2016-dec-11
 *
 * @author hl
 */
public class TunesFromOtherProjectChooser extends ProjectFileChooser {

    private final TuneSelectorPanel panel;
    private final AbcMusicEditor editor;

    public TunesFromOtherProjectChooser(AbcMusicEditor editor) {
        super();
        panel = new TuneSelectorPanel(editor, this);
        super.setAccessory(panel);
        this.editor = editor;
    }

    @Override
    public void approveSelection() {
        Project p = editor.getProject();
        for (Tune tune : panel.getTunes()) {
            List<PersonRole> importedCreators = tune.getCreators();
            importedCreators.stream().forEach((pr) -> {
                Person copiedPerson = copyPerson(pr.getPerson(), p);
                pr.setPerson(copiedPerson);
            });
            p.addTune(tune);
        }
        super.approveSelection();
    }

    private Person copyPerson(Person person, Project p) {
        for (Person old : p.getPersons()) {
            if (old.getFormalName().equals(person.getFormalName())) {
                return old;
            }
        }
        Person result = new Person(p);
        result.setEmail(person.getEmail());
        result.setFirstName(person.getFirstName());
        result.setLastName(person.getLastName());
        result.setHistory(person.getHistory());
        p.addPerson(result);
        return result;
    }
}
