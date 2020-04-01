package gui;

import gui.interfaces.IButtonListener;
import gui.panels.AudioPanel;
import gui.panels.TextPanel;
import gui.panels.CameraPanel;
import util.WebcamUtil;

import javax.swing.*;
import java.awt.*;

public class AppWindow extends JFrame {

    CameraPanel cameraPanel;
    AudioPanel audioPanel = new AudioPanel();
    TextPanel textPanel = new TextPanel();
    AppToolbar mainMenuToolBar = new AppToolbar();
    Component focusPanel;

    public AppWindow() {
        super("RetroTalker");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());
        add(mainMenuToolBar, BorderLayout.NORTH);
        mainMenuToolBar.setButtonListener(getButtonListener());

        setFocusPanel(textPanel);
        add(focusPanel, BorderLayout.CENTER);
        pack();
    }

    public void setFocusPanel(Component panel){
        focusPanel = panel;
    }


    private void initCameraPanel(){
        cameraPanel = new CameraPanel();
    }

    public IButtonListener getButtonListener() {
        return button -> {
            if (button == mainMenuToolBar.cameraButton) {
                updatePanel(focusPanel, cameraPanel);
            } else if (button == mainMenuToolBar.textButton) {
                updatePanel(focusPanel, textPanel);
            } else if (button == mainMenuToolBar.audioButton){
                updatePanel(focusPanel, audioPanel);
            }
        };
    }

    public void updatePanel(Component currentPanel, Component newPanel){
        if(currentPanel == newPanel){
            return;
        }
        remove(currentPanel);
        repaint();
        if(newPanel == cameraPanel){
            initCameraPanel();
            newPanel = cameraPanel;
        } else {
            if(cameraPanel != null && cameraPanel.getWebcam().isOpen()){
                WebcamUtil.closeWebcam();
            }
        }
        setFocusPanel(newPanel);
        add(focusPanel, BorderLayout.CENTER);
        revalidate();
        pack();
    }
}