package com.example.vehicledrivergui.packet.transportPacket;

import com.example.vehicledrivergui.packet.OutputPacket;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TransportWorker extends Thread {

    private OutputPacket packet; // variable transported to Thread
    private volatile boolean exit = false; //Thread-ending flag

    public void threadExit()
    {
        exit = true;
    }

    public void workerQueue(OutputPacket packet){
        this.packet = packet;
    }

    Gson gson = new Gson();

    @Override
    public void run()
    {
        while(true) {
            if (exit) {
                return;
            }
            String packetjson = gson.toJson(packet);
            if(!packetjson.equals("null"))
            {
                HttpURLConnection httpURLConnection;
                try {
                    URL url = new URL("https://webhook.site/a49439e4-22b5-4c87-a8db-89d8a1a73e5d");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Content-Type", "application/json");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setChunkedStreamingMode(0);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    outputStream.write(packetjson.getBytes());
                    outputStream.flush();
                    outputStream.close();
                    httpURLConnection.getResponseCode();
                    httpURLConnection.disconnect();
                    //interrupt();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

}
