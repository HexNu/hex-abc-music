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
public class EditBookAction extends AmeAction<Book> {

    private final Book book;

    public EditBookAction(AbcMusicEditor editor, Book book) {
        super(editor);
        this.book = book;
    }

    @Override
    protected void performAction(ActionEvent event) {
        EditBookDialog dialog = new EditBookDialog(editor, book);
        dialog.setVisible(true);
        if (dialog.getResult().equals(AmeDialog.Result.OK)) {
            System.out.println(book.getName());
            editor.updateMenuBar();
        }
    }

}
