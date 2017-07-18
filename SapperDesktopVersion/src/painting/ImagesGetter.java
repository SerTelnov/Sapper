package painting;

import game.Cell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Sergey on 16.07.2017.
 */

public class ImagesGetter {
    private static class Images {
        public Images() {
            try {
                smile = makeImageIcon(loadImage("images/smile.png"));
                sad = makeImageIcon(loadImage("images/sad.png"));
                flag = loadImage("images/flag.png");
                flagIcon = makeImageIcon(flag);
                bomb = loadImage("images/bomb.png");
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

        private BufferedImage loadImage(String address) throws IOException {
            try {
                return ImageIO.read(Image.class.getResource("/" + address));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return null;
        }
    }
    private static Images imgs = new Images();
    public static final BufferedImage BOMB_IMAGE = imgs.getBomb();
    public static final BufferedImage FLAG_IMAGE = imgs.getFlag();
    public static final ImageIcon SMILE_ICON = imgs.getSmile();
    public static final ImageIcon SAD_ICON = imgs.getSad();
    public static final ImageIcon FLAG_ICON = imgs.getFlagIcon();
}
