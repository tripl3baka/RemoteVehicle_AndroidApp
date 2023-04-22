package com.example.vehicledrivergui.packet.transportPacket;
import com.example.vehicledrivergui.packet.OutputPacket;

public final class Transport extends Thread {

    private static Transport instance = null;
    TransportWorker transportWorker = new TransportWorker();

    private Transport()
    {
        transportWorker.start();
    }

    public static synchronized Transport getInstance(){
        if(instance == null){
            instance = new Transport();
        }
        return instance;
    }



    public void send(OutputPacket packet) {

            transportWorker.workerQueue(packet);
    }
}
