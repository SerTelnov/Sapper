package painting;

import game.Cell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Sergey on 10.07.2017.
 */

public class Images {
    public Images() {
        try {
            smile = makeImageIcon(ImageIO.read(new File("C:\\pro\\Sapper\\smile.png")));
            sad = makeImageIcon(ImageIO.read(new File("C:\\pro\\Sapper\\sad.png")));
            flag = ImageIO.read(new File("C:\\pro\\Sapper\\flag.png"));
            bomb = ImageIO.read(new File("C:\\pro\\Sapper\\bomb.png"));
        } catch(IOException ie) {
            smile = null;
            System.out.println(ie.getMessage());
        }
    }

    private ImageIcon makeImageIcon(BufferedImage img) {
        Image newImg = img.getScaledInstance( Cell.WIDTH, Cell.HEIGHT,  java.awt.Image.SCALE_SMOOTH );
        return new ImageIcon(newImg);
    }

    private BufferedImage flag, bomb;
    private ImageIcon smile, sad;
    public ImageIcon getSmile() { return smile; }
    public ImageIcon getSad() { return sad; }
    public BufferedImage getFlag() { return flag; }
    public BufferedImage getBomb() { return bomb; }
}
