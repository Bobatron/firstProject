package gui.webcam;

import com.github.sarxos.webcam.WebcamPanel;
import gui.IButtonListener;
import util.WebcamUtil;
import javax.swing.*;
import java.awt.*;

public class MyWebcamPanel extends WebcamPanel {

    WebcamToolbar webcamToolbar = new WebcamToolbar();

    public MyWebcamPanel() {
        super(WebcamUtil.getWebcam());
        setImageSizeDisplayed(true);
        setMirrored(true);
        setLayout(new BorderLayout());
        add(webcamToolbar, BorderLayout.SOUTH);
    }

    public void setButtonListener(IButtonListener iButtonListener){
        webcamToolbar.setIPanelSwapable(iButtonListener);
    }

    public JButton getCameraButton(){
        return webcamToolbar.cameraButton;
    }

}
