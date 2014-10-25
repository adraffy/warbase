package com.antistupid.warbase.utils;

public class WarBaseFmt {
    
    
    static public void tenths(StringBuilder sb, int value, int div) {
        sb.append(value / div);
        sb.append('.');
        sb.append((value * 10 / div) % 10);
    }

    static public void msDur(StringBuilder sb, int ms) {
        if (ms < 0) {
            sb.append("-");
            msDur(sb, -ms);            
        } else if (ms < 1000) {
            sb.append(ms);
            sb.append("ms");
        } else if (ms < 10000) {
            tenths(sb, ms, 1000);
            sb.append('s');
        } else if (ms < 60000) {
            sb.append(ms / 1000);
            sb.append('s');
        } else if (ms < 600000) {
            tenths(sb, ms, 60000);
            sb.append('m');
        } else if (ms < 3600000) {
            sb.append(ms / 60000);
            sb.append('m');
        } else if (ms < 36000000) {
            tenths(sb, ms, 3600000);
            sb.append('h');
        } else if (ms < 86400000) {
            sb.append(ms / 3600000);
            sb.append('h');
        } else {
            tenths(sb, ms, 86400000);
            sb.append('d');
        }   
    }
    
}
