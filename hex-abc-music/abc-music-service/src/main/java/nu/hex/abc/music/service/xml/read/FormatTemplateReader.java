package nu.hex.abc.music.service.xml.read;

import abc.music.core.domain.FormatTemplate;
import abc.music.core.domain.PostScriptFont;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.hex.abc.music.service.io.Reader;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-16
 *
 * @author hl
 */
public class FormatTemplateReader implements Reader<FormatTemplate> {

    private final XmlNode node;

    public FormatTemplateReader(XmlNode node) {
        this.node = node;
    }

    @Override
    public FormatTemplate read() {
        FormatTemplate result = new FormatTemplate(node.getAttribute("name"));
        if (node.hasChildNamed("short-description")) {
            result.setShortDescription(node.getChild("short-description").getText());
        }
        if (node.hasAttribute("indent")) {
            result.setIndent(Double.valueOf(node.getAttribute("indent")));
        }
        if (node.hasAttribute("scale")) {
            result.setScale(Double.valueOf(node.getAttribute("scale")));
        }
        if (node.hasAttribute("max-shrinking")) {
            result.setMaxShrinking(Double.valueOf(node.getAttribute("max-shrinking")));
        }
        if (node.hasAttribute("bars-per-staff")) {
            result.setBarsPerStaff(Integer.valueOf(node.getAttribute("bars-per-staff")));
        }
        if (node.hasAttribute("line-length")) {
            result.setLineLength(Integer.valueOf(node.getAttribute("line-length")));
        }
        if (node.hasAttribute("stretch-last")) {
            result.setStretchLastStaff(Boolean.valueOf(node.getAttribute("stretch-last")));
        }
        if (node.hasAttribute("landscape")) {
            result.setLandscape(Boolean.valueOf(node.getAttribute("landscape")));
        }
        if (node.hasChildNamed("page-headers")) {
            XmlNode headers = node.getChild("page-headers");
            if (headers.hasAttribute("left")) {
                result.setHeaderLeft(headers.getAttribute("left"));
            }
            if (headers.hasAttribute("center")) {
                result.setHeaderCenter(headers.getAttribute("center"));
            }
            if (headers.hasAttribute("right")) {
                result.setHeaderRight(headers.getAttribute("right"));
            }
        }
        if (node.hasChildNamed("page-footers")) {
            XmlNode footers = node.getChild("page-footers");
            if (footers.hasAttribute("left")) {
                result.setFooterLeft(footers.getAttribute("left"));
            }
            if (footers.hasAttribute("center")) {
                result.setFooterCenter(footers.getAttribute("center"));
            }
            if (footers.hasAttribute("right")) {
                result.setFooterRight(footers.getAttribute("right"));
            }
        }
        if (node.hasChildNamed("margins") && node.getChild("margins").hasChildNamed("margin")) {
            node.getChild("margins").getChildren("margin").stream().forEach((m) -> {
                FormatTemplate.Margin margin = FormatTemplate.Margin.valueOf(m.getAttribute("position").toUpperCase());
                result.addMargin(margin, Double.valueOf(m.getAttribute("value")));
            });
        }
        if (node.hasChildNamed("spaces") && node.getChild("spaces").hasChildNamed("space")) {
            node.getChild("spaces").getChildren("space").stream().forEach((s) -> {
                FormatTemplate.Space space = FormatTemplate.Space.find(s.getAttribute("name"));
                if (space == null) {
                    Logger.getLogger(FormatTemplateReader.class.getName()).log(Level.WARNING, "Could not find Space: {0}", s.getAttribute("name"));
                }
                if (space != null && s.hasAttribute("value")) {
                    try {
                        result.addSpace(space, Double.valueOf(s.getAttribute("value")));
                    } catch (NumberFormatException e) {
                        Logger.getLogger(FormatTemplateReader.class.getName()).log(Level.WARNING, "Could not read value for {0}", space);
                    }
                }
            });
        }
        if (node.hasChildNamed("fonts") && node.getChild("fonts").hasChildNamed("font")) {
            node.getChild("fonts").getChildren("font").stream().forEach((f) -> {
                if (f.hasAttribute("ps-font") && f.hasAttribute("size")) {
                    FormatTemplate.Font font = FormatTemplate.Font.find((f.getAttribute("type")));
                    if (font != null) {
                        FormatTemplate.FontValue fontValue = new FormatTemplate.FontValue(PostScriptFont.find(f.getAttribute("ps-font")), Integer.valueOf(f.getAttribute("size")));
                        result.addFont(font, fontValue);
                    }
                }
            });
        }
        return result;
    }

}
