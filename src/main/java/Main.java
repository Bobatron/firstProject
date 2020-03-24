import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import gui.AppWindow;
import webcam.MyWebcam;

public class Main {

    public static void main(String[] args) {
        MyWebcam myWebcam = new MyWebcam();
        AppWindow appWindow = new AppWindow(myWebcam.getWebcamPanel());
    }
}
