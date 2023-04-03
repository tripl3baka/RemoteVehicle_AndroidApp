package com.example.vehicledrivergui;

import com.example.vehicledrivergui.packet.OutputPacket;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Transport {

    Gson gson = new Gson();


    public void send(OutputPacket packet) {
        String packetjson = gson.toJson(packet);

        //TODO
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //System.out.println(gson.toJson(packetjson.getBytes()));


    }


}
