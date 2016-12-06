package abc.music.editor;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created 2016-dec-06
 *
 * @author hl
 */
public class FontFactory {

    public static Font ringbearer(int size) {
        return get("RINGM___.TTF", new Double(size).floatValue());
    }

    public static Font anironMedium(int size) {
        return get("anirm___.ttf", new Double(size).floatValue());
    }

    public static Font anironBold(int size) {
        return get("anirb___.ttf", new Double(size).floatValue());
    }

    private static Font get(String fontName, float size) {
        try {
            InputStream is = FontFactory.class.getClassLoader().getResourceAsStream("fonts/" + fontName);
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            Map<TextAttribute, Object> map = new HashMap<>();
            map.put(TextAttribute.KERNING, TextAttribute.KERNING_ON);
            map.put(TextAttribute.SIZE, size);
            return font.deriveFont(map);
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(FontFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Font("FreeSerif", 0, new Double(size).intValue());
    }
}
