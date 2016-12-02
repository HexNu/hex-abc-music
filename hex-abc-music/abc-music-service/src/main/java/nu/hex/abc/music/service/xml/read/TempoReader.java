package nu.hex.abc.music.service.xml.read;

import abc.music.core.domain.Tempo;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-02
 *
 * @author hl
 */
public class TempoReader extends NodeReader<Tempo> {

    public TempoReader(XmlNode node) {
        super(node);
    }

    @Override
    public Tempo read() {
        Tempo result = new Tempo();
        if (node.hasAttribute("label")) {
            result.setLabel(node.getAttribute("label"));
        }
        if (node.hasAttribute("default-unit")) {
            result.setUnit(Tempo.Unit.find(node.getAttribute("default-unit")));
        }
        if (node.hasAttribute("units-per-minute")) {
            result.setUnitsPerMinute(Integer.valueOf(node.getAttribute("units-per-minute")));
        }
        return result;
    }
    
}
