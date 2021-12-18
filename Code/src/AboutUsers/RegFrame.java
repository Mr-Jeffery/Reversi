package AboutUsers;

import view.MainFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class RegFrame extends JFrame {
    ArrayList<String> IDList= new ArrayList<String>();
    ArrayList<String> PWList= new ArrayList<String>();
    JLabel lbl1 = new JLabel("用户名:");
    JLabel lbl2 = new JLabel("密     码:");
    JTextField txt = new JTextField(40);
    JPasswordField pwd = new JPasswordField(40);
    JButton btn2 = new JButton("Register");
    JPanel pnl = new JPanel();
    private int error = 0;

    public RegFrame(String title) throws HeadlessException {
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
        pnl.add(btn2);
        this.getContentPane().add(pnl);

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txt.getText().equals(new String(pwd.getPassword()))){
                    pnl.removeAll();
                    MainFrame mainFrame = new MainFrame();
                    JPanel panel=new JPanel();
                    JLabel img2 =new JLabel(new ImageIcon("othello.jpg"));
                    panel.add(img2);
                    img2.setBounds(0, 0, 1000, 800);
                    img2.setVisible(true);
                    panel.setBounds(0, 0, 1000, 800);
                    mainFrame.add(panel);
                    mainFrame.setVisible(true);
                }
                else{
                    if(error < 3){
                        JOptionPane.showMessageDialog(null,"密码输入错误，请再试一次");
                        error++;
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"对不起，您不是合法用户");
                        txt.setEnabled(false);
                        pwd.setEnabled(false);
                        btn2.setEnabled(false);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        LoginFrm frm = new LoginFrm("测试");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setBounds(300, 300, 500, 500);
        frm.setVisible(true);
    }
}