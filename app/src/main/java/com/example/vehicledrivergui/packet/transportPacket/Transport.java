package com.example.vehicledrivergui.packet.transportPacket;
import com.example.vehicledrivergui.packet.OutputPacket;

public class Transport extends Thread {
    TransportWorker transportWorker = new TransportWorker();

    public Transport()
    {
        transportWorker.start();
    }



    public void send(OutputPacket packet) {

            transportWorker.workerQueue(packet);
            //transportWorker.threadExit();

    }
}
