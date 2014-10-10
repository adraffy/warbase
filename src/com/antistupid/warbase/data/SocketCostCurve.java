package com.antistupid.warbase.data;

public class SocketCostCurve {

    static public int get(int itemLevel) {
        /*if (itemLevel < 1) {
            throw new IllegalArgumentException("Invalid Item Level: " + itemLevel);
        } else*/if (itemLevel < 18) {
            return 0;
        } else if (itemLevel < 30) {
            return 1;
        } else if (itemLevel < 42) {
            return 2;
        } else if (itemLevel < 54) {
            return 3;
        } else if (itemLevel < 88) {
            return 4;
        } else if (itemLevel < 99) {
            return 5;            
        } else if (itemLevel < 177) {
            return 6;
        } else {
            return 8;
        }
    }
    
}
