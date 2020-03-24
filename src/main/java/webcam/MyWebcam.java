package webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MyWebcam {
    public Webcam webcam;
    public WebcamPanel webcamPanel;
    public JButton cameraButton;

    public MyWebcam() {
        webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        createButton();
        createPanel();
    }

    public WebcamPanel getWebcamPanel(){
        return webcamPanel;
    }

    private void createPanel(){
        webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setImageSizeDisplayed(true);
        webcamPanel.setMirrored(true);
        webcamPanel.add(cameraButton);
    }

    private void createButton(){
        cameraButton = new JButton("TAKE PHOTO");
        cameraButton.addActionListener(new TakePhoto());
    }

    private class TakePhoto implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            long timestamp = System.nanoTime();
            try {
                ImageIO.write(webcam.getImage(), "JPG", new File("src/main/resources/images/photo"+timestamp+".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
