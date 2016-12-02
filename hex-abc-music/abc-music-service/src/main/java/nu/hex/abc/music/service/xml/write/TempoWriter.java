package nu.hex.abc.music.service.xml.write;

import abc.music.core.domain.Tempo;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
class TempoWriter extends XmlWriter<Tempo> {

    public TempoWriter(Tempo tempo) {
        super(tempo);
    }

    @Override
    public XmlNode write() {
        result.addAttribute("label", entity.getLabel());
        result.addAttribute("", entity.getUnit().getUnit());
        result.addAttribute("units-per-minute", entity.getUnitsPerMinute());
        return result;
    }
}
