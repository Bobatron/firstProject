package gui;

import gui.interfaces.IButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppToolbar extends JToolBar implements ActionListener {

    JButton cameraButton;
    JButton audioButton;
    JButton textButton;
    IButtonListener iButtonListener;

    public AppToolbar(){
        super();
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setFloatable(false);
        cameraButton = new JButton("IMAGE CAPTURE");
        audioButton = new JButton("AUDIO CAPTURE");
        textButton = new JButton("TEXT CAPTURE");
        cameraButton.addActionListener(this);
        audioButton.addActionListener(this);
        textButton.addActionListener(this);
        add(cameraButton);
        add(audioButton);
        add(textButton);
    }

    public void setButtonListener(IButtonListener iButtonListener){
        this.iButtonListener = iButtonListener;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        iButtonListener.buttonAction(actionEvent.getSource());
    }
}
