package abc.music.editor.action;

import abc.music.core.domain.Tune;
import abc.music.editor.AbcMusicEditor;
import java.awt.event.ActionEvent;
import java.util.List;
import nu.hex.abc.music.service.Service;

/**
 * Created 2016-dec-03
 *
 * @author hl
 */
public class SearchTunesAction extends AmeAction<List<Tune>> {

    private final String searchString;

    public SearchTunesAction(AbcMusicEditor editor, String searchString) {
        super(editor);
        this.searchString = searchString;
    }

    @Override
    protected void performAction(ActionEvent event) {
        setResult(new Service(editor.getProject()).searchTunes(searchString));
        String res = get().size() == 1 ? " result." : " results,";
        setLeftStatus("Search for \"" + searchString + "\" return " + get().size() + res, 3000);
    }
}
