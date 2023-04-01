package com.example.vehicledrivergui.packet;

public class OutputPacket {

    private int direction;
    private int acceleration;

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        if(direction > 100 || direction < -100) {
            direction = 0;
        }
        this.direction = direction;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        if(acceleration > 100 || acceleration < -100) {
            acceleration = 0;
        }
        this.acceleration = acceleration;
    }
}
