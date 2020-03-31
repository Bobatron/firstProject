package gui;

import gui.mainmenu.MainPanel;
import gui.webcam.MyWebcamPanel;
import util.WebcamUtil;

import javax.swing.*;
import java.awt.*;

public class AppWindow extends JFrame {

    MyWebcamPanel myWebcamPanel;
    MainPanel mainPanel = new MainPanel();
    AppToolbar mainMenuToolBar = new AppToolbar();

    public AppWindow() {
        super("RetroTalker");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        setLayout(new BorderLayout());
        add(mainMenuToolBar, BorderLayout.NORTH);
        mainMenuToolBar.setButtonListener(getButtonListener());

        add(mainPanel, BorderLayout.CENTER);
        pack();
    }


    private void initWebcamPanel(){
        myWebcamPanel = new MyWebcamPanel();
        myWebcamPanel.setButtonListener(getButtonListener());
    }

    public IButtonListener getButtonListener() {
        return button -> {
            if (button == mainMenuToolBar.cameraButton) {
                remove(mainPanel);
                mainMenuToolBar.cameraButton.setVisible(false);
                repaint();
                initWebcamPanel();
                add(myWebcamPanel, BorderLayout.CENTER);
            } else if (button == myWebcamPanel.getCameraButton()) {
                WebcamUtil.takePhoto();
                remove(myWebcamPanel);
                repaint();
                mainMenuToolBar.cameraButton.setVisible(true);
                add(mainPanel, BorderLayout.CENTER);
            }
            revalidate();
            pack();
        };
    }
}