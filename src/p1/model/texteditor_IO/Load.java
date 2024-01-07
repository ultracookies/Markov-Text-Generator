package p1.model.texteditor_IO;

import java.io.*;

public class Load {

    public static String test(File f) throws IOException {
        var b = new StringBuilder();
        var br = new BufferedReader(new FileReader(f));
        br.lines().forEach(w -> b.append(w + "\n"));
        br.close();
        return b.toString();
    }
}
