package util;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static gui.CameraPanel.IMAGE_LOCATION;

public class WebcamUtil {
    public static Webcam webcam;

    public static Webcam initWebcam() {
        webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        return webcam;
    }

    public static void closeWebcam(){
        webcam.close();
    }

    public static void takePhoto() {
        long timestamp = System.nanoTime();
        try {
            ImageIO.write(webcam.getImage(), "JPG", new File(IMAGE_LOCATION+"photo" + timestamp + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
