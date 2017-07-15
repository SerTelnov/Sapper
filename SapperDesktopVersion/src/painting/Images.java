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
            smile = makeImageIcon(ImageIO.read(new File("images/smile.png")));
            sad = makeImageIcon(ImageIO.read(new File("images/sad.png")));
            flag = ImageIO.read(new File("images/flag.png"));
            flagIcon = makeImageIcon(flag);
            bomb = ImageIO.read(new File("images/bomb.png"));
        } catch(IOException ie) {
            System.out.println(ie.getMessage());
        }
    }

    private ImageIcon makeImageIcon(BufferedImage img) {
        Image newImg = img.getScaledInstance( Cell.WIDTH, Cell.HEIGHT,  java.awt.Image.SCALE_SMOOTH );
        return new ImageIcon(newImg);
    }

    private BufferedImage flag, bomb;
    private ImageIcon smile, sad, flagIcon;
    public ImageIcon getSmile() { return smile; }
    public ImageIcon getSad() { return sad; }
    public ImageIcon getFlagIcon() { return flagIcon; }
    public BufferedImage getFlag() { return flag; }
    public BufferedImage getBomb() { return bomb; }
}
