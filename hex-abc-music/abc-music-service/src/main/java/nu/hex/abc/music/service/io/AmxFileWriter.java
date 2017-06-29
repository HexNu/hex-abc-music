package nu.hex.abc.music.service.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import nu.hex.mediatype.HexMediaType;

/**
 * Created 2016-dec-05
 *
 * @author hl
 */
class AmxFileWriter implements Writer<Void> {

    private final String content;
    private final String name;
    private final File resultFile;
    private java.io.Writer writer;

    public AmxFileWriter(String content, String name, File resultFile) {
        this.content = content;
        this.name = name;
        this.resultFile = resultFile;
    }

    @Override
    public Void write() {
        writer = null;
        try {
            ZipOutputStream zipOutputStream;
            writer = new OutputStreamWriter(zipOutputStream = new ZipOutputStream(new FileOutputStream(resultFile)), StandardCharsets.UTF_8);
            addZipEntry(zipOutputStream, "mimetype", HexMediaType.APPLICATION_VND_HEX_AMX);
            addZipEntry(zipOutputStream, name, content);
        } catch (IOException ex) {
            Logger.getLogger(AmxFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(AmxFileWriter.class.getName()).log(Level.SEVERE, "Unable to close OutputStreamWriter", ex);
                }
            }
        }
        return null;
    }

    private void addZipEntry(ZipOutputStream zipOutputStream, String name, String content) throws IOException {
        zipOutputStream.putNextEntry(new ZipEntry(name));
        writer.append(content);
        writer.flush();
    }

    public static void main(String[] args) {
        File file = new File("/home/hl/Skrivbord/Egna låtar.amx");
        String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<project name=\"Egna låtar\" abc-version=\"2.2\" summary=\"Egna låtar, vissa skrivna med andra\" last-updated=\"2016-12-05T12:15:15.872\">\n"
                + "  <owner first-name=\"Håkan\" last-name=\"Lidén\" email=\"hl@hex.nu\"/>\n"
                + "  <tunes>\n"
                + "    <tune id=\"1\" time-value=\"1/8\">\n"
                + "      <titles>\n"
                + "        <title>Klockare Andersson</title>\n"
                + "        <title>Vals från Norrbotten</title>\n"
                + "      </titles>\n"
                + "      <creators>\n"
                + "        <transcriber person=\"1\"/>\n"
                + "      </creators>\n"
                + "      <origins/>\n"
                + "      <comments/>\n"
                + "      <histories/>\n"
                + "      <copyrights/>\n"
                + "      <tempo default-unit=\"1/4\" units-per-minute=\"108\"/>\n"
                + "      <meter numerator=\"3\" denominator=\"4\" use-symbol=\"false\"/>\n"
                + "      <key pitch=\"D\" mode=\"Major\" signature=\"\">\n"
                + "        <modifier transpose=\"0\" clef=\"G\" octave=\"\"/>\n"
                + "      </key>\n"
                + "      <voices>\n"
                + "        <voice id=\"1\" short-name=\"Vl 1\" name=\"Violin 1\" use-voice-key=\"false\" use-voice-modifier=\"false\" stem=\"\">\n"
                + "          <key pitch=\"D\" mode=\"Major\" signature=\"\">\n"
                + "            <modifier transpose=\"0\" clef=\"G\" octave=\"\"/>\n"
                + "          </key>\n"
                + "          <body>(DF) (AD) FA | d2 (cB) AG | (FA) (DF) Ad | (fd) (AF) DF | G2 EE E2 | (A,C) (EA) c2 |\n"
                + "(cB) (AG) (FE) | F4 D2 | (DF) (AD) FA | d2 cB AG | FA DF Ad | fd AF DF | \n"
                + "G2 EE E2 | A,C EA c2 | cB AG FE |1 D6 y :|2 D4 FG  |: A2 D2 F2 | \n"
                + "A2 d3 f | e2 A2 c2 | e4 ef | gb ba ag | ge eA ce | df Ad FA | \n"
                + "DE FG AB | A2 D2 F2 | A2 d3 f | e2 A2 c2 | e4 ef |  gb ba ag | \n"
                + "ge eA ce | d3 d d2 |1 d4 FG :|2 d4 Bc [K:G]|: d2 b3 g | e4 d2 | \n"
                + "bg dB GD | G,4 B,D | A,2 D3 F | A2 B2 c2 | &quot;^4&quot;!open!e2 &quot;^3&quot;!open!d2 &quot;^4&quot;!open!A2 |  &quot;^3&quot;!open!G4 Bc | \n"
                + "d2 b3 g | e4 d2 | bg dB GD | G,4 B,D | A,2 D3 F |  A2 d2 F2 | \n"
                + "G3 G G2 |1 G4 Bc :|2 G6 |]</body>\n"
                + "        </voice>\n"
                + "        <voice id=\"2\" short-name=\"Vl 2\" name=\"Violin 2\" use-voice-key=\"false\" use-voice-modifier=\"false\" stem=\"\">\n"
                + "          <key pitch=\"D\" mode=\"Major\" signature=\"\">\n"
                + "            <modifier transpose=\"0\" clef=\"G\" octave=\"\"/>\n"
                + "          </key>\n"
                + "          <body>(A,D) (FA,) DF | B2 (AG) FE | DF A,D FA | dA FD A,D | C2 CC C2 | A,2 CE A2 | \n"
                + "AG FE FG | A4 F2 | A,D FA, DF | B2 AG FE | DF A,D FA | dA FD A,D | \n"
                + "C2 CC C2 | A,2 CE A2 | AG FE DE | F6 y :| F4 DE |: F2 A,2 D2 | \n"
                + "F2 A3 d | c2 E2 A2 | c4 cd | eg gf fe | ec AC EG | FA DF A,D  | \n"
                + "FE DE FG | F2 A,2 D2 | F2 A3 d | c2 E2 A2 | c4 cd | eg gf fe | \n"
                + "ec AC EG | F3 F F2 | F4 DE :| F4 GA [K:G]|: B2 g3 e | c4 B2 | \n"
                + "gd BG DB, | B,4 G,B, | C2 A,3 D | F2 G2 A2 | g2 f2 c2 | B4 GA |\n"
                + "B2 g3 e | c4 B2 | gd BG DB, | B,4 G,B, | C2 A,3 D | F2 F2 C2 | \n"
                + "B,3 B, B,2 | B,4 GA :| B6 |]</body>\n"
                + "        </voice>\n"
                + "      </voices>\n"
                + "      <score-layout/>\n"
                + "    </tune>\n"
                + "    <tune id=\"2\" rythm=\"\" time-value=\"1/8\">\n"
                + "      <titles>\n"
                + "        <title>Klarinettpolska</title>\n"
                + "      </titles>\n"
                + "      <creators>\n"
                + "        <composer person=\"1\"/>\n"
                + "        <transcriber person=\"1\"/>\n"
                + "      </creators>\n"
                + "      <origins/>\n"
                + "      <comments/>\n"
                + "      <histories/>\n"
                + "      <copyrights/>\n"
                + "      <tempo default-unit=\"1/4\" units-per-minute=\"96\"/>\n"
                + "      <meter numerator=\"3\" denominator=\"4\" use-symbol=\"false\"/>\n"
                + "      <key pitch=\"C\" mode=\"Minor\" signature=\"\">\n"
                + "        <modifier transpose=\"0\" clef=\"G\" octave=\"\"/>\n"
                + "      </key>\n"
                + "      <voices>\n"
                + "        <voice id=\"cl\" short-name=\"Kl\" name=\"Klarinett i B♭\" use-voice-key=\"true\" use-voice-modifier=\"false\" stem=\"\">\n"
                + "          <key pitch=\"D\" mode=\"Minor\" signature=\"\">\n"
                + "            <modifier transpose=\"0\" clef=\"G\" octave=\"\"/>\n"
                + "          </key>\n"
                + "          <body>d&gt;e d^c d2 |</body>\n"
                + "        </voice>\n"
                + "        <voice id=\"vl\" short-name=\"Fi\" name=\"Fiol\" use-voice-key=\"false\" use-voice-modifier=\"false\" stem=\"\">\n"
                + "          <key pitch=\"C\" mode=\"Minor\" signature=\"\">\n"
                + "            <modifier transpose=\"0\" clef=\"G\" octave=\"\"/>\n"
                + "          </key>\n"
                + "          <body>c&gt;d c=B c2 |</body>\n"
                + "        </voice>\n"
                + "      </voices>\n"
                + "      <score-layout/>\n"
                + "    </tune>\n"
                + "  </tunes>\n"
                + "  <persons>\n"
                + "    <person id=\"1\" first-name=\"Håkan\" last-name=\"Lidén\" email=\"hl@hex.nu\"/>\n"
                + "  </persons>\n"
                + "</project>";
        new AmxFileWriter(content, "project.xml", file).write();
    }
}
