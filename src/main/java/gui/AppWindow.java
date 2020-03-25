package gui;

import com.github.sarxos.webcam.WebcamPanel;

import javax.swing.*;
import java.awt.*;

public class AppWindow extends JFrame {

    Component component;

    public AppWindow(){
        super("The Photo App");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void draw(Component component){
        this.component = component;
        this.add(this.component);
        this.pack();
    }

    public void swapPanel(Component component){
        this.remove(this.component);
        this.component = component;
        this.add(component);
        this.pack();
    }

}
