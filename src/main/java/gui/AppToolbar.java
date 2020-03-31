package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppToolbar extends JToolBar implements ActionListener {

    JButton cameraButton;
    IButtonListener iButtonListener;

    public AppToolbar(){
        super();
        setLayout(new FlowLayout(FlowLayout.CENTER));
        cameraButton = new JButton("OPEN CAMERA");
        cameraButton.addActionListener(this);
        add(cameraButton);
    }

    public void setButtonListener(IButtonListener iButtonListener){
        this.iButtonListener = iButtonListener;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        iButtonListener.buttonAction(actionEvent.getSource());
    }
}
