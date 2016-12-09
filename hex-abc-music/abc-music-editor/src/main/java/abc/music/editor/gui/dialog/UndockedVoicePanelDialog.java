package abc.music.editor.gui.dialog;

import abc.music.editor.gui.VoicePanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Created 2016-dec-09
 *
 * @author hl
 */
public class UndockedVoicePanelDialog extends AmeDialog<VoicePanel> {

    private final VoicePanel panel;
    private JButton doneButton;
    private final int index;

    public UndockedVoicePanelDialog(VoicePanel panel, int index) {
        super(panel.getEditor(), "Voice Editor - " + panel.getVoice().getName());
        this.panel = panel;
        setup();
        this.index = index;
        panel.setDocked(false);
    }

    @Override
    protected void init() {
        setLayout(new BorderLayout());
        Dimension dimension = new Dimension(1800, 1200);
        setPreferredSize(dimension);
        setSize(dimension);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        doneButton = new JButton(" Done ");
        doneButton.addActionListener((ActionEvent e) -> {
           ok();
        });
        buttonsPanel.add(doneButton);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void setup() {
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void cancel() {
        dockPanel();
    }

    public void dockPanel() {
        panel.getVoicesPanel().reattachVoicePanel(panel, index);
        panel.setDocked(true);
    }

    @Override
    protected void accept() {
        dockPanel();
    }
}
