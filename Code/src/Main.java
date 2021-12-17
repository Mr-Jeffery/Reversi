// import view.GameFrame;
import view.MainFrame;
import view.RegisterFrame;

import javax.swing.*;

public class Main {

        public static void main(String[] args) {

            SwingUtilities.invokeLater(() -> {
                RegisterFrame registerFrame = new RegisterFrame();
                JPanel panel=new JPanel();
                JLabel img2 =new JLabel(new ImageIcon("Othello22.jpg"));
                panel.add(img2);
                img2.setBounds(0, 0, 1000, 800);
                img2.setVisible(true);
                panel.setBounds(0, 0, 1000, 800);
                registerFrame.add(panel);
                registerFrame.setVisible(true);
            });

        }
    }

