package abc.music.core.io;

import abc.music.core.domain.Tune;
import abc.music.core.domain.Voice;
import java.util.Arrays;
import java.util.List;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class AbcWriter {

    private final List<Tune> tunes;
    private int tuneIndex = 0;
    private int voiceIndex = 0;

    public AbcWriter(Tune tune) {
        this(Arrays.asList(tune));
    }

    public AbcWriter(List<Tune> tunes) {
        this.tunes = tunes;
    }

    public String write() {
        StringBuilder result = new StringBuilder();
        tunes.forEach((tune) -> {
            if (tuneIndex++ > 0) {
                result.append('\n');
            }
            result.append(createHead(tune));
            result.append(createBody(tune));
        });
        return result.toString();
    }

    private String createHead(Tune tune) {
        StringBuilder result = new StringBuilder();
        result.append(tune.getIdField()).append('\n');
        tune.getTitleFields().forEach((titleField) -> {
            result.append(titleField).append('\n');
        });
        tune.getCreators().forEach((originator) -> {
            result.append(originator.get()).append('\n');
        });
        if (tune.hasRythmField()) {
            result.append(tune.getRythmField()).append('\n');
        }
        tune.getOrigin().forEach((origin) -> {
            result.append(origin.get()).append('\n');
        });
        tune.getComments().forEach((comment) -> {
            result.append(comment.get()).append('\n');
        });
        tune.getHistory().forEach((history) -> {
            result.append(history.get()).append('\n');
        });
        if (tune.hasTempo()) {
            result.append(tune.getTempo().get()).append('\n');
        }
        if (tune.hasMeter()) {
            result.append(tune.getMeter().get()).append('\n');
        }
        if (tune.hasUnit()) {
            result.append(tune.getUnitField()).append('\n');
        }
        if (tune.hasKey()) {
            result.append(tune.getKey().get());
            if (tune.getVoices().size() <= 1 && tune.getKey().hasModifier()) {
                result.append(tune.getKey().getModifier().get());
            }
            result.append('\n');
        }
        return result.toString();
    }

    private String createBody(Tune tune) {
        StringBuilder result = new StringBuilder("");
        if (tune.getVoices().size() == 1) {
            Voice voice = tune.getVoices().get(0);
            result.append(voice.getNotes()).append('\n');
        } else if (tune.getVoices().size() > 1) {
            result.append("%% Begin Score").append('\n');
            tune.getVoices().forEach((voice) -> {
                result.append("%% ").append(voice.getName().toUpperCase()).append('\n');
                result.append(voice.get()).append('\n');
                result.append(voice.getNotes()).append('\n');
                if (voiceIndex++ < tune.getVoices().size() - 1) {
                    result.append("%%\n");
                } else {
                    result.append("%% End Score \n%%");
                }
            });
        }
        result.append('\n');
        return result.toString();
    }
}
