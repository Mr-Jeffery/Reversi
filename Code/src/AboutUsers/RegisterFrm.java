package AboutUsers;

import view.RegisterFrame;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class RegisterFrm extends JFrame {
    JLabel lbl1 = new JLabel("Users Name:");
    JLabel lbl2 = new JLabel("Password:");
    JTextField txt = new JTextField(40);
    JPasswordField pwd = new JPasswordField(40);
    JButton btn1 = new JButton("REGISTER");
    JPanel pnl = new JPanel();

    public RegisterFrm(String title) throws HeadlessException {
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
            PlayerLoader playerLoader=new PlayerLoader();
            boolean a=playerLoader.PlayerLoader(txt.getText(),String.valueOf(pwd.getPassword()));
            if(!a){
                PlayerSaver playerSaver = new PlayerSaver(txt.getText(),String.valueOf(pwd.getPassword()));
                System.out.println("Registered successfully");
                JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>Registered </font><font color='#cc22ff'>SUCCESSFULLY</font></h2></html>");
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
            else
            {
                System.out.println("Player have already registered!!");
                JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>YOU've </font><font color='#cc22ff'>ALREADY </font><font color='blue'>Registered.GO BACK AND LOG IN></font></h2></html>");
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
        });
    }


}


