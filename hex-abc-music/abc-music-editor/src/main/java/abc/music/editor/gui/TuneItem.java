package abc.music.editor.gui;

import abc.music.core.domain.Tune;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.action.OpenTuneAction;
import abc.music.editor.gui.support.ListItemMouseListener;

/**
 * Created 2016-dec-02
 *
 * @author hl
 */
public class TuneItem extends JLabel {

    private final Tune tune;

    public TuneItem(AbcMusicEditor editor, Tune tune) {
        super.setIcon(new ImageIcon(getClass().getResource("/images/empty-icon.png")));
        super.setText(tune.getTitles().get(0));
        int titleIndex = 0;
        String toolTip = "<html>";
        for (String title : tune.getTitles()) {
            if (titleIndex++ == 0) {
                toolTip += "<b>" + title + "</b><br/>";
            } else {
                toolTip += title + "<br/>";
            }
        }
        super.setToolTipText(toolTip + "<br><i>Double Click to Edit</i>");
        this.tune = tune;
        super.addMouseListener(new ListItemMouseListener() {
            @Override
            protected void doubleClickAction() {
                new OpenTuneAction(editor, tune).actionPerformed(null);
            }
        });
    }

    public Tune getTune() {
        return tune;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.tune);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TuneItem)) {
            return false;
        }
        TuneItem other = (TuneItem) obj;
        return this.getTune().getTitles().get(0).equals(other.getTune().getTitles().get(0));
    }
}
