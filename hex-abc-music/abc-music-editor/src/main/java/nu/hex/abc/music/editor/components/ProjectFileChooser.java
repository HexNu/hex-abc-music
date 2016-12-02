package nu.hex.abc.music.editor.components;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import nu.hex.abc.music.service.Service;

/**
 * Created 2016-nov-14
 *
 * @author hl
 */
public class ProjectFileChooser extends JFileChooser {

    public ProjectFileChooser() {
        super(Service.getSourceDirectory());
        super.setDialogTitle("Select Project");
        super.setAcceptAllFileFilterUsed(false);
        super.setFileFilter(new FileNameExtensionFilter("Project Files", "hmx"));
    }
}
