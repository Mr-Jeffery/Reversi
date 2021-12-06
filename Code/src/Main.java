import view.ChessBoardPanel;
import view.GameFrame;
import view.MainFrame;

import javax.swing.*;
import javax.swing.*;
import java.net.MalformedURLException;
public class Main {

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            });

        }
    }

