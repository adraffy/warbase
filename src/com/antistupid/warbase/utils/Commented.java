package com.antistupid.warbase.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;

public class Commented {

    // raffy commented text format    
    // designed for hack files, strange configuration files, etc.
    // 
    // [input]
    // <data-1> # <comment>        
    // <empty line>
    //    <data-2>    # <comment>    
    // <<<A
    // ...
    // data-3  
    // ... 
    // A;
    // 
    // [output]
    // { <data-1> , <data-2>, <data-3...\n...> }
    
    static public final int RELEASE = 3;
    
    // 004
    // added newLineNumberReader
    // 003
    // added multiline block
    // 002
    // added java8 support
    // 001
    // first version
    
    static public LineNumberReader newLineNumberReader(Path file, OpenOption... a) throws IOException {
        return new LineNumberReader(new InputStreamReader(Files.newInputStream(file, a), StandardCharsets.UTF_8));
    }
    
    static public String readLine(BufferedReader r) throws IOException {
        while (true) {
            String line = r.readLine();
            if (line == null) return null;
            line = strip(line);
            if (line != null) return line;            
        }
    }
    
    // example:
    // <<<AAA
    // anything goes here
    // multiline shit too
    // even # comments
    // AAA;
    
    static public final String BLOCK_PREFIX = "<<<";
    
    static public String readBlock(BufferedReader r) throws IOException {
        String line = readLine(r);        
        return line == null ? null : readBlock(r, line);
    }
    
    static public String readBlock(BufferedReader r, String line0) throws IOException {
        if (!line0.startsWith(BLOCK_PREFIX)) {
            return line0;
        }
        String key = line0.substring(BLOCK_PREFIX.length()) + ";";
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = r.readLine();
            if (line == null) {
                throw new IOException("Missing end of block: " + key);
            }
            if (line.contains(key) && line.trim().equals(key)) {
                break;
            }            
            if (sb.length() > 0) {
                sb.append("\n");
            }            
            sb.append(line);
        }
        return sb.toString();        
    }
    
    static public String strip(String line) {
        int pos = line.indexOf('#');
        if (pos >= 0) line = line.substring(0, pos);
        line = line.trim();
        if (line.isEmpty()) return null;
        return line;        
    }
    
    
}
