package com.antistupid.warbase.data;

public class AsiaUpgradeChain {
    
    static public int convert(int chainId) {
        switch (chainId) {
            case 111: return 113;
            case 116: return 117;
            case 124: return 125;
            default: return chainId;
        }        
    }
    
    static public boolean changes(int chainId) {
        return convert(chainId) != chainId;
    }

}
