import java.util.Scanner;

public class Tester {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        Player p1 = new Player(scanner.nextLine());
        System.out.println(p1.getPid());
        Player p2 = new Player(scanner.nextLine());
        System.out.println(p2.getPid());
    }
}
