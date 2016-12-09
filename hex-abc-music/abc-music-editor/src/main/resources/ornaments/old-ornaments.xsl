<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="text"/>

    <xsl:template match="list">
        <xsl:text>X:1&#10;T:Ornaments&#10;M:none&#10;L:1/1&#10;K:C&#10;</xsl:text>
        <xsl:apply-templates select="item"/>
        <xsl:text>&#10;</xsl:text>
    </xsl:template>

    <xsl:template match="item">
        <xsl:choose>
            <xsl:when test="@dynamic = 'true'">
                <xsl:text>"^</xsl:text>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text>"_</xsl:text>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:value-of select="label"/>
        <xsl:text>"</xsl:text>
        <xsl:value-of select="label"/>
        <xsl:text>c </xsl:text>
        <xsl:choose>
            <xsl:when test="position() mod 5 = 0">
                <xsl:text>|&#10;</xsl:text>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text>| </xsl:text>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
</xsl:stylesheet>
