import view.GameFrame;

import javax.swing.*;
import javax.swing.text.Position;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            GameFrame mainFrame = new GameFrame(800);
            mainFrame.setVisible(true);
        });
    }
}
