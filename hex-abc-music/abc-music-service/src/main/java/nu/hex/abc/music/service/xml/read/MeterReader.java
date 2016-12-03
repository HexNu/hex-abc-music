package nu.hex.abc.music.service.xml.read;

import abc.music.core.domain.Meter;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-02
 *
 * @author hl
 */
class MeterReader extends NodeReader<Meter> {

    public MeterReader(XmlNode node) {
        super(node);
    }

    @Override
    public Meter read() {
        Meter result = new Meter();
        if (node.hasAttribute("numerator")) {
            result.setNumerator(Integer.valueOf(node.getAttribute("numerator")));
        }
        if (node.hasAttribute("denominator")) {
            result.setDenominator(Integer.valueOf(node.getAttribute("denominator")));
        }
        if (node.hasAttribute("use-symbol")) {
            result.setUseSymbol(node.getAttribute("use-symbol").equals("true"));
        }
        return result;
    }

}
