package warbase;

public class StringBuilderHelp {

    static public void plural(StringBuilder sb, int num, String sep, String normal, String plural, boolean suffix) {
        sb.append(num);
        sb.append(sep);
        if (num == 1 || suffix) {
            sb.append(normal);
        } 
        if (num != 1) {
            sb.append(plural);
        }
    }
    
    static public void trim(StringBuilder sb, String s, int w) {
        if (s.length() > w) {
            sb.append(s.substring(0, w));
        } else {
            sb.append(s);
        }
    } 
    
    static public void padLeftOrTrim(StringBuilder sb, String s, int w) {
        if (s.length() > w) {
            sb.append(s.substring(0, w));
        } else {
            for (int i = s.length(); i < w; i++) {
                sb.append(' ');
            }
            sb.append(s);
        }
    }
    
    static public void padRightOrTrim(StringBuilder sb, String s, int w) {
        if (s.length() > w) {
            sb.append(s.substring(0, w));
        } else {
            sb.append(s);
            for (int i = s.length(); i < w; i++) {
                sb.append(' ');
            }
        }
    }
}
