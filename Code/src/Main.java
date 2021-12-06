import view.GameFrame;

import javax.swing.*;
import javax.swing.*;
import java.net.MalformedURLException;
public class Main {

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                GameFrame mainFrame;
                mainFrame = new GameFrame(900);
                mainFrame.setVisible(true);
            });
        }
    }

