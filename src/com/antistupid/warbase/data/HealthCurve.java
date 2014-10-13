package com.antistupid.warbase.data;

public class HealthCurve {

    static final byte[] V = {14, 14, 15, 16, 17, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19, 19, 20, 20, 20, 20, 22, 22, 22, 22, 24, 24, 24, 24, 25, 25, 26, 26, 26, 26, 28, 28, 28, 28, 29, 29, 30, 30, 31, 31, 32, 32, 33, 33, 33, 34, 34, 34, 35, 35, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 37, 37, 37, 37, 37, 37, 38, 39, 40, 42, 42, 43, 43, 43, 44, 46, 47, 48, 48, 48, 49, 49, 49, 53, 55, 58, 60, 60, 60, 60, 60, 60};

    static public int get(int playerLevel) {
        if (playerLevel < 1) {
            throw new IllegalArgumentException("Illegal Player Level: " + playerLevel);
        }
        return V[Math.min(V.length, playerLevel) - 1];
    }
    
}
