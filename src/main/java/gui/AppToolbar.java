package gui;

import gui.interfaces.IActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppToolbar extends JToolBar implements ActionListener {

    JButton cameraButton;
    JButton audioButton;
    JButton textButton;
    IActionListener iActionListener;

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

    public void setActionListener(IActionListener iActionListener){
        this.iActionListener = iActionListener;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        iActionListener.action(actionEvent.getSource());
    }
}
