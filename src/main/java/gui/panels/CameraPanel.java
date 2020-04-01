package gui.panels;

import com.github.sarxos.webcam.WebcamPanel;
import util.WebcamUtil;
import javax.swing.*;
import java.awt.*;

public class CameraPanel extends WebcamPanel {

    JButton cameraButton = new JButton("TAKE PHOTO");

    public CameraPanel() {
        super(WebcamUtil.getWebcam());
        setImageSizeDisplayed(true);
        setMirrored(true);
        setLayout(new BorderLayout());

        cameraButton.addActionListener(actionEvent -> WebcamUtil.takePhoto());
        add(cameraButton, BorderLayout.SOUTH);
    }
}
