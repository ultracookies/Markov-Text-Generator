package p1.model.texteditor_functions.spellcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class SpellCheck {
    private File dictionary = new File("Data/dictionary.txt");
    private HashSet<String> words = new HashSet<>();

    public SpellCheck() throws IOException {
        var bufferedReader = new BufferedReader(new FileReader(dictionary));
        bufferedReader.lines().forEach(w -> words.add(w));
        bufferedReader.close();
    }

    public String spellCheck(String txt) {
        var wrongWords = new StringBuilder();
        var text = txt.replaceAll("[.,/!#$%^*(){}\"\n]+", " ").split(" ");
        //var misspellList = new LinkedList<String>();
        //Arrays.stream(text).filter(w -> !words.contains(w)).filter(w -> !misspellList.contains(w)).forEach(w -> wrongWords.append(w + "\n"));
        Arrays.stream(text).filter(w -> !words.contains(w)).forEach(w -> wrongWords.append(w + "\n"));
        return wrongWords.toString();
    }
}
