package warbase;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import javax.xml.bind.DatatypeConverter;

public class WarBaseUtils {
    

    static public <T> Comparator<T[]> makeArrayComparator(Comparator<T> cmp) {
        return (a, b) -> {
            if (a == null) {
                return b == null ? 0 : 1;
            } else if (b == null) {
                return -1;
            }
            int n = a.length;
            if (n != b.length) {
                return Integer.compare(n, b.length);
            }      
            for (int i = 0; i < n; i++) {
                int c = cmp.compare(a[i], b[i]);
                if (c != 0) {
                    return c;
                }
            }        
            return 0;
        };
    }
    
    static public int compare(int[] a, int[] b) {
        int n = a.length;
        int c = n - b.length;
        if (c != 0) {
            return c;
        }
        for (int i = 0; i < n; i++) {
            c = Integer.compare(a[i], b[i]);
            if (c != 0) {
                return c;
            }
        }
        return 0;
    }
    
    static public final Comparator<int[]> INT_ARRAY_CMP = WarBaseUtils::compare;
    
   
    static public byte[] SHA1(byte[] dat) {
        try {            
            return MessageDigest.getInstance("SHA-1").digest(dat);
        } catch(NoSuchAlgorithmException err) {
            throw new IllegalArgumentException(err); // wont happen
        }  
    }
    
    static public String SHA1_hexString(String x) {
        return DatatypeConverter.printHexBinary(SHA1(x.getBytes(StandardCharsets.UTF_8)));
    }
    
    
    static public <T> int binarySearch(T[] array, ToIntFunction<T> f, int key) {
        int a = 0;
        int b = array.length;
        while (a < b) {
            int m = (a + b) >>> 1;
            int k = f.applyAsInt(array[m]);
            if (k == key) {
                return m;
            } else if (k > key) {
                b = m;
            } else {
                a = m + 1;
            }
        }
        return ~a;
    }
    
    
}
