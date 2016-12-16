package nu.hex.abc.music.service.io;

import abc.music.core.domain.History;
import abc.music.core.domain.Tune;
import abc.music.core.domain.Voice;
import abc.music.core.util.TextUtil;
import java.util.Arrays;
import java.util.List;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
class AbcDocWriter implements Writer<String> {

    private static final String NEW_LINE = "\n";
    private static final String COMMENT = "%";
    private final List<Tune> tunes;
    private int voiceIndex = 0;

    public AbcDocWriter(Tune tune) {
        this(Arrays.asList(tune));
    }

    public AbcDocWriter(List<Tune> tunes) {
        this.tunes = tunes;
    }

    @Override
    public String write() {
        StringBuilder result = new StringBuilder();
        tunes.forEach((tune) -> {
            result.append(NEW_LINE);
            result.append(createHead(tune));
            result.append(createBody(tune));
        });
        return result.toString();
    }

    private String createHead(Tune tune) {
        StringBuilder result = new StringBuilder();
        result.append(tune.getIdField()).append(NEW_LINE);
        tune.getTitleFields().forEach((titleField) -> {
            result.append(titleField).append(NEW_LINE);
        });
        tune.getCreators().forEach((originator) -> {
            result.append(originator.get()).append(NEW_LINE);
        });
        if (tune.hasRythmField()) {
            result.append(tune.getRythmField()).append(NEW_LINE);
        }
        tune.getOrigin().forEach((origin) -> {
            result.append(origin.get()).append(NEW_LINE);
        });
        tune.getComments().forEach((comment) -> {
            result.append(comment.get()).append(NEW_LINE);
        });
        tune.getHistory().forEach((history) -> {
            result.append(history.get()).append(NEW_LINE);
        });
        if (tune.hasTempo()) {
            result.append(tune.getTempo().get()).append(NEW_LINE);
        }
        if (tune.hasMeter()) {
            result.append(tune.getMeter().get()).append(NEW_LINE);
        }
        if (tune.hasTimeValue()) {
            result.append(tune.getTimeValueField()).append(NEW_LINE);
        }
        if (tune.hasScoreLayout()) {
            result.append("%%staves ").append(tune.getScoreLayout()).append(NEW_LINE);
        }
        if (tune.hasKey()) {
            result.append(tune.getKey().get());
            if (tune.getVoices().size() <= 1 && tune.getKey().hasModifier()) {
                result.append(tune.getKey().getModifier().get());
            }
            result.append(NEW_LINE);
        }
        return result.toString();
    }

    private String createBody(Tune tune) {
        StringBuilder result = new StringBuilder("");
        if (tune.getVoices().size() == 1) {
            Voice voice = tune.getVoices().get(0);
            result.append(voice.getNotes()).append(NEW_LINE);
        } else if (tune.getVoices().size() > 1) {
            result.append(COMMENT).append(" Begin Score").append(NEW_LINE);
            tune.getVoices().forEach((voice) -> {
                result.append(COMMENT).append(" ")
                        .append(voice.getName().toUpperCase()).append(NEW_LINE);
                result.append(voice.get()).append(NEW_LINE);
                result.append(voice.getNotes()).append(NEW_LINE);
                if (voiceIndex++ < tune.getVoices().size() - 1) {
                    result.append(COMMENT).append(NEW_LINE);
                } else {
                    result.append(COMMENT).append(" End Score").append(NEW_LINE)
                            .append(COMMENT).append(NEW_LINE);
                }
            });
        }
        if (tune.hasLyrics()) {
            result.append(tune.getLyrics().getAbcString()).append(NEW_LINE);
        }
        if (tune.hasHistory()) {
            result.append("%%textfont Times-Italic 12").append(NEW_LINE)
                    .append("%%textoption right").append(NEW_LINE)
                    .append("%%begintext").append(NEW_LINE);
            for (History history : tune.getHistory()) {
                String[] para = new TextUtil(history.getContent()).createLines(100).split("\n");
                for (String line : para) {
                    result.append("%%" + line).append(NEW_LINE);
                }
            }
            result.append("%%endtext").append(NEW_LINE);
        }
        result.append(NEW_LINE);
        return result.toString();
    }
}
