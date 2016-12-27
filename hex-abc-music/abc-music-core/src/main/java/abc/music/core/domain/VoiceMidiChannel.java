package abc.music.core.domain;

/**
 * Created 2016-dec-27
 *
 * @author hl
 */
public class VoiceMidiChannel {

    private Integer index;
    private MidiChannels.Channel channel;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public MidiChannels.Channel getChannel() {
        return channel;
    }

    public void setChannel(MidiChannels.Channel channel) {
        this.channel = channel;
    }
}
