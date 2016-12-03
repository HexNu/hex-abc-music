package nu.hex.abc.music.service.io;

import abc.music.core.domain.Tune;
import abc.music.core.domain.Voice;
import java.util.Arrays;
import java.util.List;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
class AbcWriter implements Writer<String> {

    private static final String NEW_LINE = "\n";
    private final List<Tune> tunes;
    private int voiceIndex = 0;

    public AbcWriter(Tune tune) {
        this(Arrays.asList(tune));
    }

    public AbcWriter(List<Tune> tunes) {
        this.tunes = tunes;
    }

    @Override
    public String write() {
        StringBuilder result = new StringBuilder(ABC_VERSION).append(NEW_LINE)
                .append(DEFAULT_CHARSET).append(NEW_LINE)
                .append(ABC_CREATOR).append(NEW_LINE);
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
        if (tune.hasUnit()) {
            result.append(tune.getUnitField()).append(NEW_LINE);
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
        result.append(NEW_LINE);
        return result.toString();
    }
    private static final String COMMENT = "%";

    public static void main(String[] args) {
        System.out.println(Writer.ABC_CREATOR);
    }
}
