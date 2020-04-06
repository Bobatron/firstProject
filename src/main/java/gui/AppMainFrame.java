package gui;

import gui.interfaces.IActionListener;
import util.WebcamUtil;

import javax.swing.*;
import java.awt.*;

public class AppMainFrame extends JFrame {

    public static final int PREF_V = 600;
    public static final int PREF_H = 800;

    CameraPanel cameraPanel;
    AudioPanel audioPanel = new AudioPanel();
    TextPanel textPanel = new TextPanel();
    AppToolbar appToolbar = new AppToolbar();
    Component focusPanel;

    public AppMainFrame() {
        super("RetroTalker");
        setResizable(false);
        setPreferredSize(new Dimension(PREF_H, PREF_V));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());
        add(appToolbar, BorderLayout.NORTH);
        appToolbar.setActionListener(getActionListener());
        audioPanel.setActionListener(getActionListener());
        textPanel.setActionListener(getActionListener());

        setFocusPanel(textPanel);
        add(focusPanel, BorderLayout.CENTER);
        pack();
    }

    public void setFocusPanel(Component panel){
        focusPanel = panel;
    }


    private void initCameraPanel(){
        cameraPanel = new CameraPanel();
        cameraPanel.setActionListener(getActionListener());
    }

    public IActionListener getActionListener() {
        return actionSource -> {
            if (actionSource == appToolbar.cameraButton) {
                updatePanel(focusPanel, cameraPanel);
            } else if (actionSource == appToolbar.textButton) {
                updatePanel(focusPanel, textPanel);
            } else if (actionSource == appToolbar.audioButton){
                updatePanel(focusPanel, audioPanel);
            } else if (actionSource == "refresh"){
                repaint();
                revalidate();
                pack();
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
            if(cameraPanel != null && cameraPanel.webcamPanel != null && cameraPanel.webcamPanel.getWebcam().isOpen()){
                WebcamUtil.closeWebcam();
            }
        }
        setFocusPanel(newPanel);
        add(focusPanel, BorderLayout.CENTER);
        revalidate();
        pack();
    }
}