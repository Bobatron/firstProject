import gui.AppWindow;
import gui.MainMenu;
import util.ImageManager;
import webcam.MyWebcam;

public class Main {

    public static void main(String[] args) {
        AppWindow appWindow = new AppWindow();
        MyWebcam myWebcam = new MyWebcam(appWindow);
        MainMenu mainMenu = new MainMenu(appWindow);
        myWebcam.init(mainMenu);
        mainMenu.init(myWebcam);
        appWindow.draw(mainMenu);
    }
}
