package com.example.vehicledrivergui;

import com.example.vehicledrivergui.packet.OutputPacket;

public class Transport {

    public void send(OutputPacket packet){
        System.out.println("acceleration:  " + packet.getAcceleration());
        System.out.println("direction:  " + packet.getDirection());

    }
}
