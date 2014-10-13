package com.antistupid.warbase.utils;

import java.util.Collection;

public class LineError {
    
    public final int lineno;
    public final String line;
    public final String error;
    
    
    public LineError(String error) { this(-1, null, error); }
    public LineError(int lineno, String line, String error) {
        this.lineno = lineno;
        this.line = line;
        this.error = error;
    }
    
    @Override
    public String toString() {
        return String.format("[%d] %s :: \"%s\"", lineno, error, line);
    }
    
    static public String toHtmlFragment(Collection<LineError> errors) {
        StringBuilder sb = new StringBuilder();
        sb.append("<p><b>Please fix the following errors:</b></p>");  
        int num = 0;
        for (LineError x: errors) {
            if (x.lineno >= 0) {
                sb.append("<p style='background-color: #ffffcc;margin-top:4px'>");
                sb.append("<tt style='font-size:8px; color: #008b8b'>");                
                sb.append("<b>[Line ");
                sb.append(x.lineno);
                sb.append("] </b> ");
                sb.append(x.line);
                sb.append("</tt></p>");
            }
            sb.append("<p style='font-size:9px'>");
            sb.append(x.error);
            sb.append("</p>");
            if (++num == 10) {
                break;
            }
        }
        if (num < errors.size()) {
            sb.append("<p style='margin-top:4px'>... and ");
            StringBuilderHelp.plural(sb, num, " ", "more error", "s", true);
            sb.append(" (not shown)</p>");
        }        
        return sb.toString();
    }
    
}
