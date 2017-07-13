package painting;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Sergey on 10.07.2017.
 */

public class Images {
    Images() {
//        try {
//            ONE = ImageIO.read(new File("C:\\pro\\SapperConsoleVersion\\src\\painting\\one.jpg"));
//        } catch(IOException ie) {
//            ONE = null;
//            System.out.println(ie.getMessage());
//        }
    }
    private BufferedImage ONE;
    public BufferedImage getOne() { return ONE; }
}
