package abc.music.editor.action;

import abc.music.core.domain.FormatTemplate;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.gui.dialog.FormatTemplateDialog;
import java.awt.event.ActionEvent;

/**
 * Created 2016-dec-16
 *
 * @author hl
 */
public class EditFormatTemplateAction extends AmeAction<FormatTemplate>{

    private final FormatTemplate template;

    public EditFormatTemplateAction(AbcMusicEditor editor) {
        this(editor, null);
    }

    public EditFormatTemplateAction(AbcMusicEditor editor, FormatTemplate template) {
        super(editor);
        this.template = template;
    }

    @Override
    protected void performAction(ActionEvent event) {
        FormatTemplateDialog dialog = new FormatTemplateDialog(editor);
        dialog.setVisible(true);
    }

}
