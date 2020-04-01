package gui.panels;

import util.PlayAudioUtil;
import util.RecordAudioUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AudioPanel extends JPanel {

    public static final String AUDIO_LOCATION = "src/main/resources/audio/";

    JList jList;
    File audioFolder = new File(AUDIO_LOCATION);
    JButton recordButton = new JButton("RECORD");
    JButton playButton = new JButton("PLAY");
    File[] audioFiles = audioFolder.listFiles();
    JToolBar audioToolbar = new JToolBar();
    JButton refreshButton = new JButton("REFRESH");
    JButton deleteButton = new JButton("DELETE");

    public AudioPanel(){
        super();
        setLayout(new BorderLayout());
        audioToolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        audioToolbar.setFloatable(false);

        playButton.addActionListener(audioButtonAction());
        refreshButton.addActionListener(refreshButtonAction());
        recordButton.addActionListener(recordButtonAction());
        deleteButton.addActionListener(deleteButtonAction());
        audioToolbar.add(recordButton);
        audioToolbar.add(playButton);
        audioToolbar.add(refreshButton);
        audioToolbar.add(deleteButton);

        jList = new JList(getAudioFileNames(audioFiles));
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(jList, BorderLayout.WEST);
        add(audioToolbar, BorderLayout.SOUTH);
    }

    private ActionListener deleteButtonAction() {
        return actionEvent -> {
            if (jList.getSelectedValue() != null) {
                File selectedFile = new File(AUDIO_LOCATION + jList.getSelectedValue().toString());
                selectedFile.delete();
            }
        };
    }

    ActionListener recordButtonAction() {
        return actionEvent -> {
            RecordAudioUtil.recordAudio();
        };
    }

    public String[] getAudioFileNames(File[] files){
        String[] fileNames = new String[files.length];
        for(int i = 0; i < files.length; i++){
            fileNames[i]  = files[i].getName();
        }
        return fileNames;
    }

    ActionListener audioButtonAction() {
        return actionEvent -> {
            if (jList.getSelectedValue() != null) {
                PlayAudioUtil.playAudio(AUDIO_LOCATION + jList.getSelectedValue().toString());
            }
        };
    }

    ActionListener refreshButtonAction() {
        return actionEvent -> {
            remove(jList);
            audioFiles = audioFolder.listFiles();
            jList = new JList(getAudioFileNames(audioFiles));
            add(jList, BorderLayout.WEST);
            audioToolbar.repaint();
            audioToolbar.revalidate();
            repaint();
            revalidate();
        };
    }

}
