import java.util.Timer;

public class test {
    public static void main(String[] args) throws InterruptedException {
        for (int i=0 ;i<10;i++){
            Thread.sleep(1000);
            System.out.println(i);
        }
    }
}
