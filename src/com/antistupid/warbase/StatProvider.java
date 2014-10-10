package com.antistupid.warbase;

import com.antistupid.warbase.stats.StatMap;

public interface StatProvider {    
    void collectStats(StatMap stats);
}
