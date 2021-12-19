package AboutUsers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class NormalRegFrame extends JFrame {
    JLabel lbl1 = new JLabel("First Users name:");
    JLabel lbl2 = new JLabel("First Users Password:");
    JTextField txt1 = new JTextField(40);
    JPasswordField pwd1 = new JPasswordField(40);

    JLabel lbl3 = new JLabel("Second Users name:");
    JLabel lbl4 = new JLabel("Second Users Password:");
    JTextField txt2 = new JTextField(40);
    JPasswordField pwd2 = new JPasswordField(40);

    JButton btn2 = new JButton("Register");
    JPanel pnl = new JPanel();
    private int error = 0;

    public NormalRegFrame(String title) throws HeadlessException {
        super(title);
        init();
    }

    private void init() {
        this.setResizable(false);

        pwd1.setEchoChar('*');
        pwd2.setEchoChar('*');

        pnl.add(lbl1);
        pnl.add(txt1);
        pnl.add(lbl2);
        pnl.add(pwd1);
        pnl.add(btn2);
        pnl.add(lbl3);
        pnl.add(txt2);
        pnl.add(lbl4);
        pnl.add(pwd2);
        pnl.add(btn2);
        this.getContentPane().add(pnl);

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayerLoader playerLoader=new PlayerLoader();
                if(!playerLoader.PlayerLoader(txt1.getText(),String.valueOf(pwd1.getPassword()),txt2.getText(),String.valueOf(pwd2.getPassword())))
                {
                    PlayerSaver playerSaver=new PlayerSaver(txt1.getText(),String.valueOf(pwd1.getPassword()),txt2.getText(),String.valueOf(pwd2.getPassword()));
                }
            }
        });
    }

    public static void main(String[] args) {
        NormalRegFrame frm = new NormalRegFrame("测试");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setBounds(300, 300, 500, 500);
        frm.setVisible(true);
        PlayerLoader playerLoader= new PlayerLoader();
        System.out.println("okok1");
        boolean a= playerLoader.PlayerLoader("aa","11","bb","22");
        System.out.println("okok2");
        System.out.println(a+"is the game");
    }
}