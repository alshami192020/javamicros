package uz.micros.devices;

import java.util.Date;

public class SensorStartThread extends Thread {

    private final Sensor s;

    public SensorStartThread(Sensor i) {
        s = i;
    }

    public void run(){
        try {
            Thread.sleep(5000);
            s.getNotifier().send(String.format("%s - started", s.getName()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
