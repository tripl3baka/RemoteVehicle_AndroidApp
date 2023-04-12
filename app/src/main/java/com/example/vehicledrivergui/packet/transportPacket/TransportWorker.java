package com.example.vehicledrivergui.packet.transportPacket;

import android.icu.util.Output;

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

    public synchronized void workerQueue(OutputPacket packet){
        this.packet = packet;
    }

    public synchronized OutputPacket getPacket() {
        return packet;
    }

    Gson gson = new Gson();

    private void sendJson(OutputPacket packet){
        String packetjson = gson.toJson(packet);
        this.packet = null;

        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL("https://webhook.site/11c81846-ee4c-4e38-849b-e1b5ab3f24d8");
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run()
    {
        while(!exit) {

            if(packet != null)
            {
              sendJson(packet);
            }
        }


    }

}
