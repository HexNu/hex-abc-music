package nu.hex.abc.music.service.io;

import abc.music.core.domain.Tune;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.hex.abc.music.service.properties.AbcMusicProperties;
import nu.hex.abc.music.service.properties.PropertyService;

/**
 * Created 2016-dec-03
 *
 * @author hl
 */
class SvgWriter implements Writer<File> {

    private static final String ISO_8859_1 = "ISO-8859-1";
    private static final String UTF_8 = "UTF-8";
    private final Tune tune;
    private File abcFile;
    private File svgFile;
    private File svgOutputFile;

    public SvgWriter(Tune tune) {
        this.tune = tune;
    }

    @Override
    public File write() {
        try {
            String svgFolder = PropertyService.getProperties().getProperty(AbcMusicProperties.PROJECT_SVG_FOLDER);
            String tuneFileName = tune.getName().replaceAll(" ", "_");
            svgFile = new File(svgFolder + tuneFileName + ".svg");
            abcFile = new File(svgFolder + tuneFileName + ".abc");
            createTemporaryAbcFile();
            Process process = processFile();
            process.waitFor();
            adjustEncoding(svgFolder, tuneFileName);
            return svgFile;
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(SvgWriter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cleanUp();
        }
        return null;
    }

    private void createTemporaryAbcFile() {
        new AbcFileWriter(tune, abcFile).write();
    }

    private Process processFile() throws IOException {
        String cmdString = "/usr/bin/abcm2ps " + getCmdFilePath(abcFile) + " -g -O " + getCmdFilePath(svgFile);
        Process process = Runtime.getRuntime().exec(cmdString);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getErrorStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
            }
        }
        return process;
    }

    private void adjustEncoding(String svgFolder, String tuneFileName) throws IOException {
        svgOutputFile = new File(svgFolder + tuneFileName + "001.svg");
        boolean firstLineRead = false;
        List<String> allNewLines = new ArrayList<>();
        allNewLines.add("<?xml version=\"1.0\" encoding=\"" + UTF_8 + "\" standalone=\"no\"?>");
        for (String line : Files.readAllLines(svgOutputFile.toPath(), Charset.forName(ISO_8859_1))) {
            if (!firstLineRead) {
                firstLineRead = true;
            } else {
                allNewLines.add(new String(line.getBytes(Charset.forName(ISO_8859_1)), Charset.forName(UTF_8)));
            }
        }
        Files.write(svgFile.toPath(), allNewLines);
    }

    private String getCmdFilePath(File file) {
        return file.getAbsolutePath().replaceAll(" ", "\\\\ \\\\");
    }

    private void cleanUp() {
        abcFile.delete();
        svgOutputFile.delete();
        svgFile.deleteOnExit();
    }
}
