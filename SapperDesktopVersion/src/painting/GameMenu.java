package painting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Sergey on 11.07.2017.
 */

public class GameMenu {

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Game");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("Easy",
                KeyEvent.VK_T);

        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menu.add(menuItem);

        return menuBar;
    }

    public Container createContentPane() {

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        JTextArea output = new JTextArea(5, 30);
        output.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(output);

        contentPane.add(scrollPane, BorderLayout.CENTER);

        return contentPane;
    }
}
