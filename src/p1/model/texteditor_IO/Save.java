package p1.model.texteditor_IO;

import java.io.*;

public class Save {

    public static boolean saveTxt(String txt, File txtFile) throws IOException {
        var oos = new ObjectOutputStream(new FileOutputStream(new File("./Data/" + txtFile.getName())));
        var bufferedWriter = new BufferedWriter(new FileWriter(txtFile));
        if (txt == null)
            txt = "";
        bufferedWriter.write(txt);
        bufferedWriter.close();
        oos.writeObject(txtFile);
        return true;
    }

}
