package UI.UIElements;

import UI.panels.FieldPainter;
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
    public static final int ICON_HEIGHT_SIZE = (int) (FieldPainter.CELL_HEIGHT * 1.5);
    public static final int ICON_WIDTH_SIZE = (int) (FieldPainter.CELL_WIDTH * 1.5);
    private static class Images {
        public Images() throws IOException {
            gameIcon = loadImage("images/MineGameIcon.png");
            smileIcon = makeImageIcon(loadImage("images/smile.png"));
            sadIcon = makeImageIcon(loadImage("images/sad.png"));
            boredIcon = makeImageIcon(loadImage("images/bored.png"));
            happyIcon = makeImageIcon(loadImage("images/happy.png"));

            flag = loadImage("images/flag.png");
            flagIcon = makeImageIcon(flag);
            bomb = loadImage("images/bomb.png");
            fakeBomb = loadImage("images/fakeBomb.png");
            explosion = loadImage("images/explosion.png");
            flagTaggedIcon = makeImageIcon(loadImage("images/flagTagged.png"));
        }

        private ImageIcon makeImageIcon(BufferedImage img) {
            Image newImg = img.getScaledInstance(ICON_HEIGHT_SIZE, ICON_WIDTH_SIZE,  java.awt.Image.SCALE_SMOOTH );
            return new ImageIcon(newImg);
        }

        public BufferedImage flag, bomb, fakeBomb, explosion, gameIcon;
        public ImageIcon smileIcon, sadIcon, flagIcon, flagTaggedIcon, boredIcon, happyIcon;

        private BufferedImage loadImage(String address) throws IOException {
            return ImageIO.read(Image.class.getResource("/" + address));
        }
    }

    private static Images imgs;

    static {
        try {
            imgs = new Images();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final BufferedImage BOMB_IMAGE = imgs.bomb;
    public static final BufferedImage FLAG_IMAGE = imgs.flag;
    public static final ImageIcon SMILE_ICON = imgs.smileIcon;
    public static final ImageIcon SAD_ICON = imgs.sadIcon;
    public static final ImageIcon FLAG_ICON = imgs.flagIcon;
    public static final BufferedImage EXPLOSION = imgs.explosion;
    public static final BufferedImage FAKE_BOMB = imgs.fakeBomb;
    public static final BufferedImage GAME_ICON = imgs.gameIcon;
    public static final ImageIcon FLAG_TAGGED = imgs.flagTaggedIcon;
    public static final ImageIcon BORED_ICON = imgs.boredIcon;
    public static final ImageIcon HAPPY_ICON = imgs.happyIcon;
}
