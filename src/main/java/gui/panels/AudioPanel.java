package gui.panels;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AudioPanel extends JPanel {

    final String AUDIO_LOCATION = "src/main/resources/audio/";
    File audioFolder = new File(AUDIO_LOCATION);
    JList jList;
    JButton recordButton = new JButton("RECORD");
    JButton playButton = new JButton("PLAY");

    public AudioPanel(){
        super();
        setLayout(new BorderLayout());
        JPanel subPabel = new JPanel();
        subPabel.setLayout(new FlowLayout(FlowLayout.LEFT));


        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(jList.getSelectedValue() != null) {
                    String selectedFile = jList.getSelectedValue().toString();
                    System.out.println(jList.getSelectedValue().toString());
                    try
                    {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(new File(AUDIO_LOCATION+selectedFile)));
                        clip.start();
                    }
                    catch (Exception exc)
                    {
                        exc.printStackTrace(System.out);
                    }
                }
            }
        });
        subPabel.add(recordButton);
        subPabel.add(playButton);

        File[] fileNames = audioFolder.listFiles();
        String[] files = new String[fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            files[i] = fileNames[i].getName();
        }
        jList = new JList(files);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(jList, BorderLayout.WEST);
        add(subPabel, BorderLayout.SOUTH);
    }

}
