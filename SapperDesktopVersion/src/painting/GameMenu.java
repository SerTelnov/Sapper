package painting;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Created by Sergey on 11.07.2017.
 */

public class GameMenu {

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("A Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);
        return menuBar;
    }
}
