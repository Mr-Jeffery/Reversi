package Asssignment4Components;//package Asssignment4Components;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class Saver{
//    private Saver(){}
//    public static void save(Game game){
//        File file = new File(".\\"+game.getName()+".txt");
//        try {
//            BufferedWriter out = new BufferedWriter(new FileWriter(".\\"+game.getName()+".txt"));
//            out.write(String.valueOf(game.toString()));
//            out.close();
//            System.out.println("The Game has been saved!");
//        } catch (IOException e) {
//            System.out.println("The Game can't be saved!");
//            e.printStackTrace();
//        }
//
//    }
//}

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileOutputStream;


public class Saver {
    public static void fileChooser() {
        JFileChooser chooser = new JFileChooser(".//Code//src//Saved_games");

//打开选择器面板
        int returnVal = chooser.showSaveDialog(new JPanel());
//保存文件从这里入手，输出的是文件名
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("你打开的文件夹是: " +
                    chooser.getSelectedFile().getPath());
            String path = chooser.getSelectedFile().getPath();
            try {
                File f = new File(path+".txt");
                System.out.println(f.getAbsolutePath());
                f.createNewFile();
                FileOutputStream out = new FileOutputStream(f);

                out.write("".getBytes());
                out.close();
            } catch (Exception e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        fileChooser();
    }
}