package nu.hex.abc.music.service.io;

import abc.music.core.domain.Tune;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.hex.abc.music.service.properties.AbcMusicProperties;
import nu.hex.abc.music.service.properties.PropertyService;
import se.digitman.lightxml.DocumentToXmlNodeParser;
import se.digitman.lightxml.XmlDocument;

/**
 * Created 2016-dec-03
 *
 * @author hl
 */
class SvgWriter implements Writer<XmlDocument> {

    private final Tune tune;
    private File abcFile;
    private File svgFile;

    public SvgWriter(Tune tune) {
        this.tune = tune;
    }

    @Override
    public XmlDocument write() {
        try {
            String svgFolder = PropertyService.getProperties().getProperty(AbcMusicProperties.PROJECT_SVG_FOLDER);
            String tuneFileName = tune.getName().replaceAll(" ", "_");
            abcFile = new File(svgFolder + tuneFileName + ".abc");
            svgFile = new File(svgFolder + tuneFileName + ".svg");
            new AbcFileWriter(tune, abcFile).write();
            String cmdString = "/usr/bin/abcm2ps " + getCmdFilePath(abcFile) + " -g -O " + getCmdFilePath(svgFile);
            System.out.println(cmdString);

            Process process = Runtime.getRuntime().exec(cmdString);
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Logger.getLogger(SvgWriter.class.getName()).log(Level.WARNING, line);
                }
            }
            process.waitFor();
            return new XmlDocument(new DocumentToXmlNodeParser(new FileInputStream(new File(svgFolder + tuneFileName + "001.svg"))).parse());
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(SvgWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private String getCmdFilePath(File file) {
        return file.getAbsolutePath().replaceAll(" ", "\\\\ \\\\");

    }
}
