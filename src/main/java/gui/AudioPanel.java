package gui;

import gui.interfaces.IActionListener;
import util.PlayAudioUtil;
import util.RecordAudioUtil;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

public class AudioPanel extends JPanel {

    public static final String AUDIO_LOCATION = "src/main/resources/audio/";

    JList jList;
    File audioFolder = new File(AUDIO_LOCATION);

    JToolBar audioToolbar = new JToolBar();

    JButton recordButton = new JButton("RECORD");
    JButton playButton = new JButton("PLAY");
    JButton deleteButton = new JButton("DELETE");

    IActionListener iActionListener;

    public AudioPanel() {
        super();
        setLayout(new BorderLayout());
        audioToolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        audioToolbar.setFloatable(false);

        playButton.addActionListener(playButtonAction());
        recordButton.addActionListener(recordButtonAction());
        deleteButton.addActionListener(deleteButtonAction());

        audioToolbar.add(recordButton);
        audioToolbar.add(playButton);
        audioToolbar.add(deleteButton);

        initAudioList();

        add(jList, BorderLayout.WEST);
        add(audioToolbar, BorderLayout.SOUTH);
    }

    private void initAudioList(){
        if (!audioFolder.exists()) audioFolder.mkdir();
        jList = new JList(getAudioFileNames(audioFolder.listFiles()));
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public String[] getAudioFileNames(File[] files) {
        if (files == null) return new String[]{};
        String[] fileNames = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            fileNames[i] = files[i].getName();
        }
        return fileNames;
    }

    private ActionListener deleteButtonAction() {
        return actionEvent -> {
            if (jList.getSelectedValue() != null) {
                File selectedFile = new File(AUDIO_LOCATION + jList.getSelectedValue().toString());
                selectedFile.delete();
                refreshPanel();
            }
        };
    }

    ActionListener recordButtonAction() {
        return actionEvent -> {
            RecordAudioUtil.recordAudio();
            refreshPanel();
        };
    }

    ActionListener playButtonAction() {
        return actionEvent -> {
            if (jList.getSelectedValue() != null) {
                PlayAudioUtil.playAudio(AUDIO_LOCATION + jList.getSelectedValue().toString());
            }
        };
    }

    void refreshPanel() {
        remove(jList);
        jList = new JList(getAudioFileNames(audioFolder.listFiles()));
        add(jList, BorderLayout.WEST);
        iActionListener.action("refresh");
    }

    public void setActionListener(IActionListener iActionListener) {
        this.iActionListener = iActionListener;
    }

}