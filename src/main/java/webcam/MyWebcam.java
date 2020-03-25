package webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import gui.AppWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MyWebcam {
    public static Webcam webcam;
    public WebcamPanel webcamPanel;
    public JButton cameraButton;
    AppWindow parent;
    Component swapComponent;

    public MyWebcam(AppWindow parent) {
        this.parent = parent;
    }

    public void open(){
        webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        createButton();
        createPanel();
    }

    public void init(Component swapComponent){
        this.swapComponent = swapComponent;
    }

    private void createPanel(){
        webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setImageSizeDisplayed(true);
        webcamPanel.setMirrored(true);
        webcamPanel.add(cameraButton);
    }

    private void createButton(){
        cameraButton = new JButton("TAKE PHOTO");
        cameraButton.addActionListener(new TakePhoto(this.swapComponent));
    }

    private class TakePhoto implements ActionListener {
        Component component;
        public TakePhoto(Component component){
            this.component = component;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            long timestamp = System.nanoTime();
            try {
                ImageIO.write(webcam.getImage(), "JPG", new File("src/main/resources/images/photo"+timestamp+".jpg"));
                webcam.close();
                parent.swapPanel(component);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
