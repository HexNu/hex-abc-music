package abc.music.editor;

import java.awt.Color;
import java.awt.Font;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
public interface AmeConstants {

    static final String APP_TITLE = "Hex Abc Music Editor";
    static final Color TITLE_COLOR = new Color(184, 36, 90);
    static final Color MENU_HEADER_COLOR = new Color(90, 35, 184);
    static final Color BACKGROUND_COLOR = new Color(251, 255, 229, 229);
    static final Color HELP_DIALOG_BACKGOUND_COLOR = Color.WHITE;

    static final Font BIG_TITLE_FONT = FontFactory.ringbearer(36);
    static final Font MEDIUM_TITLE_FONT = FontFactory.ringbearer(18);
    static final Font SMALL_TITLE_FONT = FontFactory.ringbearer(15);
    static final Font TAB_LABEL_FONT = FontFactory.ringbearer(12);
    static final Font MENU_HEADER_FONT = FontFactory.ringbearer(15);
    static final Font NOTE_AREA_FONT = new Font("FreeMono", 0, 15);
}
