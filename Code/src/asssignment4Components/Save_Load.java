package asssignment4Components;

import java.io.*;

class Saver{
    private Saver(){}
    public static void canSave(Game game){
        File file = new File(".\\src\\savedGame\\"+game.getGid()+".txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Can't create new file!");
            e.printStackTrace();
        }
        if (!file.exists()) {
            if(true/**设置点击按钮确认储存*/){Saver.save(game);}
        } else {
            System.out.println("The Game had been saved,do you want to overwrite it?");
            if(true/**设置点击按钮确认覆盖*/){Saver.save(game);}
        }

    }

    public static void save(Game game){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(".\\src\\savedGame\\"+game.getGid()+".txt"));
            String temp = game.toString();
            writer.write(temp);
            writer.close();
        } catch (IOException e) {
            System.out.println("The Game can't be saved!");
            e.printStackTrace();

        }
        System.out.println("The Game has been saved!");
    }


}


class Loader{
    private Loader(){}
    public static void load(Game game){
        try {
            BufferedReader in = new BufferedReader(new FileReader(".\\src\\"+game.getGid()+".txt"));
            //读取参数
            //load player
            //load game
            //Game game = new Game()
            in.close();
        }catch (IOException e) {
            System.out.println("The Game can't be loaded!");
        }
    }
}