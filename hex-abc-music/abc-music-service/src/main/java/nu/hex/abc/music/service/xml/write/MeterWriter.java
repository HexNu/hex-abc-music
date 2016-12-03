package nu.hex.abc.music.service.xml.write;

import abc.music.core.domain.Meter;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
class MeterWriter extends XmlWriter<Meter> {

    public MeterWriter(Meter meter) {
        super(meter);
    }

    @Override
    public XmlNode write() {
        result.addAttribute("numerator", entity.getNumerator());
        result.addAttribute("denominator", entity.getDenominator());
        result.addAttribute("use-symbol", entity.useSymbol().toString());
        return result;
    }
}
