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
    
    static public String toHtml(Collection<LineError> errors) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("Please fix the following errors:");  
        for (LineError x: errors) {
            sb.append("<br/>");
            if (x.lineno >= 0) {
                sb.append("<b>Line ");
                sb.append(x.lineno);
                sb.append("</b><br/>");
                sb.append("<tt>");
                sb.append(x.line);
                sb.append("</tt><br/>");
            }
            sb.append(x.error);
        }
        sb.append("</html>");
        return sb.toString();
    }
    
}
