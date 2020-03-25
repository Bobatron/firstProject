package gui;

import webcam.MyWebcam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {

    JButton jButton;
    boolean cameraButtonClicked;
    AppWindow parent;
    MyWebcam myWebcam;

    public MainMenu(AppWindow parent) {
        super();
        this.parent = parent;
        cameraButtonClicked = false;
        jButton = new JButton("OPEN CAMERA");
    }

    public void init(MyWebcam myWebcam){
        this.myWebcam = myWebcam;
        jButton.addActionListener(new OpenCamera(myWebcam));
        this.add(jButton);
    }

    private class OpenCamera implements ActionListener {
        MyWebcam myWebcam;

        public OpenCamera(MyWebcam myWebcam) {
            this.myWebcam = myWebcam;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            myWebcam.open();
            parent.swapPanel(myWebcam.webcamPanel);
        }
    }

}

