package util;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class WebcamUtil {
    public static Webcam webcam;

    public static Webcam getWebcam() {
        webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        return webcam;
    }

    public static void takePhoto() {
        long timestamp = System.nanoTime();
        try {
            ImageIO.write(webcam.getImage(), "JPG", new File("src/main/resources/images/photo" + timestamp + ".jpg"));
            webcam.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
