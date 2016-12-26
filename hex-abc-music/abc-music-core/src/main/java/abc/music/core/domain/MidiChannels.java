package abc.music.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import se.digitman.lightxml.DocumentToXmlNodeParser;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-26
 *
 * @author hl
 */
public class MidiChannels {

    private final List<ChannelGroup> groups = new ArrayList<>();

    public MidiChannels() {
        setup();
    }

    private void setup() {
        XmlNode rootNode = new DocumentToXmlNodeParser(MidiChannels.class.getClassLoader().getResourceAsStream("midi/midi-channels.xml")).parse();
        for (XmlNode groupNode : rootNode.getChildren("channel-group")) {
            ChannelGroup channelGroup = new ChannelGroup(groupNode.getAttribute("name"));
            for (XmlNode node : groupNode.getChildren("channel")) {
                channelGroup.addChannel(new Channel(node.getAttribute("name"), Integer.valueOf(node.getAttribute("program"))));
            }
            groups.add(channelGroup);
        }
    }

    public List<ChannelGroup> getGroups() {
        return groups;
    }

    public ChannelGroup getGroup(String label) {
        for (ChannelGroup group : groups) {
            if (group.getName().equalsIgnoreCase(label)) {
                return group;
            }
        }
        return null;
    }

    public ChannelGroup getGroup(Integer index) {
        if (index == null) {
            return groups.get(0);
        }
        return groups.get(index);
    }

    public Channel getChannel(Integer program) {
        if (program != null) {
            for (ChannelGroup group : groups) {
                if (group.getChannel(program) != null) {
                    return group.getChannel(program);
                }
            }
        }
        return null;
    }

    public static class Channel {

        private final String name;
        private final Integer program;
        private ChannelGroup group;

        public Channel(String name, Integer program) {
            this.name = name;
            this.program = program;
        }

        public String getName() {
            return name;
        }

        public Integer getProgram() {
            return program;
        }

        public ChannelGroup getGroup() {
            return group;
        }

        public void setGroup(ChannelGroup group) {
            this.group = group;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 89 * hash + Objects.hashCode(this.name);
            hash = 89 * hash + Objects.hashCode(this.group);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Channel other = (Channel) obj;
            if (!Objects.equals(this.name, other.name)) {
                return false;
            }
            if (!Objects.equals(this.group, other.group)) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    public static class ChannelGroup {

        private final String name;
        private final List<Channel> channels = new ArrayList<>();

        public ChannelGroup(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public List<Channel> getChannels() {
            return channels;
        }

        public void setChannels(List<Channel> channels) {
            this.channels.clear();
            channels.stream().forEach(this::addChannel);
        }

        public void addChannel(Channel channel) {
            channel.setGroup(this);
            this.channels.add(channel);
        }

        public Channel getChannel(Integer program) {
            if (program != null) {
                for (Channel channel : channels) {
                    if (channel.getProgram().equals(program)) {
                        return channel;
                    }
                }
            }
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 37 * hash + Objects.hashCode(this.name);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ChannelGroup other = (ChannelGroup) obj;
            if (!Objects.equals(this.name, other.name)) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return getName();
        }
    }
}
