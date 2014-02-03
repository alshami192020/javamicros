package uz.micros.devices;

import uz.micros.Notifier;

public class Sensor extends BaseDevice {

    public Sensor(Notifier ntf)  {
        super(ntf);
    }

    public void start(){

        SensorStartThread t = new SensorStartThread(this);
        t.start();

    }
}


/*

new Thread("" + i++){
            public void run(){
                System.out.println("Thread: " + getName() + " running");
                try {
                    Thread.sleep(2000);
                    System.out.println("Thread: " + getName() + " running2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

*/
