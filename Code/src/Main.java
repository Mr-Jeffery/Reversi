// import view.GameFrame;
import view.MainFrame;

import javax.swing.*;

public class Main {

        public static void main(String[] args) {

            SwingUtilities.invokeLater(() -> {
                MainFrame mainFrame = new MainFrame();
                JPanel panel=new JPanel();
                JLabel img2 =new JLabel(new ImageIcon("othello.jpg"));
                panel.add(img2);
                img2.setBounds(0, 0, 1000, 800);
                img2.setVisible(true);
                panel.setBounds(0, 0, 1000, 800);
                mainFrame.add(panel);
                mainFrame.setVisible(true);
            });

        }
    }

