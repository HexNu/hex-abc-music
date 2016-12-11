package abc.music.editor.action;

import abc.music.core.domain.Book;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.gui.dialog.AmeDialog;
import abc.music.editor.gui.dialog.EditBookDialog;
import java.awt.event.ActionEvent;

/**
 * Created 2016-dec-11
 *
 * @author hl
 */
public class CreateBookAction extends AmeAction<Book> {

    public CreateBookAction(AbcMusicEditor editor) {
        super(editor);
    }

    @Override
    protected void performAction(ActionEvent event) {
        Book book = new Book();
        EditBookDialog dialog = new EditBookDialog(editor, book);
        dialog.setVisible(true);
        if (dialog.getResult().equals(AmeDialog.Result.OK)) {
            editor.getProject().addBook(book);
            editor.updateMenuBar();
        }
    }

}
