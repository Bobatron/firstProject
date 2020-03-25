package util;

import java.io.File;
import java.io.FileInputStream;

public class ImageManager {

    static File files = new File("src/main/resources/images");
    public static int ImageCount(){
        return files.list().length;
    }

}
