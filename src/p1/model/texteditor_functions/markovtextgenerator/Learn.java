package p1.model.texteditor_functions.markovtextgenerator;

import java.util.LinkedList;
import java.util.TreeMap;

public class Learn {
    private String[] words;
    private TreeMap<String, LinkedList<String>> parents = new TreeMap<>();

    private void fillParents() {
        parents.clear();
        for (int i=0; i<words.length; i++) {
            if (!parents.containsKey(words[i]))
                parents.put(words[i], new LinkedList<>());
            if (i == words.length-1) break;
            parents.get(words[i]).add(words[i+1]);
        }
    }

    public void inputTxt(String txt1) {
        words = txt1.replaceAll("[\\p{Punct}]", "").replaceAll("\n", " ").split(" ");
        fillParents();
    }

    public TreeMap<String, LinkedList<String>> getParents() {
        return parents;
    }
}
