package com.example.vehicledrivergui.packet;

public class OutputPacket {

    private int direction;
    private int acceleration;


    public void setDirection(int direction) {
        if(direction > 100 || direction < -100) {
            direction = 0;
        }
        this.direction = direction;
    }

    public void setAcceleration(int acceleration) {
        if(acceleration > 100 || acceleration < -100) {
            acceleration = 0;
        }
        this.acceleration = acceleration;
    }
}
