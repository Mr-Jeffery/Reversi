package AboutUsers;

import view.MainFrame;
import view.RegisterFrame;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class LoginFrm extends JFrame {
    JLabel lbl1 = new JLabel("Users Name:");
    JLabel lbl2 = new JLabel("Password:");
    JTextField txt = new JTextField(40);
    JPasswordField pwd = new JPasswordField(40);
    JButton btn1 = new JButton("LOG IN");
    JPanel pnl = new JPanel();
    private int error = 0;

    public LoginFrm(String title) throws HeadlessException {
        super(title);
        init();
    }

    private void init() {
        this.setResizable(false);

        pwd.setEchoChar('*');

        pnl.add(lbl1);
        pnl.add(txt);
        pnl.add(lbl2);
        pnl.add(pwd);
        pnl.add(btn1);
        this.getContentPane().add(pnl);

        btn1.addActionListener(e -> {
            PlayerLoader playerLoader = new PlayerLoader();
            boolean a = playerLoader.PlayerLoader(txt.getText(), String.valueOf(pwd.getPassword()));
            if (a) {
                System.out.println("Loaded successfully");
                JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>Loaded  </font><font color='#cc22ff'>SUCCESSFULLY</font></h2></html>");
                MainFrame mainFrame = new MainFrame();
                JPanel panel = new JPanel();
                JLabel img2 = new JLabel(new ImageIcon("choose_Mode.JPG"));
                panel.add(img2);
                img2.setBounds(0, 0, 1000, 800);
                img2.setVisible(true);
                panel.setBounds(0, 0, 1000, 800);
                mainFrame.add(panel);
                mainFrame.setVisible(true);
                this.setVisible(false);
            } else {
                System.out.println("Player haven't registered");
                JOptionPane dl = new JOptionPane();
                int userOption = JOptionPane.showConfirmDialog(null, "You Haven't registered yet !Go Back?", "WARNING", JOptionPane.YES_NO_OPTION);
                if (userOption == JOptionPane.OK_OPTION) {
                    RegisterFrame registerFrame = new RegisterFrame();
                    JPanel panel=new JPanel();
                    JLabel img2 =new JLabel(new ImageIcon("Othello22.jpg"));
                    panel.add(img2);
                    img2.setBounds(0, 0, 1000, 800);
                    img2.setVisible(true);
                    panel.setBounds(0, 0, 1000, 800);
                    registerFrame.add(panel);
                    registerFrame.setVisible(true);
                    this.setVisible(false);
                }
                else if(userOption == JOptionPane.NO_OPTION)
                {
                    LoginFrm frm = new LoginFrm("LOG IN");
                    frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frm.setBounds(300, 300, 500, 500);
                    frm.setVisible(true);
                    this.setVisible(false);
                }
            }
        });

    }
}


