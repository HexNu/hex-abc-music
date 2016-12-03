package abc.music.editor.components;

import abc.music.core.domain.Tune;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.action.OpenTuneAction;

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
        super.setToolTipText("Double Click to Edit");
        this.tune = tune;
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    new OpenTuneAction(editor, tune).actionPerformed(null);
                }
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
