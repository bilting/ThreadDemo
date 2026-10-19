
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class ThreadDemo{

    public static void main(String[] args) {

        SoundHello sh = new SoundHello();
        PrintMessage pm = new PrintMessage("hello");

        Thread t1 = new Thread(sh);
        //Thread t2 = new Thread(pm);
        Thread t2 = new Thread(pm);
        
  
        t1.start();
        t2.start();



        System.out.println("After starting   Threads");

    }
}


class PrintMessage implements Runnable {

    private String message;

    public PrintMessage(String message){
        this.message = message;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(message);
                MessageCounter.increment();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // Ignore
            }
        }
    }
}

class SoundHello implements Runnable {
    @Override
     public void run() {
        while (true) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("hello.aif"));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                System.out.println("Pratad hello");
                MessageCounter.increment();
                Thread.sleep(4000);

            } catch (InterruptedException e) {
                // Ignore
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

class MessageCounter {
    static int count = 0;

    public synchronized static void increment() {
        count++;
    }

    public static int getCount(){
        return count;
    }


}