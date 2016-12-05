package abc.music.editor.gui.support;

import javax.swing.DefaultComboBoxModel;

/**
 * Created 2016-dec-05
 *
 * @author hl
 */
public class TransposeComboBoxModel extends DefaultComboBoxModel {

    public TransposeComboBoxModel() {
        super(TransposeMap.getItems().toArray(new TransposeMap.Item[TransposeMap.getItems().size()]));
    }

}
