package gui.webcam;

import gui.IButtonListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class WebcamToolbar extends JToolBar implements ActionListener {

    JButton cameraButton;
    IButtonListener iButtonListener;

    WebcamToolbar(){
        super();
        setLayout(new FlowLayout(FlowLayout.CENTER));
        cameraButton = new JButton("TAKE PHOTO");
        cameraButton.addActionListener(this);
        add(cameraButton);
    }

    void setIPanelSwapable(IButtonListener iButtonListener){
        this.iButtonListener = iButtonListener;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        iButtonListener.buttonAction(actionEvent.getSource());
    }
}
