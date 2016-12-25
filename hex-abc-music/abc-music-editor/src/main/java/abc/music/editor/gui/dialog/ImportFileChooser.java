package abc.music.editor.gui.dialog;

import abc.music.editor.gui.support.AmeFileView;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import nu.hex.abc.music.service.Service;

/**
 * Created 2016-nov-14
 *
 * @author hl
 */
public class ImportFileChooser extends JFileChooser {

    public ImportFileChooser() {
        super(Service.getSourceDirectory());
        super.setDialogTitle("Select Project");
        super.setFileView(new AmeFileView());
        super.setAcceptAllFileFilterUsed(false);
        super.setFileFilter(new FileNameExtensionFilter("Project Files", Service.SUFFIX));
        super.addChoosableFileFilter(new FileNameExtensionFilter("ABC-file", "abc"));
    }
}
