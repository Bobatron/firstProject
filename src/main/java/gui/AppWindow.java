package gui;

import com.github.sarxos.webcam.WebcamPanel;

import javax.swing.*;

public class AppWindow extends JFrame {

    public AppWindow(WebcamPanel panel){
        super("The Photo App");
        this.add(panel);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

}
