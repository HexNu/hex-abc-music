package nu.hex.abc.music.service.xml.write;

import abc.music.core.domain.FormatTemplate;
import nu.hex.abc.music.service.io.Writer;
import se.digitman.lightxml.NodeFactory;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-16
 *
 * @author hl
 */
public class FormatTemplateWriter implements Writer<XmlNode> {

    private final FormatTemplate template;
    private XmlNode result;

    public FormatTemplateWriter(FormatTemplate template) {
        this.template = template;
    }

    @Override
    public XmlNode write() {
        result = NodeFactory.createNode("template");
        result.addAttribute("name", template.getName());
        result.addChild(NodeFactory.createNode("short-description", template.getShortDescription()));
        if (template.hasIndent()) {
            result.addAttribute("indent", template.getIndent().toString());
        }
        if (template.hasScale()) {
            result.addAttribute("scale", template.getScale().toString());
        }
        if (template.hasMaxSrhinking()) {
            result.addAttribute("max-shrinking", template.getMaxShrinking().toString());
        }
        if (template.hasBarsPerStaff()) {
            result.addAttribute("bars-per-staff", template.getBarsPerStaff());
        }
        if (template.hasLineLength()) {
            result.addAttribute("line-length", template.getLineLength());
        }
        if (template.hasStretchLastStaff()) {
            result.addAttribute("stretch-last-staff", template.getStretchLastStaff().toString());
        }
        if (template.hasLandscape()) {
            result.addAttribute("landscape", template.getLandscape().toString());
        }
        XmlNode pageHeaders = NodeFactory.createNode("page-headers");
        result.addChild(pageHeaders);
        if (template.hasHeaderLeft()) {
            pageHeaders.addAttribute("left", template.getHeaderLeft());
        }
        if (template.hasHeaderCenter()) {
            pageHeaders.addAttribute("center", template.getHeaderCenter());
        }
        if (template.hasHeaderRight()) {
            pageHeaders.addAttribute("right", template.getHeaderRight());
        }
        XmlNode pageFooters = NodeFactory.createNode("page-footers");
        if (template.hasFooterLeft()) {
            pageFooters.addAttribute("left", template.getFooterLeft());
        }
        if (template.hasFooterCenter()) {
            pageFooters.addAttribute("center", template.getFooterCenter());
        }
        if (template.hasFooterRight()) {
            pageFooters.addAttribute("right", template.getFooterRight());
        }
        result.addChild(pageFooters);
        if (template.hasMargins()) {
            template.getMargins().keySet().stream().forEach((margin) -> {
                createMarginNode(margin, template.getMargin(margin));
            });
        }
        if (template.hasSpaceValues()) {
            template.getSpaces().keySet().stream().forEach((space) -> {
                createSpaceNode(space, template.getSpaceValue(space));
            });
        }
        if (template.hasFonts()) {
            template.getFonts().keySet().stream().forEach((font) -> {
                createFontNode(font, template.getFontValue(font));
            });
        }
        return result;
    }

    private void createMarginNode(FormatTemplate.Margin margin, Double value) {
        if (value != null) {
            XmlNode marginNode = NodeFactory.createNode("margin");
            marginNode.addAttribute("position", margin.name().toLowerCase());
            marginNode.addAttribute("value", value.toString());
            if (!result.hasChildNamed("margins")) {
                result.addChild(NodeFactory.createNode("margins"));
            }
            result.getChild("margins").addChild(marginNode);
        }
    }

    private void createSpaceNode(FormatTemplate.Space space, Double value) {
        if (value != null) {
            XmlNode spaceNode = NodeFactory.createNode("space");
            spaceNode.addAttribute("name", space.name().toLowerCase().replaceAll("_", "-"));
            spaceNode.addAttribute("value", value.toString());
            if (!result.hasChildNamed("spaces")) {
                result.addChild(NodeFactory.createNode("spaces"));
            }
            result.getChild("spaces").addChild(spaceNode);
        }
    }

    private void createFontNode(FormatTemplate.Font font, FormatTemplate.FontValue fontValue) {
        if (fontValue != null) {
            XmlNode fontNode = NodeFactory.createNode("font");
            fontNode.addAttribute("type", font.name().toLowerCase().replaceAll("_", "-"));
            fontNode.addAttribute("ps-font", fontValue.getPsFont().getName());
            fontNode.addAttribute("size", fontValue.getSize());
            if (!result.hasChildNamed("fonts")) {
                result.addChild(NodeFactory.createNode("fonts"));
            }
            result.getChild("fonts").addChild(fontNode);
        }
    }

}
